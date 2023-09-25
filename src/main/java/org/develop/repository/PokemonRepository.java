package org.develop.repository;

import org.develop.model.Pokemon;

import java.io.IOException;
import java.sql.SQLException;

public interface PokemonRepository<T,ID> extends CRUDRepository<T,ID>{
    Pokemon findByName(String name) throws SQLException;

}
