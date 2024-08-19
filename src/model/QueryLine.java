package model;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class QueryLine {

    Map<String, String> relation = new HashMap<>();

    String tableName;

    public QueryLine(String tableName) {
        this.tableName = tableName;
    }

    public void setAttribute(String column, String value) {
        relation.put(column, value);
    }

    public String getInsertQuery() {

        StringBuilder query = new StringBuilder();

        StringBuilder insert = new StringBuilder("INSERT INTO ");
        insert.append(tableName);
        insert.append("(");
        
        StringBuilder values = new StringBuilder("VALUES");
        values.append("(");


        for(Entry<String, String> entry : relation.entrySet()) {
            insert.append(entry.getKey());
            insert.append(",");
            insert.append(" ");

            values.append(entry.getValue());
            values.append(",");
            values.append(" ");
        }

        insert.delete(insert.length()-2, insert.length()-1);
        values.delete(values.length()-2, values.length()-1);

        insert.append(")");
        values.append(")");

        query.append(insert);
        query.append(" ");
        query.append(values);

        return query.toString();
    }

}
