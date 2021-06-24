package merlobranco.springframework.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import merlobranco.springframework.commands.RecipeCommand;
import merlobranco.springframework.converters.RecipeCommandToRecipe;
import merlobranco.springframework.converters.RecipeToRecipeCommand;
import merlobranco.springframework.domain.Recipe;
import merlobranco.springframework.repositories.RecipeRepository;

@ExtendWith(MockitoExtension.class)
class RecipeServiceImplTest {
	
	public static final Long ID = 1L;
	
	@Mock
	RecipeRepository recipeRepository;
	
	@Mock
    RecipeToRecipeCommand recipeToRecipeCommand;

    @Mock
    RecipeCommandToRecipe recipeCommandToRecipe;
	
	@InjectMocks
	RecipeServiceImpl recipeService;
	
	Recipe returnRecipe;

	@BeforeEach
	void setUp() throws Exception {
		returnRecipe = new Recipe();
		returnRecipe.setId(ID);
	}

	@Test
	void testFindAll() {
		
		Set<Recipe> recipesData = new HashSet<>();
		recipesData.add(new Recipe());
		
		when(recipeRepository.findAll()).thenReturn(recipesData);
		
		Set<Recipe> recipes = recipeService.findAll();
		assertEquals(recipes.size(), 1);
		verify(recipeRepository, times(1)).findAll();
	}
	
	@Test
	void testFindById() {
		// Given
		when(recipeRepository.findById(anyLong())).thenReturn(Optional.of(returnRecipe));
		
		// When
		Recipe recipe = recipeService.findById(ID);
		
		// Then
		assertNotNull(recipe, "Null recipe returned");
		assertEquals(ID, recipe.getId());
		verify(recipeRepository).findById(ID);
	}
	
	@Test
	void findCommandById() {
		// Given
        when(recipeRepository.findById(anyLong())).thenReturn(Optional.of(returnRecipe));
        RecipeCommand recipeCommand = new RecipeCommand();
        recipeCommand.setId(ID);
        when(recipeToRecipeCommand.convert(any())).thenReturn(recipeCommand);

        // When
        RecipeCommand recipeCommandById = recipeService.findCommandById(ID);

        // Then
        assertNotNull(recipeCommandById, "Null recipe returned");
        verify(recipeRepository, times(1)).findById(anyLong());
        verify(recipeRepository, never()).findAll();
		
	}
	
}
