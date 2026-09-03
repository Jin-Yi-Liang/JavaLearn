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

                        //add session attribute to save username and password in backend
                        session.setAttribute("username",username);
                        session.setAttribute("password",password);
                        session.setMaxInactiveInterval(10);


                        if("admin".equals(username) && "Qwe123!@#".equals(password)){
                            String rememberMe=(String)request.getParameter("rememberMe");
                            if(rememberMe!=null){
                                //add Cookie to save username adn password in frontend
                                Cookie usernameCookie = new Cookie("username",username);
                                Cookie passwordCookie = new Cookie("password",password);
                                response.addCookie(usernameCookie);
                                response.addCookie(passwordCookie);
                            }
                            //redirect to blog page
                            response.sendRedirect("https://jin-yi-liang.github.io/blog/");
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