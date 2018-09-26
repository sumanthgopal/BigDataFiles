<%-- 
    Document   : loginsuccess
    Created on : Feb 14, 2018, 7:05:29 PM
    Author     : Nirali
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
         <c:set var="contextPath" value="${pageContext.request.contextPath}"></c:set>
    
        
        
        <%--<jsp:include page="menu.jsp" />--%>
       
        
        <h1>Welcome ${sessionScope.username}</h1>
        [<a href="${contextPath}/message/send.htm">Send message</a>]
        [<a href="${contextPath}/message/display.htm">Display messages</a>]
        <br/> <br/>
        <form action="${contextPath}/message/search.htm" method="post">
            <input type="text" name='searchQuery'/>
            <input type='submit' value="Search Message">
        </form>
    </body>
</html>
