/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package java_mongo_crud;

import com.mongodb.*;
import java.net.UnknownHostException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author DC
 */
public class DbConfig {

    private static MongoClient mongo;
    private static DB db;
    private static DBCollection collection;

    public static void init() {
        try {
            mongo = new MongoClient("localhost",27017);
            db = mongo.getDB("java_mogno_crud");
            collection = db.getCollection("fruits");
            System.out.println("database connected");
        } catch (Exception e) {
            System.out.println("datababse not connected");
            System.out.println(e);
        }
    }

    private static FruitsModel CreateFruitRecord(int id, String name, int price, boolean avail) {
        FruitsModel fr = new FruitsModel();
        fr.setId(id);
        fr.setName(name);
        fr.setPrice(price);
        fr.setAailability(avail);
        return fr;
    }

    public static DBObject createBSONObject(FruitsModel fruit) {
        BasicDBObjectBuilder docBuild = BasicDBObjectBuilder.start();
        docBuild.append("_id", fruit.getId());
        docBuild.append("name", fruit.getName());
        docBuild.append("availability", fruit.getAvailability());
        docBuild.append("price", fruit.getPrice());
        return docBuild.get();
    }

    public static void insertRecord(int id, String name, int price, boolean avail) {
        FruitsModel fruit = CreateFruitRecord(id, name, price, avail);
        DBObject doc = createBSONObject(fruit);
        WriteResult result = collection.insert(doc);
    }

    public static void ShowAllRecords() {
        DBCursor cursor = collection.find();
        while (cursor.hasNext()) {
            System.out.println(cursor.next());
        }
    }

    public static void FetchRecordById(int id) {
        DBObject query = BasicDBObjectBuilder.start().add("_id", id).get();
        DBCursor cursor = collection.find(query);
        if (cursor.hasNext() == false) {
            System.out.println("no records found");
        } else {
            System.out.println("Record found !!!");
            while (cursor.hasNext()) {
                System.out.println(cursor.next());
            }
        }
    }

    public static void DeleteRecordById(int id) {
        DBObject query = BasicDBObjectBuilder.start().add("_id", id).get();
        WriteResult result = collection.remove(query);
        if (result.getN() == 1) {
            System.out.println("Record Deleted...");
        } else {
            System.out.println("Record doesn't Exist");
        }
    }

    public static void UpdateRecordById(int id, String name, int price, boolean availability) {
        DBObject idQuery = BasicDBObjectBuilder.start().add("_id", id).get();
        DBObject updateParams = BasicDBObjectBuilder.start().add("name", name).add("price", price).add("availability", availability).get();
        WriteResult result = collection.update(idQuery, updateParams);
        if (result.getN() == 1) {
            System.out.println("Record updated");
        } else {
            System.out.println("Record doesn't exist");
        }
    }
}
