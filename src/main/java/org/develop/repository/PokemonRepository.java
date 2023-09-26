package org.develop.repository;

import org.develop.model.Pokemon;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

public interface PokemonRepository<T,ID> extends CRUDRepository<T,ID>{
    Optional<Pokemon> findByName(String name) throws SQLException;

}
