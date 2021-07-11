package merlobranco.springframework.repositories;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Set;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import merlobranco.springframework.domain.Ingredient;

@ExtendWith(SpringExtension.class)
@DataJpaTest // Will bring an embedded database and will configure Spring data JPA for us
public class IngredientRepositoryIT {
	
	@Autowired
	IngredientRepository ingredientRepository;
	
	Long RECIPE_ID = 1l;
	
	@Test
	void testFindByRecipeId() {
		Set<Ingredient> ingredients = ingredientRepository.findByRecipeId(RECIPE_ID);
		assertNotNull(ingredients);
//		assertFalse(ingredients.isEmpty());
		
	}

}
