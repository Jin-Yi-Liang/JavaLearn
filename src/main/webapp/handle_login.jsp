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
                    <div>
                    <%
                        request.setCharacterEncoding("UTF-8");
                        String username=request.getParameter("username");
                        String password=request.getParameter("password");

                        session.setAttribute("username",username);
                        session.setAttribute("password",password);
                        session.setMaxInactiveInterval(5);

                        if("admin".equals(username) && "Qwe123!@#".equals(password)){
                            response.sendRedirect("blog/index.html");
                        }
                        else{
                            request.getRequestDispatcher("index.jsp").forward(request,response);
                            //response.sendRedirect("index.jsp");
                        }
                    %>
                    </div>

            </article>
            <footer>
                <br />
                <p>Michael reserve all rights</p>
            </footer>
        </section>
    </main>
</body>

</html>