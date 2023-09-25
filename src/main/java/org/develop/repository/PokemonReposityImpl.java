package org.develop.repository;

import org.develop.model.Pokemon;
import org.develop.services.DatabaseManager;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PokemonReposityImpl implements PokemonRepository<Pokemon,Integer>{

    private static PokemonReposityImpl instance;

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
    public List<Pokemon> findAll() throws SQLException {
        var stmt = db.getConnection()
                .prepareStatement("SELECT * FROM Pokemon");
        ResultSet rs = stmt.executeQuery();
        ArrayList<Pokemon> pokedex = new ArrayList<Pokemon>();
        while (rs.next()) {
            Pokemon pk = new Pokemon();
            pk.setId(rs.getInt("id"));
            pk.setNum(rs.getString("num"));
            pk.setName(rs.getString("name"));
            pk.setHeight(rs.getString("height"));
            pk.setWeight(rs.getString("weight"));

            pokedex.add(pk);
        }

        //Cerrar conexiones
        stmt.close();
        rs.close();
        db.closeConnection();
        return pokedex;
    }

    @Override
    public Optional<Pokemon> findById(Integer id) throws SQLException {
        var stmt = db.getConnection()
                .prepareStatement("SELECT * FROM Pokemon WHERE id = ?");
        stmt.setInt(1,id);
        ResultSet rs = stmt.executeQuery();
        Optional<Pokemon> pokemon = Optional.empty();
        while (rs.next()) {
            Pokemon pk = new Pokemon();
            pk.setId(rs.getInt("id"));
            pk.setNum(rs.getString("num"));
            pk.setName(rs.getString("name"));
            pk.setHeight(rs.getString("height"));
            pk.setWeight(rs.getString("weight"));
            pokemon = Optional.of(pk);
        }
        return pokemon;
    }

    @Override
    public Pokemon save(Pokemon pokemon) throws SQLException {

        return null;
    }

    @Override
    public Pokemon remove(Pokemon element) throws SQLException {
        return null;
    }

    @Override
    public Pokemon update(Pokemon element) throws SQLException {
        return null;
    }

    @Override
    public void deleteAll() throws SQLException {

    }

    @Override
    public Pokemon findByName() throws SQLException {
        return null;
    }

    @Override
    public void backup() throws SQLException, IOException {

    }

    @Override
    public void restore() throws SQLException, IOException {

    }
}
