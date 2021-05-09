package merlobranco.springframework.services;

import org.springframework.stereotype.Service;

import merlobranco.springframework.domain.Recipe;
import merlobranco.springframework.repositories.RecipeRepository;

@Service
public class RecipeServiceImpl extends JpaService<Recipe, Long> implements RecipeService {

    public RecipeServiceImpl(RecipeRepository repository) {
        super(repository);
    }
}