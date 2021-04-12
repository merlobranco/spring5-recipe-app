package repositories;

import org.springframework.data.repository.CrudRepository;

import merlobranco.springframework.domain.Recipe;

public interface RecipeRepository extends CrudRepository<Recipe, Long> {

}
