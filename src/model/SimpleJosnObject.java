package model;

import java.util.HashMap;
import java.util.Map;

public class SimpleJosnObject {
    private Map<String, Object> jsonMap;

    public SimpleJosnObject() {
        jsonMap = new HashMap<>();
    }

    public void put(String key, String value) {
        jsonMap.put(key, "\"" + value + "\"");
    }

    public void put(String key, int value) {
        jsonMap.put(key, value);
    }

    public void put(String key, boolean value) {
        jsonMap.put(key, value);
    }

    public void put(String key, SimpleJosnObject value) {
        jsonMap.put(key, value.toString());
    }

    public void put(String key, SimpleJsonArray value) {
        jsonMap.put(key, value.toString());
    }

    @Override
    public String toString() {
        StringBuilder jsonBuilder = new StringBuilder();
        jsonBuilder.append("{");

        for(String key: jsonMap.keySet()) {
            jsonBuilder.append("\"").append(key).append("\":").append(jsonMap.get(key)).append(",");
        }

        if (!jsonMap.isEmpty()) {
            jsonBuilder.deleteCharAt(jsonBuilder.length() -1);
        }

        jsonBuilder.append("}");

        return jsonBuilder.toString();
    }
}
