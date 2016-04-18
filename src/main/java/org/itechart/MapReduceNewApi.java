package org.itechart;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class MapReduceNewApi {
    public static void main(String[] args) {
        MongoClient mongoClient = new MongoClient();
        MongoDatabase warehouse = mongoClient.getDatabase("warehouse");
        MongoCollection<Document> company = warehouse.getCollection("company");

        String map = "function () {" +
                "emit(monthNames[this.date.getMonth()], 1);" +
                "}";

        String reduce = "function (key, value) {" +
                "return Array.sum(value);" +
                "}";

        company.mapReduce(map, reduce).
                scope(new Document("monthNames", new String[]{"J", "F", "Mr", "A", "My", "Jn", "Jl", "A", "S", "O", "N"}));
    }
}
