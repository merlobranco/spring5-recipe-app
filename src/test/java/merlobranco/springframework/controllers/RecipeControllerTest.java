package merlobranco.springframework.controllers;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import merlobranco.springframework.commands.RecipeCommand;
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
		when(recipeService.findById(anyLong())).thenReturn(returnRecipe);
		
		mockMvc.perform(get("/recipe/show/1"))
        .andExpect(status().isOk())
        .andExpect(view().name("recipe/show"))
        .andExpect(model().attributeExists("recipe"));
	}
	
	@Test
    public void testCreate() throws Exception {
        
        mockMvc.perform(get("/recipe/form"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/recipeForm"))
                .andExpect(model().attributeExists("recipe"));
    }

    
	@Test
	public void testUpdate() throws Exception {
		RecipeCommand command = new RecipeCommand();
		command.setId(2L);
		
		when(recipeService.findCommandById(anyLong())).thenReturn(command);
		
		mockMvc.perform(get("/recipe/form/1"))
		.andExpect(status().isOk())
		.andExpect(view().name("recipe/recipeForm"))
		.andExpect(model().attributeExists("recipe"));
	}
	
	@Test
    public void testSave() throws Exception {
        RecipeCommand command = new RecipeCommand();
        command.setId(2L);

        when(recipeService.saveRecipeCommand(any())).thenReturn(command);

        mockMvc.perform(post("/recipe/")
        		.contentType(MediaType.APPLICATION_FORM_URLENCODED)
        		.param("id", "")
                .param("description", "some string"))
        .andExpect(status().is3xxRedirection())
        .andExpect(view().name("redirect:/recipe/show/2"));
    }
	
	@Test
    public void testDelete() throws Exception {
		mockMvc.perform(get("/recipe/delete/1"))
        .andExpect(status().is3xxRedirection())
        .andExpect(view().name("redirect:/"));
		
		verify(recipeService).deleteById(anyLong());
    }
	

}
