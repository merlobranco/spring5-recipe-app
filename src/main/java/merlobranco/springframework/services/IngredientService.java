package merlobranco.springframework.services;

import merlobranco.springframework.commands.IngredientCommand;
import merlobranco.springframework.domain.Ingredient;

public interface IngredientService extends CrudService<Ingredient, Long> {
	
	IngredientCommand findCommandById(Long id);

}
