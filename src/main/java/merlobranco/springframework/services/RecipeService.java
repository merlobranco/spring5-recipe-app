package merlobranco.springframework.services;

import java.util.Set;

import merlobranco.springframework.domain.Recipe;

public interface RecipeService {
	
	Set<Recipe> getRecipes();

}
