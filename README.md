# Proyecto Pokemon - Java con SQLite
***
Este proyecto es una aplicación simple en Java que utiliza SQLite como base de datos. A continuación, se describen los pasos para configurar y ejecutar el proyecto.
## Requisitos
***
* Java 8 o superior
* Gradle
## Configuración
***

### Paso 1: Dependencias de Gradle
Agrega las siguientes dependencias a tu archivo `build.gradle`:

```kotlin
plugins {
    id("java")
}

group = "org.develop"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    // https://mvnrepository.com/artifact/com.google.code.gson/gson
    implementation("com.google.code.gson:gson:2.10.1")
    // https://mvnrepository.com/artifact/org.projectlombok/lombok
    implementation("org.projectlombok:lombok:1.18.30")
    annotationProcessor("org.projectlombok:lombok:1.18.30")
    // Logger
    implementation("ch.qos.logback:logback-classic:1.4.11")
    // https://mvnrepository.com/artifact/org.xerial/sqlite-jdbc
    implementation("org.xerial:sqlite-jdbc:3.43.0.0")
    // https://mvnrepository.com/artifact/com.opencsv/opencsv
    implementation("com.opencsv:opencsv:5.8")
        // Ibatis lo usaremos para leer los scripts SQL desde archivos
    implementation("org.mybatis:mybatis:3.5.13")
}

tasks.test {
    useJUnitPlatform()
}
```

## Model
***
### Paso 2: Crear la clase PrevEvolution
Crea una clase `PrevEvolution` con los atributos `num` y `name`.

```java
package org.develop.model;

import lombok.Data;

@Data
public class PrevEvolution{
    private String num;
    private String name;
}
```

### Paso 3: Crear la clase NextEvolution
Crea una clase `NextEvolution` con los atributos `num`, `name`, con sus `getters and setters`.

```java
package org.develop.model;

import lombok.Data;

@Data
public class NextEvolution{
    private String num;
    private String name;

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
```

### Paso 4: Crear la clase Pokemon
Crea una clase `Pokemon` con los atributos `num`, `name`,`img`, `type`,`height`, `weight`,`candy`, `candy_count`,`egg`, `spawn_chance`,`avg_spawns`, `spawn_time`,`multipliers`, `weaknesses`,`next_evolution`, `prev_evolution`, con sus `getters`, `setters` y `toString`. Ademas implementamos un metodo `parseWight` y otro `parseHeight`, estos dos metodos nos ayudara a transformar un formato `String` a un `Double`.

```java
package org.develop.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Data

public class Pokemon{

    private int id;
    private String num;
    private String name;
    private String img;
    private ArrayList<String> type;
    private String height;
    private String weight;
    private String candy;
    private int candy_count;
    private String egg;
    private double spawn_chance;
    private double avg_spawns;
    private String spawn_time;
    private ArrayList<Double> multipliers;
    private ArrayList<String> weaknesses;
    private ArrayList<NextEvolution> next_evolution;
    private ArrayList<PrevEvolution> prev_evolution;

 public int getId() {
  return id;
 }

 public void setId(int id) {
  this.id = id;
 }

 public String getNum() {
  return num;
 }

 public void setNum(String num) {
  this.num = num;
 }

 public String getName() {
  return name;
 }

 public void setName(String name) {
  this.name = name;
 }

 public String getImg() {
  return img;
 }

 public void setImg(String img) {
  this.img = img;
 }

 public ArrayList<String> getType() {
  return type;
 }

 public void setType(ArrayList<String> type) {
  this.type = type;
 }

 public String getHeight() {
  return height;
 }

 public void setHeight(String height) {
  this.height = height;
 }

 public String getWeight() {
  return weight;
 }

 public void setWeight(String weight) {
  this.weight = weight;
 }

 public String getCandy() {
  return candy;
 }

 public void setCandy(String candy) {
  this.candy = candy;
 }

 public int getCandy_count() {
  return candy_count;
 }

 public void setCandy_count(int candy_count) {
  this.candy_count = candy_count;
 }

 public String getEgg() {
  return egg;
 }

 public void setEgg(String egg) {
  this.egg = egg;
 }

 public double getSpawn_chance() {
  return spawn_chance;
 }

 public void setSpawn_chance(double spawn_chance) {
  this.spawn_chance = spawn_chance;
 }

 public double getAvg_spawns() {
  return avg_spawns;
 }

 public void setAvg_spawns(double avg_spawns) {
  this.avg_spawns = avg_spawns;
 }

 public String getSpawn_time() {
  return spawn_time;
 }

 public void setSpawn_time(String spawn_time) {
  this.spawn_time = spawn_time;
 }

 public ArrayList<Double> getMultipliers() {
  return multipliers;
 }

 public void setMultipliers(ArrayList<Double> multipliers) {
  this.multipliers = multipliers;
 }

 public ArrayList<String> getWeaknesses() {
  return weaknesses;
 }

 public void setWeaknesses(ArrayList<String> weaknesses) {
  this.weaknesses = weaknesses;
 }

 public ArrayList<NextEvolution> getNext_evolution() {
  return next_evolution;
 }

 public void setNext_evolution(ArrayList<NextEvolution> next_evolution) {
  this.next_evolution = next_evolution;
 }

 public ArrayList<PrevEvolution> getPrev_evolution() {
  return prev_evolution;
 }

 public void setPrev_evolution(ArrayList<PrevEvolution> prev_evolution) {
  this.prev_evolution = prev_evolution;
 }
 
 public double parseWeight(){
   String medidas[]=weight.split(" ");
   return Double.parseDouble(medidas[0]);
 }

 
 public double parseHeight(){
   String medidas[]=height.split(" ");
   return Double.parseDouble(medidas[0]);
 }


 @Override
    public String toString() {
        return "Pokemon{" +
                "id=" + id +
                ", num='" + num + '\'' +
                ", name='" + name + '\'' +
                ", img='" + img + '\'' +
                ", type=" + type +
                ", height='" + height + '\'' +
                ", weight='" + weight + '\'' +
                ", candy='" + candy + '\'' +
                ", candy_count=" + candy_count +
                ", egg='" + egg + '\'' +
                ", spawn_chance=" + spawn_chance +
                ", avg_spawns=" + avg_spawns +
                ", spawn_time='" + spawn_time + '\'' +
                ", multipliers=" + multipliers +
                ", weaknesses=" + weaknesses +
                ", next_evolution=" + next_evolution +
                ", prev_evolution=" + prev_evolution +
                '}';
    }
}

```

### Paso 5: Crear la clase Pokedex

Crea una clase `Pokedex` con un atributo `pokemon` que sera de tipo `ArrayList<Pokemon>`  .

```java
package org.develop.model;

import lombok.Data;

import java.util.ArrayList;

@Data
public class Pokedex {
    public ArrayList<Pokemon> pokemon;
}
```

## Controllers
***
### Paso 6: Crear la clase PokemonControllers
Crea una clase `PokemonControllers` proporciona una interfaz para interactuar con un `Pokedex` de `Pokemon` y realizar diversas consultas y cálculos relacionados con los `Pokemon` almacenados en ella. El metodo principal de esta clase es el `cargarPokedex` que se encarga de leer el archivo `pokemon.json` y almacenar todos los datos en la `Pokedex`, este metodo se ejecutara siempre que se obtenga la instancia del `PokemonControllers`.

```java
package org.develop.controllers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.develop.model.NextEvolution;
import org.develop.model.Pokedex;
import org.develop.model.Pokemon;

import java.io.File;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;

public class PokemonController {
    private static PokemonController instance;
    private Pokedex pokedex;
    
    //Constructor privado
    private PokemonController() {
        cargarPokedex();
    }

    // Instancia
    public static PokemonController getInstance() {
        if (instance == null) {
            instance = new PokemonController();
        }
        return instance;
    }

    public static void closeInstance() {
        instance=null;
    }
    
    //Lee fichero JSON y carga los datos al Pokedex
    public void cargarPokedex() {
        Path rutaRelativa = Paths.get("");
        String rutaAbsoluta = rutaRelativa.toAbsolutePath().toString();
        String rutaData = rutaAbsoluta + File.separator + "data";
        String rutaPokemon = rutaData + File.separator + "pokemon.json";

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        // Actualizar a try-with-resources
        try (Reader reader = Files.newBufferedReader(Paths.get(rutaPokemon))) {
            this.pokedex = gson.fromJson(reader, new TypeToken<Pokedex>() {}.getType());
            System.out.println("Pokedex loaded! There are: " + pokedex.pokemon.size());
        } catch (Exception e) {
            System.out.println("Error loading Pokedex!");
            System.out.println("Error: "); e.printStackTrace();
        }
    }

    public ArrayList<Pokemon> obtenerPokemons(){
        return this.pokedex.pokemon;
    }

    public Pokemon getPokemonXId(int id){
        return this.pokedex.pokemon.get(id);
    }

    //Obtiene los primeros 10 Pokemons de la Pokedex
    public List<String> obtenerNom10Primeros(){
        List<String> list = obtenerPokemons()
                .stream()
                .limit(10)
                .map(Pokemon::getName)
                .toList();

        return list;
    }
 
    //Obtiene los ultimos 5 Pokemos de la Pokedex
    public List<String> obtenerNom5Ultimos(){
         List<String> list = obtenerPokemons()
                .stream()
                 .skip(pokedex.pokemon.size()-5)
                 .map(Pokemon::getName)
                 .toList();

         return list;
    }

    //Obtiene el Pokemon por su nombre
    public Optional<Pokemon> getPokemonXName(String name){
        Optional<Pokemon> pokemon = obtenerPokemons()
                .stream()
                .filter(pk -> name.equalsIgnoreCase(pk.getName()))
                .findFirst();

        return pokemon.isPresent()?pokemon:Optional.of(new Pokemon());
    }

    //Resto de Codigo
}

```
### Paso 7: Crear la clase PokeWriteCSV

Crea una clase `PokeWriteCSV` con sus dos metodos : `writePokedexToCSV`, `escribirCSV`. 
Estos dos metodos proporcionan una funcionalidad para exportar los datos de una `Pokedex` de `Pokemon` a un archivo `CSV` de destino.

```java
package org.develop.controllers;

import com.opencsv.CSVWriter;
import org.develop.model.Pokemon;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Properties;

public class PokeWriteCSV {

        public void writePokedexToCSV(List<Pokemon> pokedex, String filePath) {
        try (CSVWriter writer = new CSVWriter(new FileWriter(filePath))) {
            // Escribir el encabezado del CSV
            String[] header = {"Id", "Número","Nombre", "Altura", "Peso"};
            writer.writeNext(header);

            // Escribir los datos de los Pokémon
            for (Pokemon pokemon : pokedex) {
                String[] data = {
                        String.valueOf(pokemon.getId()),
                        pokemon.getNum(),
                        pokemon.getName(),
                        pokemon.getHeight(),
                        pokemon.getWeight(),
                };
                writer.writeNext(data);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void escribirCSV(PokemonController pkController){
        Path rutaRelativa = Paths.get("");
        String rutaAbsoluta = rutaRelativa.toAbsolutePath().toString();
        String rutaData = rutaAbsoluta + File.separator + "data";
        String rutaEscritura = rutaData +File.separator + "pokedex.csv";
        PokeWriteCSV csvWriter = new PokeWriteCSV();
        csvWriter.writePokedexToCSV(pkController.obtenerPokemons(), rutaEscritura);

        System.out.println("Pokedex guardada en " + rutaEscritura);
    }

}
```
### Paso 8: Crear la clase PokeReadCsv

Crea la clase `PokeReadCsv` proporciona la funcionalidad de leer datos desde un archivo `CSV` y convertirlos en una lista de objetos `Pokemon`, lo que facilita la carga de datos de `Pokemon` desde un archivo externo en la aplicación.

```java
package org.develop.controllers;

import com.opencsv.CSVReader;
import org.develop.model.Pokemon;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;


public class PokeReadCsv {
    String path;  //Ruta al archivo CSV

    public ArrayList<Pokemon> leerCSV(){
        ArrayList<Pokemon> pokedex = new ArrayList<Pokemon>();
        path= Paths.get("").toAbsolutePath().toString() + File.separator + "data" + File.separator + "pokedex.csv";

        try (CSVReader reader = new CSVReader(new FileReader(path))){
            String[] line;
            //Saltamos la primera linea con la información
            reader.readNext();

            //Leeremos todos los datos del csv
            while ((line = reader.readNext()) != null){
                Pokemon pk=new Pokemon();
                pk.setId(Integer.parseInt(line[0]));
                pk.setNum(line[1]);
                pk.setName(line[2]);
                pk.setHeight(line[3]);
                pk.setWeight(line[4]);
                pokedex.add(pk);
            }
        }catch (IOException e){
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }
        return pokedex;
    }
}
```

## Services
***
### Paso 9: Crear la clase DatabaseManager

Crea la clase `DatabaseManager` que maneja la conexión a la base de datos SQLite y la inicialización de las tablas. Esta
clase es un singleton y tiene un método `getInstance` para obtener la instancia. También tiene un método `executeScript`
que ejecuta un script SQL desde un archivo.

```java
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
    private Connection connection;
    private PreparedStatement preparedStatement;
    private String serverUrl;
    private String dataBaseName;
    private boolean chargeTables;
    private String conURL;
    private String initScript;

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

    public static DatabaseManager getInstance(){
        if (instance==null){
            instance=new DatabaseManager();
        }
        return instance;
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
```
## Ejecucion
***
### Paso 1: Iniciar la Base de Datos
En el método `main`, obtén la instancia de `DatabaseManager` y usa el método `executeScript` para ejecutar un script SQL
desde un archivo:

```java
public class Main {
public static void main(String[] args) throws SQLException {
       DatabaseManager dbManager = DatabaseManager.getInstance();
       //El executeScript se ejecuta automaticamente ya que asi se establece en el fichero de propiedades.
}
}
```

## Repository
***
La clase `PokemonReposityImpl` tiene una implementacion de la interfaz `PokemonRepository`. Esta clase se utiliza para interactuar con una base de datos y realizar operaciones relacionadas con objetos `Pokemon`.
En resumen, la clase `PokemonRepositoryImpl` proporciona métodos para guardar, buscar y recuperar Pokemon desde una base de datos. Se utiliza en la capa de acceso a datos de una aplicación para interactuar con la información relacionada con los Pokemon en la base de datos.

```java
package org.develop.repository;

import org.develop.model.Pokemon;
import org.develop.services.DatabaseManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PokemonReposityImpl implements PokemonRepository<Pokemon,Integer>{

    private static PokemonReposityImpl instance; //instance para aplicar el patrón Singleton y un logger (logger) para registrar eventos y errores.

    private final Logger logger = LoggerFactory.getLogger(PokemonReposityImpl.class);

    private final DatabaseManager db;

    private PokemonReposityImpl(DatabaseManager db) {
        this.db = db;
    }

    //El método `getInstance` se utiliza para obtener una instancia de PokemonRepositoryImpl y asegurarse de que solo exista una instancia en la aplicación (patrón Singleton).
    public static PokemonReposityImpl getInstance(DatabaseManager db) {
        if (instance == null) {
            instance = new PokemonReposityImpl(db);
        }

        return instance;
    }
    
    //El metodo `save` se utiliza para guardar un objeto Pokemon en la base de datos. Se crea una consulta SQL que inserta los valores del Pokemon en la tabla Pokemon. Si la operación es exitosa, se devuelve el mismo objeto Pokemon que se guardó.
       @Override
    public Pokemon save(Pokemon pokemon) {
       String sqlQuery ="INSERT INTO Pokemon (id,num ,name, height, weight) VALUES (?,?,?,?,?)";
       try (var conn = db.getConnection()){
        var stmt = conn.prepareStatement(sqlQuery);
        stmt.setInt(1,pokemon.getId());
        stmt.setString(2,pokemon.getNum());
        stmt.setString(3,pokemon.getName());
        stmt.setString(4,pokemon.getHeight());
        stmt.setString(5,pokemon.getWeight());
        stmt.executeUpdate();
       }catch (SQLException e ){
           logger.error("Failed to save Pokemon " + e.getMessage());
       }

        return pokemon;
    }

    //El método `findAll` se utiliza para recuperar todos los Pokemon de la base de datos. Se ejecuta una consulta SQL para seleccionar todos los registros de la tabla Pokemon, y los resultados se almacenan en una lista de objetos Pokemon.
    @Override
    public List<Pokemon> findAll() throws SQLException {
        logger.debug("Obteniendo todos los Pokemon");
        String sqlQuery = "SELECT * FROM Pokemon";
        ArrayList<Pokemon> pokedex = new ArrayList<Pokemon>();


        try (var conn = db.getConnection();var stmt = conn.prepareStatement(sqlQuery)){
            ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            Pokemon pk = new Pokemon();
            pk.setId(rs.getInt("id"));
            pk.setNum(rs.getString("num"));
            pk.setName(rs.getString("name"));
            pk.setHeight(rs.getString("height"));
            pk.setWeight(rs.getString("weight"));
            pokedex.add(pk);
            }
        }

        return pokedex;
    }

    //El método `findById` se utiliza para buscar un Pokemon por su ID en la base de datos. Se ejecuta una consulta SQL que selecciona un Pokemon por su ID y se devuelve como un objeto Optional. Si se encuentra el Pokemon, se devuelve como un Optional que contiene el Pokemon; de lo contrario, se devuelve un Optional vacío.
    @Override
    public Optional<Pokemon> findById(Integer id) throws SQLException {
        logger.debug("Obtener Pokemon con ID: " + id);
        String sqlQuery ="SELECT * FROM Pokemon WHERE id = ?";
        Optional<Pokemon> pokemon = Optional.empty();

        try(var conn = db.getConnection(); var stmt = conn.prepareStatement(sqlQuery)){
            stmt.setInt(1,id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Pokemon pk = new Pokemon();
                pk.setId(rs.getInt("id"));
                pk.setNum(rs.getString("num"));
                pk.setName(rs.getString("name"));
                pk.setHeight(rs.getString("height"));
                pk.setWeight(rs.getString("weight"));
                System.out.println("Hola");
                pokemon = Optional.of(pk);
            }

            db.closeConnection();
        }catch (SQLException e){
            logger.error("Error obteniendo Pokemon con ID: " + id);
            System.out.println(e.getMessage());
        }

        return pokemon;
    }

    //El método ´findByName´ se utiliza para buscar un Pokemon por su nombre en la base de datos. Se ejecuta una consulta SQL que selecciona un Pokemon por su nombre y se devuelve como un objeto Optional. Si se encuentra el Pokemon, se devuelve como un Optional que contiene el Pokemon; de lo contrario, se devuelve un Optional vacío.
    @Override
    public Optional<Pokemon> findByName(String name) {
        logger.debug("Obtener Pokemon Con Nombre: " + name);
        String sqlQuery ="SELECT * FROM Pokemon WHERE name = ?";
        Pokemon pokemon = new Pokemon();
        try (var conn = db.getConnection();var stmt = conn.prepareStatement(sqlQuery)){
            stmt.setString(1,name);
            var rs = stmt.executeQuery();
        while (rs.next()){
            pokemon.setId(rs.getInt("id"));
            pokemon.setNum(rs.getString("num"));
            pokemon.setName(rs.getString("name"));
            pokemon.setHeight(rs.getString("height"));
            pokemon.setWeight(rs.getString("weight"));
        }
        db.closeConnection();
        return Optional.of(pokemon);
        }catch (SQLException e ){
            logger.error("Error al obtener Pokemon con Nombre: " + name);
            System.out.println(e.getMessage());
        }

        return Optional.empty();
    }

}
```

Y luego en tu main o donde quieras


```java
public class Main {

    public static void main(String[] args) throws SQLException {
        PokeReadCsv pokeReadCsv=new PokeReadCsv();
        DatabaseManager dbManager = DatabaseManager.getInstance();
        PokemonController pokemonController = PokemonController.getInstance();
        PokemonReposityImpl pkRepoImp = PokemonReposityImpl.getInstance(dbManager);
        ArrayList<Pokemon> pokemons = pokeReadCsv.leerCSV();

//        pokemons.stream()
//                .forEach(poke -> pkRepoImp.save(poke));
//
//        Optional<Pokemon> pk = pkRepoImp.findById(20);
//        System.out.println(pk.isPresent()?pk.get():"not found");
//
//        Optional<Pokemon> pk2 = pkRepoImp.findByName("Pikachu");
//        System.out.println(pk2.isPresent()?pk2.get():"not found");

//        System.out.println("Obtener Pokemon por ID");
//       Pokemon pokemon=pokemonController.getPokemonXId(25);
//
//        System.out.println("Obtener Primeros 10");
//       List<String> primerosDiez = pokemonController.obtenerNom10Primeros();git
//       primerosDiez.forEach(System.out::println);
//
//        System.out.println("Obtener Primeros 5");
//       List<String> cincoUltimos = pokemonController.obtenerNom5Ultimos();
//       cincoUltimos.forEach(System.out::println);
//
//        System.out.println();
//        Optional<Pokemon> pika = pokemonController.getPokemonXName("Pikachu");
//
//        System.out.println(pika.get());
//
//        Optional<Pokemon> nextEvolution = pokemonController.getEvolution("Charmander");
//        System.out.println(nextEvolution.orElse(new Pokemon()));
//
//        List<String> pokemonsXType=pokemonController.getPokeXType("Fire");
//        pokemonsXType.forEach(System.out::println);
//
//        List<String> weakPokemonsXType=pokemonController.getWeakPokeWatElec();
//        weakPokemonsXType.forEach(System.out::println);
//
//        long count = pokemonController.getCountOfWeak();
//        System.out.println(count);
//
//        Pokemon pokeMaxWeak = pokemonController.maxWeakPokemon().get();
//        System.out.println(pokeMaxWeak);
//
//        Pokemon pokeMinEvo = pokemonController.minEvoPokemon().get();
//        System.out.println(pokeMinEvo);
//
//        Pokemon pokeType = pokemonController.getPokeNotEvoType("Fire").get();
//        System.out.println(pokeType);
//
//        Pokemon evo = pokemonController.getEvolution(pokeType.getName()).get();
//        System.out.println(evo);
//
//        Optional<Pokemon> pokeMaxWei = pokemonController.getPokeMaxWeight();
//        System.out.println(pokeMaxWei.get());
//
//        Optional<Pokemon> pokeMaxHei = pokemonController.getPokeMaxHeight();
//        System.out.println(pokeMaxHei.get());
//
//        Optional<Pokemon> pokeMaxLength = pokemonController.getPokemonLength();
//        System.out.println(pokeMaxLength.get());
//
//        double mediaPeso = pokemonController.getAverageWeight();
//        System.out.println("Media de peso: " + mediaPeso);
//
//        double mediaAltura = pokemonController.getAverageHeight();
//        System.out.println("Media de altura: " + mediaAltura);
//
//        double mediaEvos = pokemonController.getAverageEvolutions();
//        System.out.println(mediaEvos);
//
//        double mediaWeak = pokemonController.getAverageWeaks();
//        System.out.println(mediaWeak);
        System.out.println("Tipos de pokemon");
        var groupType= pokemonController.getGroupType();
        groupType.forEach((k,v)-> System.out.println("Type " + k + ": " + v));
//
//        System.out.println("Debilidades de pokemon");
//        var groupWeak=pokemonController.getGroupWeak();
//        groupWeak.forEach((k,v)-> System.out.println("Weak " + k + ": " + v));
//
//        var groupNumEvo = pokemonController.getPokeXNumEvo();
//        groupNumEvo.forEach((k,v) -> System.out.println("Num. Evoluciones " + k + ": " + v));
//
//        String commonWeak = pokemonController.getMostCommonWeak();
//        System.out.println("Most Common Weak is " +commonWeak);
    }
}
```


