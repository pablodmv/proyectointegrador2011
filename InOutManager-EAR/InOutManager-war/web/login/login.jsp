<%-- 
    Document   : login
    Created on : 06/02/2012, 09:00:31 PM
    Author     : gustavo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
         <link rel="stylesheet" href="resources/css/styles.css">
        <title>Login Page</title>
    </head>
    <body>
         <div id="layout">
            <div id="layout2">
                <div id="top">

                </div>
                <div id="header">
                    <div id="logo"><h1>InOut</h1></div>
                    <div id="main-menu" class="centrado">
                        <div id="menu-content" class="centrado">
                        </div>
                    </div>
		</div>
		<div id="content" style="color:#6f3320;">
                    <form action="j_security_check" method="POST">
                        <table>
                            <tr>
                                <td>
                                    Usuario:
                                </td>
                                <td>
                                    <input type="text" name="j_username"/>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    Contraseña:
                                </td>
                                <td>
                                    <input type="password" name="j_password"/>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                   <input type="submit" value="Ingresar"/>
                                </td>
                            </tr>
                        </table>
                    </form>
		</div>

		<div id="footer">
			<p><a href="#">InOut Manager</a></p>
		</div>
            </div>
	</div>
    </body>
</html>
