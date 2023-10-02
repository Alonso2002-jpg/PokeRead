package org.develop.repository;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

/**
 * Esta interfaz representa un repositorio generico para realizar operaciones CRUD (Create, Read, Update, Delete)
 * en un almacen de datos.
 *
 * @param <T> El tipo de entidad que se almacena y manipula en el repositorio.
 * @param <ID> El tipo de identificador unico utilizado para buscar y acceder a las entidades.
 */

public interface CRUDRepository<T,ID> {

    /**
     * Recupera todas las entidades almacenadas en el almacen de datos y las devuelve en una lista.
     *
     * @return Una lista que contiene todas las entidades almacenadas en el almacen de datos.
     * @throws SQLException Si ocurre un error al acceder a la base de datos o al realizar la operacion de recuperacion.
     */
    List<T> findAll() throws SQLException;

    /**
     * Busca y devuelve una entidad por su identificador unico.
     *
     * @param id El identificador unico de la entidad que se desea buscar.
     * @return Un objeto Optional que representa la entidad encontrada, o un Optional vacio si no se encuentra ninguna entidad con el ID especificado.
     * @throws SQLException Si ocurre un error al acceder a la base de datos o al realizar la operacion de busqueda.
     */
    Optional<T> findById(ID id) throws SQLException;;


    /**
     * Guarda una entidad en el almacen de datos. Puede ser utilizado para crear una nueva entidad o actualizar una existente.
     *
     * @param element La entidad que se va a guardar en el almacen de datos.
     * @return La entidad guardada, que puede incluir informacion actualizada despues de la operacion.
     * @throws SQLException Si ocurre un error al acceder a la base de datos o al realizar la operacion de guardado.
     */
    T save(T element) throws SQLException;
}
