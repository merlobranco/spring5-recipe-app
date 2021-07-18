package merlobranco.springframework.services;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;
import merlobranco.springframework.commands.IngredientCommand;
import merlobranco.springframework.converters.IngredientCommandToIngredient;
import merlobranco.springframework.converters.IngredientToIngredientCommand;
import merlobranco.springframework.domain.Ingredient;
import merlobranco.springframework.repositories.IngredientRepository;

@Slf4j
@Service
public class IngredientServiceImpl extends JpaService<Ingredient, Long> implements IngredientService {
	
	private final IngredientToIngredientCommand ingredientToIngredientCommand;
	private final IngredientCommandToIngredient ingredientCommandToIngredient;
	private final IngredientRepository ingredientRepository;

    public IngredientServiceImpl(IngredientToIngredientCommand ingredientToIngredientCommand, IngredientCommandToIngredient ingredientCommandToIngredient, IngredientRepository ingredientRepository) {
        super(ingredientRepository);
    	this.ingredientToIngredientCommand = ingredientToIngredientCommand;
		this.ingredientCommandToIngredient = ingredientCommandToIngredient;
        this.ingredientRepository = ingredientRepository;
    }
    
    @Override
    @Transactional(readOnly = true)
	public Set<IngredientCommand> findCommadsByRecipeId(Long recipeId) {
		Set<Ingredient> ingredients = ingredientRepository.findByRecipeId(recipeId);
		if (ingredients == null)
			return new HashSet<>();
		
		return ingredients.stream().map(i -> ingredientToIngredientCommand.convert(i)).collect(Collectors.toSet());
	}

	@Override
	@Transactional(readOnly = true)
	public IngredientCommand findCommandById(Long id) {
		Ingredient ingredient = ingredientRepository.findById(id).orElse(null);
		return ingredientToIngredientCommand.convert(ingredient);
	}

	@Override
	@Transactional
	public IngredientCommand saveIngredientCommand(IngredientCommand command) {
		Ingredient detachedIngredient = ingredientCommandToIngredient.convert(command);
		Ingredient savedIngredient = ingredientRepository.save(detachedIngredient);
		log.debug("Saved Ingredient Id: " + savedIngredient.getId());
		
		return ingredientToIngredientCommand.convert(savedIngredient);
	}
}
