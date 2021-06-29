package merlobranco.springframework.repositories;

import org.springframework.data.repository.CrudRepository;

import merlobranco.springframework.domain.Ingredient;

public interface IngredientRepository extends CrudRepository<Ingredient, Long> {
}
