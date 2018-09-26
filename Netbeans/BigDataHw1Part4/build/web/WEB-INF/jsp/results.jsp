<%-- 
    Document   : results.jsp
    Created on : Sep 24, 2018, 3:45:06 PM
    Author     : sgopalakrishna
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <table border="1px solid">
            <th>Title</th>
            <th>Genre</th>
            <th>MovieId</th>
            <c:forEach items="${requestScope.str}" var="s">
            <tr>
                <td>
                    ${s.title}
                </td>
                <td>
                    ${s.genre}
                </td>
                <td>
                    ${s.movieid}
                </td>
            </tr>
                
            </c:forEach>
        </table>
    </body>
</html>
