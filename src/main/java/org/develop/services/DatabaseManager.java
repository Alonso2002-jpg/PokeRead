package org.develop.services;


import org.apache.ibatis.jdbc.ScriptRunner;

import javax.xml.crypto.Data;
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;
/**
 * Esta clase gestiona la conexion y las operaciones con la base de datos. Proporciona metodos para establecer
 * la conexion, ejecutar consultas y realizar tareas relacionadas con la base de datos.
 */
public class DatabaseManager {
    private static DatabaseManager instance;
    private Connection connection;
    private PreparedStatement preparedStatement;
    private String serverUrl;
    private String dataBaseName;
    private boolean chargeTables;
    private String conURL;
    private String initScript;


    /**
     * Constructor privado utilizado para inicializar una instancia de DatabaseManager. Este constructor configura
     * la conexion a la base de datos, abre la conexion y, opcionalmente, carga tablas iniciales si se establece la
     * propiedad "chargeTables" como verdadera en la configuracion.
     *
     * @throws SQLException Si se produce un error en la conexion a la base de datos.
     * @throws FileNotFoundException Si no se encuentra el archivo de propiedades de configuracion.
     * @throws IOException Si se produce un error al leer el archivo de propiedades de configuracion.
     */
    private DatabaseManager(){
        try {
            configFromProperties();
            openConnection();
            if (chargeTables){
                executeScript(initScript,true);
            }
            System.out.println("Successfully");
        }catch (SQLException | FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Obtiene una instancia unica de DatabaseManager. Si aun no existe una instancia, se crea y se configura una
     * nueva conexion a la base de datos.
     *
     * @return La instancia unica de DatabaseManager.
     */
    public static DatabaseManager getInstance(){
        if (instance==null){
            instance=new DatabaseManager();
        }
        return instance;
    }

    /**
     * Obtiene una conexion a la base de datos. Si la conexion no existe o esta cerrada, se intenta abrir una nueva
     * conexion.
     *
     * @return Una conexion a la base de datos.
     * @throws SQLException Si se produce un error al abrir la conexion a la base de datos.
     */
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

    /**
     * Abre una conexion a la base de datos utilizando la URL de conexion configurada.
     *
     * @throws SQLException Si se produce un error al abrir la conexion a la base de datos.
     */
    private void openConnection() throws SQLException{
        connection = DriverManager.getConnection(conURL);
    }

    /**
     * Cierra la conexion a la base de datos y el PreparedStatement asociado (si existe).
     *
     * @throws SQLException Si se produce un error al cerrar la conexion o el PreparedStatement.
     */
    public void closeConnection() throws SQLException{
        if (preparedStatement != null){ preparedStatement.close();}
        connection.close();
    }

    /**
     * Lee la configuracion de la base de datos desde un archivo de propiedades y configura las propiedades necesarias
     * para la conexion a la base de datos.
     *
     * El archivo de propiedades debe incluir las siguientes claves (con valores predeterminados entre parentesis):
     * - "database.url" (jdbc:sqlite): URL del servidor de la base de datos.
     * - "database.name" (Pokemon): Nombre de la base de datos.
     * - "database.initDatabase" (false): Indica si se debe cargar un script de inicializacion de la base de datos.
     * - "database.connectionUrl": URL de conexion final de la base de datos.
     * - "database.initScript" (init.sql): Ruta del script de inicializacion de la base de datos.
     *
     * @throws RuntimeException Si se produce un error al leer o cargar el archivo de propiedades.
     */
        private void configFromProperties(){
        try{
        Properties properties = new Properties();
        properties.load(DatabaseManager.class.getClassLoader().getResourceAsStream("config.properties"));

        serverUrl= properties.getProperty("database.url","jdbc:sqlite");
        dataBaseName = properties.getProperty("database.name","Pokemon");
        chargeTables=Boolean.parseBoolean(properties.getProperty("database.initDatabase","false"));
        conURL =properties.getProperty("database.connectionUrl", serverUrl + ":"+dataBaseName + ".db");
        System.out.println(conURL);
        initScript=properties.getProperty("database.initScript","init.sql");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Ejecuta un script SQL en la base de datos actualmente conectada.
     *
     * @param script    El nombre del archivo de script SQL que se debe ejecutar.
     * @param logWriter Indica si se debe escribir la salida del script en el registro (true) o no (false).
     * @throws IOException Si se produce un error al leer el archivo de script.
     * @throws SQLException Si se produce un error al ejecutar el script SQL.
     * @throws FileNotFoundException Si el archivo de script especificado no se encuentra.
     */
    public void executeScript(String script, boolean logWriter) throws IOException, SQLException {
        ScriptRunner runner = new ScriptRunner(connection);
        InputStream inputStream = ClassLoader.getSystemResourceAsStream(script);
        if (inputStream != null) {
            InputStreamReader reader = new InputStreamReader(inputStream);
            runner.setLogWriter(logWriter ? new PrintWriter(System.out) : null);
            runner.runScript(reader);
        } else {
            throw new FileNotFoundException("Script not found: " + script);
        }
    }


}