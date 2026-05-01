package com.project.Student.Course.Management.System;

import com.project.Student.Course.Management.System.entity.Course;
import com.project.Student.Course.Management.System.entity.Student;
import com.project.Student.Course.Management.System.repository.CourseRepository;
import com.project.Student.Course.Management.System.repository.StudentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ApplicationTests {

	@Autowired
	private TestRestTemplate restTemplate;

	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private CourseRepository courseRepository;

	@Test
	void contextLoads() {
	}


	@Test
	void studentPagesRender() {
		Course course = new Course();
		course.setId("101L");
		course.setCourseName("Java");
		course = courseRepository.save(course);

		Course secondCourse = new Course();
		secondCourse.setId("102L");
		secondCourse.setCourseName("Spring Boot");
		secondCourse = courseRepository.save(secondCourse);

		Student student = new Student();
		student.setId("B201");
		student.setName("Test Student");
		student.setEmailId("test@example.com");
		student.setCourseList(List.of(course));
		student = studentRepository.save(student);

		assertPageRenders("/student/all");
		assertPageRenders("/student/add");

		ResponseEntity<String> detailsResponse = restTemplate.getForEntity("/student/details/" + student.getId(), String.class);
		assertThat(detailsResponse.getStatusCode().is2xxSuccessful()).isTrue();
		assertThat(detailsResponse.getBody()).contains("Test Student", "Java");

		ResponseEntity<String> editResponse = restTemplate.getForEntity("/student/edit/" + student.getId(), String.class);
		assertThat(editResponse.getStatusCode().is2xxSuccessful()).isTrue();
		assertThat(editResponse.getBody()).contains("value=\"" + course.getId() + "\" checked=\"checked\"");

		MultiValueMap<String, String> form = new LinkedMultiValueMap<>();
		form.add("id", student.getId().toString());
		form.add("name", student.getName());
		form.add("emailId", student.getEmailId());
		form.add("courseIds", course.getId().toString());
		form.add("courseIds", secondCourse.getId().toString());

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

		ResponseEntity<String> updateResponse = restTemplate.postForEntity(
				"/student/update",
				new HttpEntity<>(form, headers),
				String.class);

		assertThat(updateResponse.getStatusCode().is3xxRedirection()).isTrue();

		Student updatedStudent = studentRepository.findByIdWithCourses(student.getId()).orElseThrow();
		assertThat(updatedStudent.getCourseList())
				.extracting(Course::getId)
				.containsExactlyInAnyOrder(course.getId(), secondCourse.getId());

		ResponseEntity<String> deleteResponse = restTemplate.getForEntity(
				"/student/delete/" + student.getId(),
				String.class);

		assertThat(deleteResponse.getStatusCode().is2xxSuccessful()
				|| deleteResponse.getStatusCode().is3xxRedirection()).isTrue();
		assertThat(studentRepository.existsById(student.getId())).isFalse();
		assertThat(courseRepository.existsById(course.getId())).isTrue();
	}

	@Test
	void courseCrudWorks() {
		Course course = new Course();
		course.setId("301L");
		course.setCourseName("Algorithms");
		course = courseRepository.save(course);

		assertPageRenders("/course/all");
		assertPageRenders("/course/add");

		ResponseEntity<String> editResponse = restTemplate.getForEntity("/course/edit/" + course.getId(), String.class);
		assertThat(editResponse.getStatusCode().is2xxSuccessful()).isTrue();
		assertThat(editResponse.getBody()).contains("Algorithms");

		MultiValueMap<String, String> createForm = new LinkedMultiValueMap<>();
		createForm.add("id", "302");
		createForm.add("courseName", "Database Systems");

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

		ResponseEntity<String> createResponse = restTemplate.postForEntity(
				"/course/save",
				new HttpEntity<>(createForm, headers),
				String.class);

		assertThat(createResponse.getStatusCode().is3xxRedirection()).isTrue();
		assertThat(courseRepository.findAll())
				.extracting(Course::getCourseName)
				.contains("Database Systems");

		MultiValueMap<String, String> updateForm = new LinkedMultiValueMap<>();
		updateForm.add("id", course.getId().toString());
		updateForm.add("courseName", "Advanced Algorithms");

		ResponseEntity<String> updateResponse = restTemplate.postForEntity(
				"/course/update",
				new HttpEntity<>(updateForm, headers),
				String.class);

		assertThat(updateResponse.getStatusCode().is3xxRedirection()).isTrue();
		assertThat(courseRepository.findById(course.getId()).orElseThrow().getCourseName())
				.isEqualTo("Advanced Algorithms");

		ResponseEntity<String> deleteResponse = restTemplate.getForEntity(
				"/course/delete/" + course.getId(),
				String.class);

		assertThat(deleteResponse.getStatusCode().is2xxSuccessful()
				|| deleteResponse.getStatusCode().is3xxRedirection()).isTrue();
		assertThat(courseRepository.existsById(course.getId())).isFalse();
	}

	private void assertPageRenders(String path) {
		ResponseEntity<String> response = restTemplate.getForEntity(path, String.class);

		assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
		assertThat(response.getBody()).isNotBlank();
	}
}
