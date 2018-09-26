/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.me.dao;

import com.me.pojo.Sales;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Sumanth
 */
public class SalesDAO {

    public Map<String, Object> readCsvData(String fileName) {
        Map<String, Object> map = new HashMap();
        ArrayList<Sales> data = new ArrayList();
        Statement stmt = null;
        Connection conn = null;
        int count = 0;
        try {
            Class.forName("org.relique.jdbc.csv.CsvDriver");
            conn = DriverManager.getConnection("jdbc:relique:csv:E:\\NEUSemester2\\Web Tools");
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM " + fileName);
            while (rs.next()) {
                count++;
                Sales s = new Sales();
                s.setSalesOrderID(rs.getInt("SalesOrderID"));
                s.setRevisionNumber(rs.getInt("RevisionNumber"));
                s.setOrderDate(new Date(rs.getString("OrderDate")));
                s.setDueDate(new Date(rs.getString("DueDate")));
                s.setShipDate(new Date(rs.getString("ShipDate")));
                s.setStatus(rs.getInt("Status"));
                s.setOnlineOrderFlag(rs.getInt("OnlineOrderFlag"));
                s.setSalesOrderNumber(rs.getString("SalesOrderNumber"));
                s.setPurchaseOrderNumber(rs.getString("PurchaseOrderNumber"));
                s.setAccountNumber(rs.getString("AccountNumber"));
                s.setCustomerID(rs.getInt("CustomerID"));
                s.setSalesPersonID(rs.getInt("SalesPersonID"));
                s.setBillToAddressID(rs.getInt("BillToAddressID"));
                s.setShipToAddressID(rs.getInt("ShipToAddressID"));
                s.setShipMethodID(rs.getInt("ShipMethodID"));
                s.setCreditCardID(rs.getInt("CreditCardID"));
                s.setCreditCardApprovalCode(rs.getInt("CreditCardApprovalCode"));
                s.setCurrencyRateID(rs.getInt("CurrencyRateID"));
                s.setSubTotal(rs.getFloat("SubTotal"));
                s.setTaxAmt(rs.getFloat("TaxAmt"));
                s.setFreight(rs.getFloat("Freight"));
                s.setTotalDue(rs.getFloat("TotalDue"));
                s.setComment(rs.getString("Comment"));
                s.setModifiedDate(new Date(rs.getString("ModifiedDate")));
                data.add(s);
            }
            map.put("count", count);
            map.put("data", data);
        } catch (SQLException ex) {
            Logger.getLogger(SalesDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SalesDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(SalesDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return map;
    }

    public void addSales(Sales s) {
        Connection conn = null;
        PreparedStatement st = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/webtools", "root", "root");
            String query = "Insert into salesorder (SalesOrderID,RevisionNumber,OrderDate,DueDate,ShipDate,Status,OnlineOrderFlag,SalesOrderNumber,PurchaseOrderNumber,AccountNumber,CustomerID,SalesPersonID,TerritoryID,BillToAddressID,ShipToAddressID,ShipMethodID,CreditCardID,CreditCardApprovalCode,CurrencyRateID,SubTotal,TaxAmt,Freight,TotalDue,Comment,ModifiedDate) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            st = conn.prepareStatement(query);
            st.setInt(1, s.getSalesOrderID());
            st.setInt(2, s.getRevisionNumber());
            st.setDate(3, new java.sql.Date (s.getOrderDate().getTime()));
            st.setDate(4, new java.sql.Date (s.getDueDate().getTime()));
            st.setDate(5, new java.sql.Date ( s.getShipDate().getTime()));
            st.setInt(6, s.getStatus());
            st.setInt(7, s.getOnlineOrderFlag());
            st.setString(8, s.getSalesOrderNumber());
            st.setString(9, s.getPurchaseOrderNumber());
            st.setString(10, s.getAccountNumber());
            st.setInt(11, s.getCustomerID());
            st.setInt(12, s.getSalesPersonID());
            st.setInt(13, s.getTerritoryID());
            st.setInt(14, s.getBillToAddressID());
            st.setInt(15, s.getShipToAddressID());
            st.setInt(16, s.getShipMethodID());
            st.setInt(17, s.getCreditCardID());
            st.setInt(18, s.getCreditCardApprovalCode());
            st.setInt(19, s.getCurrencyRateID());
            st.setFloat(20, s.getSubTotal());
            st.setFloat(21, s.getTaxAmt());
            st.setFloat(22, s.getFreight());
            st.setFloat(23, s.getTotalDue());
            st.setString(24, s.getComment());
            st.setDate(25, new java.sql.Date ( s.getModifiedDate().getTime()));
            st.execute();
        } catch (ClassNotFoundException ex) {
            System.out.println("Class Not Found " + ex.getMessage());
        } catch (SQLException ex) {
            System.out.println("SQL Exception " + ex.getMessage());
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
                if (st != null) {
                    st.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(SalesDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
