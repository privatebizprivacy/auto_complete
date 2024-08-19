package model;

import java.util.ArrayList;
import java.util.List;

public class SimpleJsonArray {
    private List<Object> jsonArray;

    public SimpleJsonArray(){
        jsonArray = new ArrayList<>();
    }
    
    public void add(String value) {
        jsonArray.add("\"" + value +"\"");  //문자열 값을 "로 감싸기
    }

    public void add(int value) {
        jsonArray.add(value);
    }

    public void add(boolean value) {
        jsonArray.add(value);
    }

    public void add(SimpleJosnObject value) {
        jsonArray.add(value.toString());
    }

    public void add(SimpleJsonArray value) {
        jsonArray.add(value.toString());
    }

    @Override
    public String toString() {
        StringBuilder jsonBuilder = new StringBuilder();
        jsonBuilder.append("[");

        for(Object value : jsonArray) {
            jsonBuilder.append(value).append(",");
        }

        if(!jsonArray.isEmpty()) {
            jsonBuilder.deleteCharAt(jsonBuilder.length() - 1);
        }

        jsonBuilder.append("]");
        return jsonBuilder.toString();
    }
    
}
