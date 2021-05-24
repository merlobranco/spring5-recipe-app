package merlobranco.springframework.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;
import merlobranco.springframework.commands.RecipeCommand;
import merlobranco.springframework.converters.RecipeCommandToRecipe;
import merlobranco.springframework.converters.RecipeToRecipeCommand;
import merlobranco.springframework.domain.Recipe;
import merlobranco.springframework.repositories.RecipeRepository;

@Slf4j
@Service
public class RecipeServiceImpl extends JpaService<Recipe, Long> implements RecipeService {
	
	private final RecipeRepository recipeRepository;
    private final RecipeCommandToRecipe recipeCommandToRecipe;
    private final RecipeToRecipeCommand recipeToRecipeCommand;

    public RecipeServiceImpl(RecipeRepository recipeRepository, RecipeCommandToRecipe recipeCommandToRecipe, RecipeToRecipeCommand recipeToRecipeCommand) {
        super(recipeRepository);
        this.recipeRepository = recipeRepository;
        this.recipeCommandToRecipe = recipeCommandToRecipe;
        this.recipeToRecipeCommand = recipeToRecipeCommand;
    }

	@Override
	@Transactional
	public RecipeCommand saveRecipeCommand(RecipeCommand command) {
		 Recipe detachedRecipe = recipeCommandToRecipe.convert(command);
		 Recipe savedRecipe = recipeRepository.save(detachedRecipe);
	     log.debug("Saved Recipe Id: " + savedRecipe.getId());
	     return recipeToRecipeCommand.convert(savedRecipe);
	}
}