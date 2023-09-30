package org.develop.model;

import lombok.Data;

import java.util.ArrayList;
/**
 * La clase Pokedex representa una colección de Pokémon.
 * Contiene una lista de objetos de la clase Pokemon.
 */
@Data
public class Pokedex {
    public ArrayList<Pokemon> pokemon;
}
