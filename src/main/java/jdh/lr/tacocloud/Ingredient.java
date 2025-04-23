package jdh.lr.tacocloud;

import lombok.Data;

@Data  //Генерит все методы (equals, hashCode, геттеры, сеттеры итд)
public class Ingredient {

    private final String id;
    private final String name;
    private final Type type;

    public enum Type {
        WRAP, PROTEIN, VEGETABLE, CHEESE, SAUCE
    }
}
