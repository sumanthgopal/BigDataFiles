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
            <th>IP</th>
            <th>Date</th>
            <th>Zone</th>
            <th>Method</th>
            <th>FileName</th>
            <th>Protocol</th>
            <th>ErrorCode</th>
            <c:forEach items="${requestScope.str}" var="s">
            <tr>
                <td>
                    ${s.ip}
                </td>
                <td>
                    ${s.date}
                </td>
                <td>
                    ${s.zone}
                </td>
                <td>
                    ${s.method}
                </td>
                <td>
                    ${s.filename}
                </td>
                <td>
                    ${s.protocol}
                </td>
                <td>
                    ${s.errorcode}
                </td>
            </tr>
                
            </c:forEach>
        </table>
    </body>
</html>
