package com.me.controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.client.MongoCursor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import java.util.ArrayList;
import java.util.Arrays;
import org.bson.Document;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 *
 * @author sgopalakrishna
 */
public class DataController extends AbstractController {

    public DataController() {
    }

    protected ModelAndView handleRequestInternal(
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        if (request.getRequestURI().endsWith("search.htm")) {
            BufferedReader reader = null;
            try {
                MongoClient mongoClient = new MongoClient();
                //2. select db
                MongoDatabase db = mongoClient.getDatabase("accessdb");
                //select collection
                db.getCollection("access").drop();
                MongoCollection<Document> collection = db.getCollection("access");
                // Reads the input file
                reader = new BufferedReader(new FileReader("/Users/sgopalakrishna/Documents/BigDataFiles/access.txt"));
                String currentLine;
                while ((currentLine = reader.readLine()) != null) {
                    String[] words = currentLine.split("\\s");
                    if (words.length == 10 || words.length == 9) {
                        Document doc = new Document("ip", words[0])
                                .append("date", words[3].replaceAll("[\\[]", ""))
                                .append("zone", words[4].replaceAll("[\\]]", ""))
                                .append("method", words[5].replaceAll("[\"]", ""))
                                .append("filename", words[6])
                                .append("protocol", words[7].replaceAll("[\"]", ""))
                                .append("errorcode", words[8]);
                        //5. Save doc
                        collection.insertOne(doc);
                    }
                }
            } catch (Exception e) {
                System.err.println("Error: " + e.getMessage());
            } finally {
                try {
                    reader.close();
                } catch (IOException e) {
                    System.out.println("File cannot be closed " + e.getMessage());
                }
            }
            return new ModelAndView("search");
        } else if (request.getRequestURI().endsWith("results.htm")) {
            MongoClient mongoClient = new MongoClient();
            MongoDatabase db = mongoClient.getDatabase("accessdb");
            MongoCollection collection = db.getCollection("access");
            ArrayList<JSONObject> str = new ArrayList();
            if (request.getParameter("searchoption").equals("ip")) {
                String data = request.getParameter("data");
                BasicDBObject query = new BasicDBObject();
                query.put("ip", data);
                MongoCursor<Document> cursor = collection.find(query).iterator();
                while (cursor.hasNext()) {
                    JSONParser parser = new JSONParser();
                    JSONObject json = (JSONObject) parser.parse(cursor.next().toJson());
                    str.add(json);
                }
                return new ModelAndView("results", "str", str);
            } else {
                String data = request.getParameter("data");
                BasicDBObject query = new BasicDBObject();
                query.put("errorcode", data);
                MongoCursor<Document> cursor = collection.find(query).iterator();
                while (cursor.hasNext()) {
                    JSONParser parser = new JSONParser();
                    JSONObject json = (JSONObject) parser.parse(cursor.next().toJson());
                    str.add(json);
                }
                return new ModelAndView("results", "str", str);
            }
        }
        return null;
    }
}
