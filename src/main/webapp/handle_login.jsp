<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Login page</title>
    <link rel="stylesheet" href="../css/util.css">
</head>

<body>
    <header>
        <h1>Login Page</h1>
    </header>
    <main>
        <section>
            <article>
                    <div id="show_login_info">
                    <%
                        request.setCharacterEncoding("UTF-8");
                        String username=request.getParameter("username");
                        String password=request.getParameter("password");

                        if("admin".equals(username) && "Qwe123!@#".equals(password)){
                            response.sendRedirect("blog/index.html");
                        }
                        else{
                            request.getRequestDispatcher("index.jsp").forward(request,response);
                        }
                    %>

            </article>
            <footer>
                <br />
                <p>Michael reserve all rights</p>
            </footer>
        </section>
    </main>
</body>

</html>