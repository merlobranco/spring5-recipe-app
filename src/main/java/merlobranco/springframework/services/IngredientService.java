package merlobranco.springframework.services;

import java.util.Set;

import merlobranco.springframework.commands.IngredientCommand;
import merlobranco.springframework.domain.Ingredient;

public interface IngredientService extends CrudService<Ingredient, Long> {
	
	Set<IngredientCommand> findCommadsByRecipeId(Long recipeId);
	
	IngredientCommand findCommandById(Long id);

}
