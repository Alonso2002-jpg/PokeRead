package org.develop.repository;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface CRUDRepository<T,ID> {

    /**
     * Devuelve una lista de todos los elementos de la Base de Datos.
     *
     * @return Lista de elementos
     * @throws SQLException Si hay algún error en la base de datos
     */

    List<T> findAll() throws SQLException;

    /**
     * Devuelve un Optional del elemento dado un id
     *
     * @param id Id del elemento
     * @return Optional del elemento
     * @throws SQLException Si hay algún error en la base de datos
     */
    Optional<T> findById(ID id) throws SQLException;;


    /**
     * Inserta un elemento en el repositorio
     *
     * @param element Elemento a insertar
     * @return Elemento insertado
     * @throws SQLException Si hay algún error en la base de datos
     */
    T save(T element) throws SQLException;
}
