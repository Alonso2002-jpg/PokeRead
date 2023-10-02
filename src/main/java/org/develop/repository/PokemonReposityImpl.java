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
/**
 * La clase PokemonRepositoryImpl implementa la interfaz PokemonRepository para acceder y manipular datos de Pokemon en una base de datos.
 */
public class PokemonReposityImpl implements PokemonRepository<Pokemon,Integer>{

    private static PokemonReposityImpl instance;

    private final Logger logger = LoggerFactory.getLogger(PokemonReposityImpl.class);

    private final DatabaseManager db;

    private PokemonReposityImpl(DatabaseManager db) {
        this.db = db;
    }

    /**
     * Obtiene una instancia de PokemonRepositoryImpl.
     *
     * @param db El administrador de la base de datos utilizado para acceder a los datos de Pokemon.
     * @return Una instancia de PokemonRepositoryImpl.
     */

    public static PokemonReposityImpl getInstance(DatabaseManager db) {
        if (instance == null) {
            instance = new PokemonReposityImpl(db);
        }

        return instance;
    }

    /**
     * Guarda un Pokemon en la base de datos.
     *
     * @param pokemon El Pokemon que se desea guardar en la base de datos.
     * @return El Pokemon que se ha guardado en la base de datos.
     */
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

    /**
     * Obtiene una lista de todos los Pokemon en la base de datos.
     *
     * @return Una lista de objetos Pokemon que representan todos los Pokemon en la base de datos.
     * @throws SQLException Si ocurre un error al acceder a la base de datos.
     */
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

    /**
     * Busca un Pokemon en la base de datos por su ID.
     *
     * @param id El ID del Pokemon que se desea buscar.
     * @return Un objeto Optional que contiene el Pokemon si se encuentra en la base de datos, o un Optional vacio si no se encuentra.
     * @throws SQLException Si ocurre un error al acceder a la base de datos.
     */
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


    /**
     * Busca un Pokemon en la base de datos por su nombre.
     *
     * @param name El nombre del Pokemon que se desea buscar.
     * @return Un objeto Optional que contiene el Pokemon si se encuentra en la base de datos, o un Optional vacio si no se encuentra.
     */
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
