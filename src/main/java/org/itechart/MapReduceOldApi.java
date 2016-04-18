package org.itechart;

import com.mongodb.*;

import java.util.HashMap;

public class MapReduceOldApi {
    public static void main(String[] args) {
        MongoClient mongoClient = new MongoClient();
        DB db = mongoClient.getDB("warehouse");
        DBCollection collection = db.getCollection("company");

        String map = "function () {" +
                "emit(monthNames[this.date.getMonth()], 1);" +
                "}";

        String reduce = "function (key, value) {" +
                "return Array.sum(value);" +
                "}";

        MapReduceCommand cmd = new MapReduceCommand(collection, map, reduce,
                "output", MapReduceCommand.OutputType.REPLACE, null);
        HashMap<String, Object> scope = new HashMap<>();

        scope.put("monthNames", new String[]{"J", "F", "Mr", "A", "My", "Jn", "Jl", "A", "S", "O", "N"});

        cmd.setScope(scope);

        MapReduceOutput out = collection.mapReduce(cmd);
    }
}
