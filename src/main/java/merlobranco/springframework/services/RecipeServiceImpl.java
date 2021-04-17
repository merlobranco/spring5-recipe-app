package merlobranco.springframework.services;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;
import merlobranco.springframework.domain.Recipe;
import merlobranco.springframework.repositories.RecipeRepository;

@Slf4j
@Service
public class RecipeServiceImpl implements RecipeService {

    private final RecipeRepository recipeRepository;

    public RecipeServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Override
    @Transactional(readOnly=true)
    public Set<Recipe> getRecipes() {
    	log.debug("I'm in the service");
    	return new HashSet<Recipe>((Collection<? extends Recipe>) recipeRepository.findAll());
    }
}