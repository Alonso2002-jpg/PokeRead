package org.develop.model;

import lombok.Data;

/**
 * La clase NextEvolution representa la información sobre la siguiente evolución de un Pokémon.
 * Cada instancia de esta clase contiene detalles sobre el número de la evolución y el nombre
 * de la evolución y le aplicamos los getters and setters.
 */
@Data
public class NextEvolution{
    private String num;
    private String name;

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
