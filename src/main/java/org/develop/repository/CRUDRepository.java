package org.develop.repository;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface CRUDRepository<T,ID> {

    List<T> findAll() throws SQLException;

    Optional<T> findById(ID id) throws SQLException;;

    T save(T element) throws SQLException;
}
