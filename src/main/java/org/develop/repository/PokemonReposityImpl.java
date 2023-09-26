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

    private static PokemonReposityImpl instance;

    private final Logger logger = LoggerFactory.getLogger(PokemonReposityImpl.class);

    private final DatabaseManager db;

    private PokemonReposityImpl(DatabaseManager db) {
        this.db = db;
    }

    public static PokemonReposityImpl getInstance(DatabaseManager db) {
        if (instance == null) {
            instance = new PokemonReposityImpl(db);
        }

        return instance;
    }

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
