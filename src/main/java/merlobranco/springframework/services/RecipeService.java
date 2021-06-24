package merlobranco.springframework.services;

import merlobranco.springframework.commands.RecipeCommand;
import merlobranco.springframework.domain.Recipe;

public interface RecipeService extends CrudService<Recipe, Long> {
	
	RecipeCommand saveRecipeCommand(RecipeCommand command);
	
	RecipeCommand findCommandById(Long id);

}
