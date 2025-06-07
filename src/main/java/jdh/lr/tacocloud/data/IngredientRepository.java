package jdh.lr.tacocloud.data;

import jdh.lr.tacocloud.Ingredient;

import java.util.Optional;

public interface IngredientRepository {

    Iterable<Ingredient> findAll();

    Optional<Ingredient> findById( String id);

    Ingredient save(Ingredient ingredient);

}
