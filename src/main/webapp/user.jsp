<%@ page
    contentType="text/html;charset=UTF-8"
    import="org.maxing.learning.jdbc.domain.JdbcStudent"
    import="java.math.BigDecimal"
%>
<head>
    <meta charset="utf-8">
    <title>User info</title>
</head>
<body>
    <header>
        <h1>User info</h1>
    </header>
    <main>
        <aside>
            <nav>
                <ul>
                    <li><a href=https://jin-yi-liang.github.io/blog/>blog</a></li>
                </ul>
            </nav>
        </aside>
        <section>
            <article>
                <div id="user_info">
                    <p>Username: ${sessionScope.username}</p>
                </div>
                <div id="student_info">
                    <%
                        JdbcStudent student = request.getAttribute("student");
                    %>
                    <p><%= student.toString() %></p>
                </div>
            </article>
            <footer>
                <p>Michale reserver all rights</p>
            </footer>
        </section>
    </main>
</body>