package json;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Andrii_Rodionov on 1/3/2017.
 */
public class JsonObject extends Json {

    private ArrayList<JsonPair> pairs;

    public JsonObject(JsonPair... jsonPairs) {
        // ToDo rewrite as HashMap
        pairs = new ArrayList<JsonPair>();
        for(JsonPair jp : jsonPairs){
            pairs.add(jp);
        }

    }

    @Override
    public String toJson() {
        if (pairs.isEmpty()){
            return "{}";
        }
        else {
            StringBuilder sb = new StringBuilder("{");
            int countComas = 0;
            for(JsonPair jp : pairs){
                sb.append(jp.toString());
                if(countComas < pairs.size() - 1){
                    sb.append(", ");
                    countComas++;
                }
            }
            sb.append('}');
            return sb.toString();

        }

    }

    public void add(JsonPair jsonPair) {
        pairs.add(jsonPair);

    }

    public Json find(String name) {

        Json res = new JsonObject();
        for(JsonPair jp : pairs){
            if (jp.toString().toString().contains(name)){
                return jp.value;
            }
        }
        return null;
    }

    public JsonObject projection(String... names) {
        ArrayList<JsonPair> jpairs = new ArrayList<JsonPair>();
        JsonObject newJObj = new JsonObject();

        for(String name : names){
            Json val = find(name);

            if(val != null){
                JsonPair jp = new JsonPair(name, val);
                jpairs.add(jp);
            }

        }
        for(JsonPair jp: jpairs){
            newJObj.add(jp);
        }
        return newJObj;
    }
}
