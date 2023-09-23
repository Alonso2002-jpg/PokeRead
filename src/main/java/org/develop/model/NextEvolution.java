package org.develop.model;

import lombok.Data;

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
