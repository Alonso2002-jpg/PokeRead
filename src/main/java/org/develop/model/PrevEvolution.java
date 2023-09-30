package org.develop.model;

import lombok.Data;
/**
 * La clase PrevEvolution representa información sobre una evolución previa de un Pokémon.
 * Cada instancia de esta clase contiene detalles sobre el número de la evolución previa y el nombre
 * de la evolución previa.
 */
@Data
public class PrevEvolution{
    private String num;
    private String name;
}
