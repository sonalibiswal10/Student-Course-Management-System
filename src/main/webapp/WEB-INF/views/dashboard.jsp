<!DOCTYPE html>
<html>
<head>
    <title>Student Course Dashboard</title>
    <style>
        * {
            box-sizing: border-box;
        }

        body {
            margin: 0;
            font-family: Arial, Helvetica, sans-serif;
            background: #eef2f7;
            color: #1d2733;
        }

        a {
            color: inherit;
            text-decoration: none;
        }

        .shell {
            max-width: 1180px;
            margin: 0 auto;
            padding: 28px 24px 42px;
        }

        .header {
            display: flex;
            align-items: center;
            justify-content: space-between;
            gap: 20px;
            padding: 22px;
            margin-bottom: 18px;
            border: 1px solid #d7dee8;
            border-radius: 8px;
            background: #ffffff;
        }

        h1 {
            margin: 0;
            font-size: 30px;
            line-height: 1.15;
        }

        .subtitle {
            margin: 7px 0 0;
            color: #5b6675;
            font-size: 15px;
        }

        .top-actions,
        .action-row {
            display: flex;
            gap: 10px;
            flex-wrap: wrap;
        }

        .button,
        button {
            display: inline-flex;
            align-items: center;
            justify-content: center;
            min-height: 38px;
            padding: 0 14px;
            border: 1px solid #bcc7d4;
            border-radius: 6px;
            background: #ffffff;
            color: #1d2733;
            font-size: 14px;
            font-weight: 700;
            cursor: pointer;
        }

        .button.primary {
            border-color: #2563eb;
            background: #2563eb;
            color: #ffffff;
        }

        .button.danger,
        button.danger {
            border-color: #dc2626;
            color: #dc2626;
        }

        .stats {
            display: grid;
            grid-template-columns: repeat(2, minmax(0, 1fr));
            gap: 14px;
            margin-bottom: 18px;
        }

        .stat {
            padding: 20px;
            border: 1px solid #d7dee8;
            border-radius: 8px;
            background: #ffffff;
        }

        .stat-label {
            color: #5b6675;
            font-size: 14px;
            font-weight: 700;
        }

        .stat-value {
            margin-top: 8px;
            font-size: 36px;
            font-weight: 800;
        }

        .grid {
            display: grid;
            grid-template-columns: repeat(2, minmax(0, 1fr));
            gap: 18px;
        }

        .panel {
            border: 1px solid #d7dee8;
            border-radius: 8px;
            background: #ffffff;
            overflow: hidden;
        }

        .panel-head {
            padding: 18px 20px;
            border-bottom: 1px solid #e4e9f0;
            background: #f9fbfd;
        }

        .panel h2 {
            margin: 0;
            font-size: 20px;
        }

        .panel-note {
            margin: 5px 0 0;
            color: #66717f;
            font-size: 13px;
        }

        .panel-body {
            padding: 18px 20px 20px;
        }

        .link-list {
            display: grid;
            gap: 10px;
            margin-bottom: 18px;
        }

        .link-card {
            display: flex;
            align-items: center;
            justify-content: space-between;
            gap: 14px;
            min-height: 58px;
            padding: 13px 14px;
            border: 1px solid #e2e8f0;
            border-radius: 6px;
            background: #ffffff;
        }

        .link-card:hover {
            border-color: #9db5d8;
            background: #f8fbff;
        }

        .link-title {
            display: block;
            font-weight: 800;
        }

        .link-meta {
            display: block;
            margin-top: 3px;
            color: #66717f;
            font-size: 13px;
        }

        .arrow {
            color: #2563eb;
            font-weight: 800;
        }

        .form-stack {
            display: grid;
            gap: 12px;
        }

        .mini-form {
            display: grid;
            grid-template-columns: 1fr auto;
            gap: 10px;
            padding: 12px;
            border: 1px solid #e2e8f0;
            border-radius: 6px;
            background: #fbfcfe;
        }

        label {
            display: grid;
            gap: 5px;
            color: #4b5563;
            font-size: 13px;
            font-weight: 700;
        }

        input {
            width: 100%;
            min-height: 38px;
            padding: 0 10px;
            border: 1px solid #c8d2df;
            border-radius: 6px;
            background: #ffffff;
            color: #1d2733;
            font-size: 14px;
        }

        .section-title {
            margin: 0 0 10px;
            color: #374151;
            font-size: 14px;
            font-weight: 800;
        }

        @media (max-width: 780px) {
            .header {
                align-items: flex-start;
                flex-direction: column;
            }

            .stats,
            .grid {
                grid-template-columns: 1fr;
            }

            .mini-form {
                grid-template-columns: 1fr;
            }
        }
    </style>
</head>
<body>
<main class="shell">
    <section class="header">
        <div>
            <h1>Student Course Management</h1>
            <p class="subtitle">Use this dashboard for every student, course, and assignment workflow.</p>
        </div>
        <div class="top-actions">
            <a class="button primary" href="/student/add">Add Student</a>
            <a class="button" href="/course/add">Add Course</a>
        </div>
    </section>

    <section class="stats">
        <div class="stat">
            <div class="stat-label">Total Students</div>
            <div class="stat-value">${studentCount}</div>
        </div>
        <div class="stat">
            <div class="stat-label">Total Courses</div>
            <div class="stat-value">${courseCount}</div>
        </div>
    </section>

    <section class="grid">
        <div class="panel">
            <div class="panel-head">
                <h2>Student Links</h2>
                <p class="panel-note">Create, view, update, delete, and inspect students.</p>
            </div>
            <div class="panel-body">
                <div class="link-list">
                    <a class="link-card" href="/student/all">
                        <span>
                            <span class="link-title">All Students</span>
                            <span class="link-meta">List students with view, edit, and delete options</span>
                        </span>
                        <span class="arrow">Open</span>
                    </a>
                    <a class="link-card" href="/student/add">
                        <span>
                            <span class="link-title">Add Student</span>
                            <span class="link-meta">Create a student with manual ID and course assignment</span>
                        </span>
                        <span class="arrow">Open</span>
                    </a>
                </div>

                <p class="section-title">Student Actions By ID</p>
                <div class="form-stack">
                    <form class="mini-form" action="/dashboard/student/details" method="get">
                        <label>Student ID
                            <input type="number" name="id" required/>
                        </label>
                        <button type="submit">View Details</button>
                    </form>
                </div>
            </div>
        </div>

        <div class="panel">
            <div class="panel-head">
                <h2>Course Links</h2>
                <p class="panel-note">Create, update, delete, and review course records.</p>
            </div>
            <div class="panel-body">
                <div class="link-list">
                    <a class="link-card" href="/course/all">
                        <span>
                            <span class="link-title">All Courses</span>
                            <span class="link-meta">List courses with edit and delete options</span>
                        </span>
                        <span class="arrow">Open</span>
                    </a>
                    <a class="link-card" href="/course/add">
                        <span>
                            <span class="link-title">Add Course</span>
                            <span class="link-meta">Create a course with manual course ID</span>
                        </span>
                        <span class="arrow">Open</span>
                    </a>
                </div>

                <p class="section-title">Course Actions By ID</p>
                <div class="form-stack">
                    <form class="mini-form" action="/dashboard/course/edit" method="get">
                        <label>Course ID
                            <input type="text" name="id" required/>
                        </label>
                        <button type="submit">Edit</button>
                    </form>
                </div>
            </div>
        </div>
    </section>
</main>
</body>
</html>
