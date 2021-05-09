package merlobranco.springframework.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.HashSet;
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
class IndexControllerTest {
	
	@InjectMocks
	IndexController indexController;
	
	@Mock
	RecipeService recipeService;
	
	@Mock
	Model model;

	@BeforeEach
	void setUp() throws Exception {
		indexController = new IndexController(recipeService);
	}

	@Test
	void testMockMVC() throws Exception {
		MockMvc mockMvc = MockMvcBuilders.standaloneSetup(indexController).build();
		
		mockMvc.perform(get("/"))
        .andExpect(status().isOk())
        .andExpect(view().name("index"));
	}
	
	@Test
	void testGetIndexPage() {
		// Given
		Set<Recipe> recipes = new HashSet<>();
		recipes.add(new Recipe());
		
		// We cannot pass again a recipe with empty id
		// It will be considered the same as the previous one
		Recipe recipe = new Recipe();
		recipe.setId(1L);
		recipes.add(recipe);
		
		when(recipeService.findAll()).thenReturn(recipes);
		
		ArgumentCaptor<Set<Recipe>> argumentCaptor = ArgumentCaptor.forClass(Set.class);
		
		// When
		String viewName = indexController.getIndexPage(model);
		
		// Then
		assertEquals("index", viewName);
		verify(recipeService, times(1)).findAll();
		verify(model, times(1)).addAttribute(eq("recipes"), argumentCaptor.capture());
		Set<Recipe> setInController = argumentCaptor.getValue();
		assertEquals(2, setInController.size());
	}

}
