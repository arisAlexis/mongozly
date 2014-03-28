package org.mongozly.server.rest.nio;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.util.JSON;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author aris
 */
public class UserDAOTest {
    
    static final String DBNAME="mongozly-test";
    
    static UserDAO userDAO;
    static DB db;
    static DBCollection col;
    static List<DBObject> testUsersList;
    
    
    public UserDAOTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
       MongoBean.getInstance();
       MongoBean.getInstance().setDbName(DBNAME);
       userDAO = new UserDAO(MongoBean.getInstance().getMorphia(), MongoBean.getInstance().getConnection(),DBNAME);
       db=MongoBean.getInstance().getConnection().getDB(DBNAME);
       col=db.getCollection("users");
       
       //populate once the test data into an array to increase speed of tests 
       testUsersList=new ArrayList<>();
       
       try (BufferedReader br = new BufferedReader(new FileReader("db/jsonTestData"))) {
            String line = br.readLine();

            while (line != null && !line.trim().equals("")) {
                DBObject dbObject = (DBObject) JSON.parse(line);
                testUsersList.add(dbObject);
                line = br.readLine();
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(UserDAOTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(UserDAOTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    /** 
     * Populate the database with some users before each test and delete them afterwards
     */
    public void setUp() throws Exception {
        for (DBObject o:testUsersList) 
            col.insert(o);
        assertEquals(col.getCount(),testUsersList.size());
    }
    
    @After
    /**
     * Delete completely the collection to avoid tests overlapping
     */
    public void tearDown() {
        col.remove(new BasicDBObject());
    }

    /**
     * Test of addUser method, of class UserDAO.
     */
    @Test
    public void testAddUser() {
        System.out.println("addUser");
        long originalCount=col.getCount();
        User user = new User();
        user.setUserName("fictionalUser");
        user.setEmail("fictionalEmail");
        
        boolean result = userDAO.addUser(user);
        assertTrue(result);
        assertEquals(col.getCount(),originalCount+1);
        
    }

    /**
     * Test of deleteUser method, of class UserDAO.
     */
    @Test
    public void testDeleteUser() {
        System.out.println("deleteUser");        
        
        String userName = "user1";
        userDAO.deleteUser(userName);
        BasicDBObject document = new BasicDBObject();
	    document.put("userName", userName);
        DBCursor cur=col.find(document);
        assertEquals(cur.count(),0);
        
    }

    /**
     * Test of findUser method, of class UserDAO.
     */
    @Test
    public void testFindUser() {
        System.out.println("findUser");
        String userName = "user1";
        User result = userDAO.findUser(userName);
        assertEquals(result.getUserName(), userName);
    }
    
}
