package org.develop.repository;

import org.develop.model.Pokemon;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

/**
 * La interfaz PokemonRepository proporciona metodos para acceder y manipular datos de Pokemon en un repositorio.
 *
 * @param <T> El tipo de entidad Pokemon.
 * @param <ID> El tipo de identificador para las entidades Pokemon.
 */

public interface PokemonRepository<T,ID> extends CRUDRepository<T,ID>{

    /**
     * Busca un Pokemon por su nombre en el repositorio.
     *
     * @param name El nombre del Pokemon que se desea buscar.
     * @return Un objeto Optional que contiene el Pokemon encontrado por nombre, o un Optional vacio si no se encuentra ningun Pokemon con ese nombre.
     * @throws SQLException Si ocurre un error al acceder a la base de datos.
     */
    Optional<Pokemon> findByName(String name) throws SQLException;

}
