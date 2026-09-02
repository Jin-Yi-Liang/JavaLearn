<%@ page contentType="text/html;charset=UTF-8" language="java"
        import="java.util.Date"
        import="java.text.SimpleDateFormat"
%>

<html>
<head>
    <title>JSP Page</title>
    <link rel="stylesheet" href="css/util.css">
    <script src="blog/js/util.js" defer></script>
</head>
<body>
    <header>
        <h1>JSP page</h1>
    </header>
    <main>
        <section>
            <article>

                <div id="time">
                    <p>
                        <%
                            SimpleDateFormat sdf=new SimpleDateFormat();
                            String current_time=sdf.format(new Date());
                        %>
                        <%= current_time %>
                    </p>
                </div>

                <div id="session">
                    <p>
                        <%
                            String id=session.getId();
                            String username=(String)session.getAttribute("username");
                            String password=(String)session.getAttribute("password");
                        %>
                        <p>Session Id: <%= id %></p>
                        <p>Username: <%= username %></p>
                        <p>Password: <%= password %></p>
                    </p>
                </div>

                <form action="handle_login.jsp" method="post">
                    <div>
                        <span>username:</span>
                        <input type="input" name="username" value="">
                    </div>
                    <div>
                        <span>password:</span>
                        <input type="input" name="password" value="">
                    </div>
                    <div>
                        <input type="submit" value="submit">
                    </div>
                </form>

            </article>
            <footer>
                <br />
                <p>Michael reserve all rights</p>
            </footer>
        </section>
    </main>
</body>
</html>