package org.develop.repository;

import java.io.IOException;
import java.sql.SQLException;

public interface PokemonRepository<T,ID> extends CRUDRepository<T,ID>{
    void deleteAll() throws SQLException;

    void backup() throws SQLException, IOException;

    void restore() throws SQLException, IOException;
}
