package org.develop.model;

import lombok.Data;

/**
 * La clase NextEvolution representa la informacion sobre la siguiente evolucion de un Pokemon.
 * Cada instancia de esta clase contiene detalles sobre el numero de la evolucion y el nombre
 * de la evolucion y le aplicamos los getters and setters.
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
