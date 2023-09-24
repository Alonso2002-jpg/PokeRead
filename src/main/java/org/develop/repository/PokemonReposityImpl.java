package org.develop.repository;

import org.develop.model.Pokemon;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class PokemonReposityImpl implements PokemonRepository<Pokemon,Integer>{
    @Override
    public List<Pokemon> findAll() throws SQLException {
        return null;
    }

    @Override
    public Optional<Pokemon> findById(Integer integer) throws SQLException {
        return Optional.empty();
    }

    @Override
    public Pokemon save(Pokemon element) throws SQLException {
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
    public void backup() throws SQLException, IOException {

    }

    @Override
    public void restore() throws SQLException, IOException {

    }
}
