<%-- 
    Document   : search.jsp
    Created on : Sep 23, 2018, 6:41:19 PM
    Author     : sgopalakrishna
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Search Page</h1>
        <form action="results.htm" method="post">
            Enter Data for Search:<input type="text" name="data" required><br><br>
            <input type="submit" value="Submit">
        </form>
    </body>
</html>
