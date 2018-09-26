<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Read CSV Data</title>
    </head>

    <body>
        <c:if test="${empty requestScope.map.readData}">
            <form action="readcsv.htm" method="post">
                Enter CSV FileName: <input type="text" name="csvFileName" required="true"><br>
                <input type="hidden" name="hiddenField" value="csvName">
                <input type="submit" value="Submit">
                </form>
        </c:if>
        <c:if test="${requestScope.map.readData eq 'noData'}">
            <h1>No Data to Add</h1>
        </c:if>
        <c:if test="${requestScope.map.readData eq 'dataSent'}">
            <form action="readcsv.htm" method="post">
            <table border="1px solid">
                <tr>
                    <th>SalesOrderID</th>
                    <th>RevisionNumber</th>
                    <th>OrderDate</th>
                    <th>DueDate</th>
                    <th>ShipDate</th>
                    <th>Status</th>
                    <th>OnlineOrderFlag</th>
                    <th>SalesOrderNumber</th>
                    <th>PurchaseOrderNumber</th>
                    <th>AccountNumber</th>
                    <th>CustomerID</th>
                    <th>SalesPersonID</th>
                    <th>TerritoryID</th>
                    <th>BillToAddressID</th>
                    <th>ShipToAddressID</th>
                    <th>ShipMethodID</th>
                    <th>CreditCardID</th>
                    <th>CreditCardApprovalCode</th>
                    <th>CurrencyRateID</th>
                    <th>SubTotal</th>
                    <th>TaxAmt</th>
                    <th>Freight</th>
                    <th>TotalDue</th>
                    <th>Comment</th>
                    <th>ModifiedDate</th>
                </tr>
                <c:set var="i" value="1"/>
                <c:forEach items="${requestScope.map.csvData}" var="sales">
                    <tr>
                        <td><input name="salesOrderID${i}" type="number" value="${sales.salesOrderID}" required="true"></td>
                        <td><input name="revisionNumber${i}" type="number" value="${sales.revisionNumber}" required="true"></td>
                        <td><input name="orderDate${i}" type="date" value="<fmt:formatDate pattern = 'yyyy-MM-dd' value = '${sales.orderDate}'/>" required="true"></td>
                        <td><input name="dueDate${i}" type="date" value="<fmt:formatDate pattern = 'yyyy-MM-dd' value = '${sales.dueDate}'/>" required="true"></td>
                        <td><input name="shipDate${i}" type="date" value="<fmt:formatDate pattern = 'yyyy-MM-dd' value = '${sales.shipDate}'/>" required="true"></td>
                        <td><input name="status${i}" type="number" value="${sales.status}" required="true"></td>
                        <td><input name="onlineOrderFlag${i}" type="number" value="${sales.onlineOrderFlag}" required="true"></td>
                        <td><input name="salesOrderNumber${i}" type="text" value="${sales.salesOrderNumber}" required="true"></td>
                        <td><input name="purchaseOrderNumber${i}" type="text" value="${sales.purchaseOrderNumber}" required="true"></td>
                        <td><input name="accountNumber${i}" type="text" value="${sales.accountNumber}" required="true"></td>
                        <td><input name="customerID${i}" type="number" value="${sales.customerID}" required="true"></td>
                        <td><input name="salesPersonID${i}" type="number" value="${sales.salesPersonID}" required="true"></td>
                        <td><input name="territoryID${i}" type="number" value="${sales.territoryID}" required="true"></td>
                        <td><input name="billToAddressID${i}" type="number" value="${sales.billToAddressID}" required="true"></td>
                        <td><input name="shipToAddressID${i}" type="number" value="${sales.shipToAddressID}" required="true"></td>
                        <td><input name="shipMethodID${i}" type="number" value="${sales.shipMethodID}" required="true"></td>
                        <td><input name="creditCardID${i}" type="number" value="${sales.creditCardID}" required="true"></td>
                        <td><input name="creditCardApprovalCode${i}" type="number" value="${sales.creditCardApprovalCode}" required="true"></td>
                        <td><input name="currencyRateID${i}" type="number" value="${sales.currencyRateID}"></td>
                        <td><input name="subTotal${i}" type="number" value="${sales.subTotal}" step="0.0001" min="1" required="true"></td>
                        <td><input name="taxAmt${i}" type="number" value="${sales.taxAmt}" step="0.0001" min="1" required="true"></td>
                        <td><input name="freight${i}" type="number" value="${sales.freight}" step="0.0001" min="1" required="true"></td>
                        <td><input name="totalDue${i}" type="number" value="${sales.totalDue}" step="0.0001" min="1" required="true"></td>
                        <td><input name="comment${i}" type="text" value="${sales.comment}"></td>
                        <td><input name="modifiedDate${i}" type="date" value="<fmt:formatDate pattern = 'yyyy-MM-dd' value = '${sales.modifiedDate}'/>" required="true"></td>
                    </tr>
                    <c:set var="i" value="${i+1}"/>
                </c:forEach>
            </table>
             <input  type="hidden" name="hiddenField" value="csvToDb">
             <input type="submit" value="Submit">
             </form>
        </c:if>
             <c:if test="${requestScope.map.readData eq 'finalPage'}">
                 <h1>Number of Rows Inserted is ${noOfRows}</h1>
             </c:if>
    </body>
</html>
