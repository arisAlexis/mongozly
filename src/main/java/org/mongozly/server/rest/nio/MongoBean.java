package org.mongozly.server.rest.nio;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.mongodb.MongoClient;
import java.net.UnknownHostException;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
/**
 * Singleton class that provides a mongo connection that is pooled by default and should not be instantiated
 *
 * @author aris
 */
public class MongoBean {
    
    private static MongoBean instance=new MongoBean();
    private String dbName="mongozly"; //default
    
    private MongoClient mongoClient;
    private Morphia morphia;
    
    MongoBean() {
        try {
            mongoClient=new MongoClient();
            System.out.println("connected to mongo");
        } catch (UnknownHostException ex) {
            ex.printStackTrace();
        }
        morphia = new Morphia();
        morphia.map(User.class);
        Datastore ds = morphia.createDatastore(mongoClient, dbName);
        ds.ensureIndexes();
    }
    
    public void setDbName(String dbName) {
        this.dbName=dbName;
    }
    public static MongoBean getInstance() {       
        return instance;
    }
    
    public MongoClient getConnection() {
        return mongoClient;
    }
        
    public Morphia getMorphia() {
        return morphia;
    }
}
