package merlobranco.springframework.controllers;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import merlobranco.springframework.commands.IngredientCommand;
import merlobranco.springframework.commands.RecipeCommand;
import merlobranco.springframework.services.IngredientService;
import merlobranco.springframework.services.RecipeService;

@ExtendWith(MockitoExtension.class)
class IngredientControllerTest {
	
	@InjectMocks
	IngredientController ingredientController;
	
	@Mock
    RecipeService recipeService;
	@Mock
	IngredientService ingredientService;

    MockMvc mockMvc;

	@BeforeEach
	void setUp() throws Exception {
		ingredientController = new IngredientController(recipeService, ingredientService);
		mockMvc = MockMvcBuilders.standaloneSetup(ingredientController).build();
	}

	@Test
	void testShowIngredients() throws Exception {
		// Given
		RecipeCommand recipeCommand = new RecipeCommand();
		when(recipeService.findCommandById(anyLong())).thenReturn(recipeCommand);
		
		// When
		mockMvc.perform(get("/recipe/1/ingredients"))
			.andExpect(status().isOk())
			.andExpect(view().name("recipe/ingredient/list"))
			.andExpect(model().attributeExists("recipe"));
      
	    // Then
	    verify(recipeService, times(1)).findCommandById(anyLong());
	}
	
	@Test
	void testShowIngredient() throws Exception {
		// Given
		IngredientCommand ingredientCommand = new IngredientCommand();
		when(ingredientService.findCommandById(anyLong())).thenReturn(ingredientCommand);
		
		mockMvc.perform(get("/recipe/1/ingredients/1"))
			.andExpect(status().isOk())
			.andExpect(view().name("recipe/ingredient/show"))
			.andExpect(model().attributeExists("ingredient"));
		
		
		// Then
		verify(ingredientService, times(1)).findCommandById(anyLong());
		
	}

}
