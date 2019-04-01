package tech.bts.cardgame.examples;

import com.mongodb.client.*;
import org.bson.Document;

import static tech.bts.cardgame.util.database.MongoUtil.doc;

public class MongoExample {

    public static void main(String... args) {

        // Create a client and connect to the server (by default, local server)
        MongoClient mongoClient = MongoClients.create();

        // Get the database
        MongoDatabase database = mongoClient.getDatabase("test");

        // Get a collection (table)
        MongoCollection<Document> productsCol = database.getCollection("products");

        /*
        // { name: "bike", price: 100.0, available: true }
        Document bike = new Document()
                .append("name", "bike")
                .append("price", 100.0)
                .append("available", true);

        productsCol.insertOne(bike);
        */

        /*
        // { name: "tv" }
        //Document query = new Document("name", "tv"); // when updating, usually find by ID
        Document query = new Document("_id", new ObjectId("5ca1c7d9e074536685ede458"));

        // { $set: { price: 200 } }
        Document updates = new Document("$set", new Document("price", 175.0));

        productsCol.updateOne(query, updates);
        */

        // query only products with price greater than 150
        // { price: { $gt: 150 } }
        Document query = doc("price", doc("$gt", 150.0));

        MongoCursor<Document> productsCursor = productsCol.find(query).iterator();

        while (productsCursor.hasNext()) {
            Document product = productsCursor.next();
            String name = product.getString("name");
            Double price = product.getDouble("price");
            Boolean available = product.getBoolean("available");

            System.out.println(name + " " + price + " " + available);
        }

        productsCursor.close();
    }

}
