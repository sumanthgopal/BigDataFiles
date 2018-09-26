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
            BufferedReader movies = null;
            BufferedReader ratings = null;
            BufferedReader tags = null;
            try {
                MongoClient mongoClient = new MongoClient();
                //2. select db
                MongoDatabase db = mongoClient.getDatabase("movielens");
                //select collection
                db.getCollection("movies").drop();
                db.getCollection("ratings").drop();
                db.getCollection("tags").drop();
                MongoCollection<Document> movieCollection = db.getCollection("movies");
                MongoCollection<Document> ratingsCollection = db.getCollection("ratings");
                MongoCollection<Document> tagsCollection = db.getCollection("tags");
                // Reads the input file
                movies = new BufferedReader(new FileReader("/Users/sgopalakrishna/Documents/BigDataFiles/movies.dat"));
                ratings = new BufferedReader(new FileReader("/Users/sgopalakrishna/Downloads/ratings.dat"));
                tags = new BufferedReader(new FileReader("/Users/sgopalakrishna/Documents/BigDataFiles/tags.dat"));
                String currentLine;
                while ((currentLine = ratings.readLine()) != null) {
                    String[] words = currentLine.split("(::)");
                    Document doc = new Document("userid", words[0])
                            .append("movieid", words[1])
                            .append("rating", words[2])
                            .append("timestamp", words[3]);
                    //5. Save doc
                    ratingsCollection.insertOne(doc);
                }
                currentLine = null;
                while ((currentLine = movies.readLine()) != null) {
                    String[] words = currentLine.split("(::)");
                        Document doc = new Document("title", words[1])
                                .append("genre", words[2])
                                .append("movieid", words[0]);
                        movieCollection.insertOne(doc);
                }
                currentLine = null;
                while ((currentLine = tags.readLine()) != null) {
                    String[] words = currentLine.split("(::)");
                    Document doc = new Document("userid", words[0])
                            .append("movieid", words[1])
                            .append("tag", words[2])
                            .append("timestamp", words[3]);
                    //5. Save doc
                    tagsCollection.insertOne(doc);
                }
            } catch (Exception e) {
                System.err.println("Error: " + e.getMessage());
            } finally {
                try {
                    movies.close();
                    ratings.close();
                    tags.close();
                } catch (IOException e) {
                    System.out.println("File cannot be closed " + e.getMessage());
                }
            }
            return new ModelAndView("search");
        } else if (request.getRequestURI().endsWith("results.htm")) {
            MongoClient mongoClient = new MongoClient();
            MongoDatabase db = mongoClient.getDatabase("movielens");
            MongoCollection collection = db.getCollection("movies");
            ArrayList<JSONObject> str = new ArrayList();
            if (request.getParameter("searchoption").equals("title")) {
                String data = request.getParameter("data");
                BasicDBObject query = new BasicDBObject();
                query.put("title", data);
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
                query.put("genre", data);
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
