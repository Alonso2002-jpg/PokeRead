package org.develop.repository;

import org.develop.model.Pokemon;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

/**
 * La interfaz PokemonRepository proporciona métodos para acceder y manipular datos de Pokémon en un repositorio.
 *
 * @param <T> El tipo de entidad Pokémon.
 * @param <ID> El tipo de identificador para las entidades Pokémon.
 */

public interface PokemonRepository<T,ID> extends CRUDRepository<T,ID>{

    /**
     * Busca un Pokémon por su nombre en el repositorio.
     *
     * @param name El nombre del Pokémon que se desea buscar.
     * @return Un objeto Optional que contiene el Pokémon encontrado por nombre, o un Optional vacío si no se encuentra ningún Pokémon con ese nombre.
     * @throws SQLException Si ocurre un error al acceder a la base de datos.
     */
    Optional<Pokemon> findByName(String name) throws SQLException;

}
