package org.develop.model;

import lombok.Data;

import java.util.ArrayList;
/**
 * La clase Pokedex representa una coleccion de Pokemon.
 * Contiene una lista de objetos de la clase Pokemon.
 */
@Data
public class Pokedex {
    public ArrayList<Pokemon> pokemon;
}
