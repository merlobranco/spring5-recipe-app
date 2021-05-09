package merlobranco.springframework.controllers;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import merlobranco.springframework.domain.Recipe;
import merlobranco.springframework.services.RecipeService;

@ExtendWith(MockitoExtension.class)
class RecipeControllerTest {
	
	public static final Long ID = 1L;
	
	@Mock
	RecipeService recipeService;
	
	@Mock
	Model model;
	
	@InjectMocks
	RecipeController recipeController;
	
	MockMvc mockMvc;
	
	Recipe returnRecipe;
	
	@BeforeEach
	void setUp() throws Exception {
		returnRecipe = new Recipe();
		returnRecipe.setId(ID);
		
		mockMvc = MockMvcBuilders
                .standaloneSetup(recipeController)
                .build();
	}
	
	@Test
	void testFindById() {
		// Given
		when(recipeService.findById(ID)).thenReturn(returnRecipe);
		ArgumentCaptor<Recipe> argumentCaptor = ArgumentCaptor.forClass(Recipe.class);
		
		// When
		String viewName = recipeController.showById(ID, model);
		
		// Then
		assertEquals(viewName, "recipe/show");
		verify(recipeService).findById(ID);
		verify(model).addAttribute(eq("recipe"), argumentCaptor.capture());
		Recipe recipeController = argumentCaptor.getValue();
		assertNotNull(recipeController, "Null recipe returned");
		assertEquals(ID, recipeController.getId());
	}

	@Test
	void testShowById() throws Exception {
		mockMvc.perform(get("/recipe/show/1"))
        .andExpect(status().isOk())
        .andExpect(view().name("recipe/show"));		
	}

}
