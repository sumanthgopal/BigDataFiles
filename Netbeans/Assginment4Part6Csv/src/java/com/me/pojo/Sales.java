/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.me.pojo;

import java.util.Date;

/**
 *
 * @author Sumanth
 */
public class Sales {

    private int salesOrderID;
    private int revisionNumber;
    private Date orderDate;
    private Date dueDate;
    private Date shipDate;
    private int status;
    private int onlineOrderFlag;
    private String salesOrderNumber;
    private String purchaseOrderNumber;
    private String accountNumber;
    private int customerID;
    private int salesPersonID;
    private int territoryID;
    private int billToAddressID;
    private int shipToAddressID;
    private int shipMethodID;
    private int creditCardID;
    private int creditCardApprovalCode;
    private int currencyRateID;
    private float subTotal;
    private float taxAmt;
    private float freight;
    private float totalDue;
    private String comment;
    private Date modifiedDate;

    public int getSalesOrderID() {
        return salesOrderID;
    }

    public void setSalesOrderID(int salesOrderID) {
        this.salesOrderID = salesOrderID;
    }

    public int getRevisionNumber() {
        return revisionNumber;
    }

    public void setRevisionNumber(int revisionNumber) {
        this.revisionNumber = revisionNumber;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Date getShipDate() {
        return shipDate;
    }

    public void setShipDate(Date shipDate) {
        this.shipDate = shipDate;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getOnlineOrderFlag() {
        return onlineOrderFlag;
    }

    public void setOnlineOrderFlag(int onlineOrderFlag) {
        this.onlineOrderFlag = onlineOrderFlag;
    }

    public String getSalesOrderNumber() {
        return salesOrderNumber;
    }

    public void setSalesOrderNumber(String salesOrderNumber) {
        this.salesOrderNumber = salesOrderNumber;
    }

    public String getPurchaseOrderNumber() {
        return purchaseOrderNumber;
    }

    public void setPurchaseOrderNumber(String purchaseOrderNumber) {
        this.purchaseOrderNumber = purchaseOrderNumber;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public int getSalesPersonID() {
        return salesPersonID;
    }

    public void setSalesPersonID(int salesPersonID) {
        this.salesPersonID = salesPersonID;
    }

    public int getTerritoryID() {
        return territoryID;
    }

    public void setTerritoryID(int territoryID) {
        this.territoryID = territoryID;
    }

    public int getBillToAddressID() {
        return billToAddressID;
    }

    public void setBillToAddressID(int billToAddressID) {
        this.billToAddressID = billToAddressID;
    }

    public int getShipToAddressID() {
        return shipToAddressID;
    }

    public void setShipToAddressID(int shipToAddressID) {
        this.shipToAddressID = shipToAddressID;
    }

    public int getShipMethodID() {
        return shipMethodID;
    }

    public void setShipMethodID(int shipMethodID) {
        this.shipMethodID = shipMethodID;
    }

    public int getCreditCardID() {
        return creditCardID;
    }

    public void setCreditCardID(int creditCardID) {
        this.creditCardID = creditCardID;
    }

    public int getCreditCardApprovalCode() {
        return creditCardApprovalCode;
    }

    public void setCreditCardApprovalCode(int creditCardApprovalCode) {
        this.creditCardApprovalCode = creditCardApprovalCode;
    }

    public int getCurrencyRateID() {
        return currencyRateID;
    }

    public void setCurrencyRateID(int currencyRateID) {
        this.currencyRateID = currencyRateID;
    }

    public float getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(float subTotal) {
        this.subTotal = subTotal;
    }

    public float getTaxAmt() {
        return taxAmt;
    }

    public void setTaxAmt(float taxAmt) {
        this.taxAmt = taxAmt;
    }

    public float getFreight() {
        return freight;
    }

    public void setFreight(float freight) {
        this.freight = freight;
    }

    public float getTotalDue() {
        return totalDue;
    }

    public void setTotalDue(float totalDue) {
        this.totalDue = totalDue;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }


}
