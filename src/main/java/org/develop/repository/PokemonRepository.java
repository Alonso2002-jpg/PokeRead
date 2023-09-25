package org.develop.repository;

import org.develop.model.Pokemon;

import java.io.IOException;
import java.sql.SQLException;

public interface PokemonRepository<T,ID> extends CRUDRepository<T,ID>{
    void deleteAll() throws SQLException;

    Pokemon findByName() throws SQLException;

    void backup() throws SQLException, IOException;

    void restore() throws SQLException, IOException;
}
