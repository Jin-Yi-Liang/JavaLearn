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
                    <%
                        SimpleDateFormat sdf=new SimpleDateFormat();
                        String current_time=sdf.format(new Date());
                    %>
                    <%= current_time %>
                </div>

                <div id="application">
                        <%
                            Integer count=(Integer)application.getAttribute("count");
                            if(count==null) count=0;
                            application.setAttribute("count",++count);
                        %>
                        <p>Visited Counts: <%= count %></p>
                </div>

                <div id="session">
                    <%
                        String idSession=session.getId();
                        String usernameSession=(String)session.getAttribute("username");
                        String passwordSession=(String)session.getAttribute("password");

                        if("admin".equals(usernameSession) && "Qwe123!@#".equals(passwordSession)){
                            response.sendRedirect("https://jin-yi-liang.github.io/blog/");
                        }
                    %>
                    <p>Session Id: <%= idSession %></p>
                    <p>Session Username: <%= usernameSession %></p>
                    <p>Session Password: <%= passwordSession %></p>
                </div>

                <div id="Cookie">
                    <%
                        Cookie[]cookies=request.getCookies();
                        String usernameCookie="";
                        String passwordCookie="";
                        if(cookies!=null && cookies.length>0){
                            for(int i=0;i<cookies.length;++i){
                                Cookie cookie=cookies[i];
                                if("username".equals(cookie.getName())){
                                    usernameCookie=cookie.getValue();
                                }
                                else if("password".equals(cookie.getName())){
                                    passwordCookie=cookie.getValue();
                                }
                                else{
                                    System.out.println("uncognized attribute in cookie");
                                }
                            }
                        }
                    %>
                    <p>Cookie Username: <%= usernameCookie %></p>
                    <p>Cookie Password: <%= passwordCookie %></p>
                </div>

                <form action="handle_login.jsp" method="post">
                    <div>
                        <span>username:</span>
                        <input type="text" name="username" value="">
                    </div>
                    <div>
                        <span>password:</span>
                        <input type="text" name="password" value="">
                    </div>
                    <div>
                        <input type="checkbox" name="rememberMe" checked>
                        Remember Me
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