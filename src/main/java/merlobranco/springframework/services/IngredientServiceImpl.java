package merlobranco.springframework.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import merlobranco.springframework.commands.IngredientCommand;
import merlobranco.springframework.converters.IngredientToIngredientCommand;
import merlobranco.springframework.domain.Ingredient;
import merlobranco.springframework.repositories.IngredientRepository;
import merlobranco.springframework.repositories.RecipeRepository;

@Service
public class IngredientServiceImpl extends JpaService<Ingredient, Long> implements IngredientService {
	
	private final IngredientToIngredientCommand ingredientToIngredientCommand;
	private final RecipeRepository recipeRepository;
	private final IngredientRepository ingredientRepository;

    public IngredientServiceImpl(IngredientToIngredientCommand ingredientToIngredientCommand, RecipeRepository recipeRepository, IngredientRepository ingredientRepository) {
        super(ingredientRepository);
    	this.ingredientToIngredientCommand = ingredientToIngredientCommand;
        this.recipeRepository = recipeRepository;
        this.ingredientRepository = ingredientRepository;
    }

	@Override
	@Transactional(readOnly = true)
	public IngredientCommand findCommandById(Long id) {
		Ingredient ingredient = ingredientRepository.findById(id).orElse(null);
		return ingredientToIngredientCommand.convert(ingredient);
	}
}
