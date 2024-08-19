package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;


/**
 * DataBase
 */
public class DataBase {

    private static final Logger log = Logger.getLogger(DataBase.class.getName());

    Map<String, String> database;

    public DataBase() {
        this.database = new HashMap<>();
    }

    public List<String> selectAll() {
        return new ArrayList<>(Arrays.asList(this.database.values().toArray(new String[0])));
    }

    public static void main(String[] args) throws Exception {
    }
}