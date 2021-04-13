package merlobranco.springframework.services;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import merlobranco.springframework.domain.Recipe;
import merlobranco.springframework.repositories.RecipeRepository;

@Service
public class RecipeServiceImpl implements RecipeService {

    private final RecipeRepository recipeRepository;

    public RecipeServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Override
    @Transactional(readOnly=true)
    public Set<Recipe> getRecipes() {
    	return new HashSet<Recipe>((Collection<? extends Recipe>) recipeRepository.findAll());
    }
}