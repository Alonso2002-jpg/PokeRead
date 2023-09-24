package org.develop.services;


import org.apache.ibatis.jdbc.ScriptRunner;

import javax.xml.crypto.Data;
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseManager {
    private static DatabaseManager instance;
    Connection connection;
    private PreparedStatement preparedStatement;
    private String serverUrl;
    private String serverPort;
    private String dataBaseName;

    private String conURL;
    private String jdbcDriver;


    private DatabaseManager(){
        try {
            configFromProperties();
            openConnection();
            System.out.println("Successfully");
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static DatabaseManager getInstance(){
        if (instance==null){
            instance=new DatabaseManager();
        }
        return instance;
    }

    private void configFromProperties(){
        Properties properties = new Properties();
        serverUrl= properties.getProperty("database.url","jdbc:sqlite");
        serverPort = properties.getProperty("database.port","3306");
        dataBaseName = properties.getProperty("database.name","Pokemon");
        jdbcDriver = properties.getProperty("database.driver","org.sqlite.JDBC");
        conURL =properties.getProperty("database.connectionUrl", serverUrl + ":"+dataBaseName+".db");
    }

    public Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()){
            try{
                openConnection();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        return connection;
    }

    private void openConnection() throws SQLException{
        connection = DriverManager.getConnection(conURL);
    }

    public void closeConnection() throws SQLException{
        if (preparedStatement != null){ preparedStatement.close();}
        connection.close();
    }

    public void executeScript(String script, boolean logWriter) throws FileNotFoundException{
        ScriptRunner runner = new ScriptRunner(connection);
        var file = ClassLoader.getSystemResource(script).getFile();
        Reader reader = new BufferedReader(new FileReader(file));
        runner.setLogWriter(logWriter ? new PrintWriter(System.out):null);
        runner.runScript(reader);
    }


}