package merlobranco.springframework.repositories;

import java.util.Set;

import org.springframework.data.repository.CrudRepository;

import merlobranco.springframework.domain.Ingredient;

public interface IngredientRepository extends CrudRepository<Ingredient, Long> {
	
	public Set<Ingredient> findByRecipeId(Long recipeId);
	
}
