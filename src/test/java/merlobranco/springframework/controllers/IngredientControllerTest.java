package merlobranco.springframework.controllers;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
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
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import merlobranco.springframework.commands.IngredientCommand;
import merlobranco.springframework.services.IngredientService;
import merlobranco.springframework.services.UnitOfMeasureService;

@ExtendWith(MockitoExtension.class)
class IngredientControllerTest {
	
	@InjectMocks
	IngredientController ingredientController;
	
	@Mock
	IngredientService ingredientService;
	@Mock
	UnitOfMeasureService unitOfMeasureService;
	@Mock
	Model model;

    MockMvc mockMvc;

	@BeforeEach
	void setUp() throws Exception {
		ingredientController = new IngredientController(ingredientService, unitOfMeasureService);
		mockMvc = MockMvcBuilders.standaloneSetup(ingredientController).build();
	}

	@Test
	void testListIngredients() throws Exception {
		// Given
		Set<IngredientCommand> ingredientsCommand = new HashSet<>();
		ingredientsCommand.add(new IngredientCommand());
				
		// We cannot pass again a ingredient with empty id
		// It will be considered the same as the previous one
		IngredientCommand ingredientCommand = new IngredientCommand();
		ingredientCommand.setId(1L);
		ingredientsCommand.add(ingredientCommand);
		
		when(ingredientService.findCommadsByRecipeId(anyLong())).thenReturn(ingredientsCommand);
		ArgumentCaptor<Set<IngredientCommand>> argumentCaptor = ArgumentCaptor.forClass(Set.class);
		
		// When
		String viewName = ingredientController.showIngredients(anyLong(), model);
		
		// Then
		assertEquals("recipe/ingredient/list", viewName);
		verify(ingredientService, times(1)).findCommadsByRecipeId(anyLong());
		verify(model, times(1)).addAttribute(eq("ingredients"), argumentCaptor.capture());
		Set<IngredientCommand> setInController = argumentCaptor.getValue();
		assertEquals(2, setInController.size());
	}
	
	@Test
	void testShowIngredients() throws Exception {
		// Given
		Set<IngredientCommand> ingredientsCommand = new HashSet<>();
		ingredientsCommand.add(new IngredientCommand());
				
		// We cannot pass again a ingredient with empty id
		// It will be considered the same as the previous one
		IngredientCommand ingredientCommand = new IngredientCommand();
		ingredientCommand.setId(1L);
		ingredientsCommand.add(ingredientCommand);
		
		when(ingredientService.findCommadsByRecipeId(anyLong())).thenReturn(ingredientsCommand);
		
		// When
		mockMvc.perform(get("/recipe/1/ingredients"))
			.andExpect(status().isOk())
			.andExpect(view().name("recipe/ingredient/list"))
			.andExpect(model().attributeExists("ingredients"))
			.andExpect(model().attributeExists("recipeId"));
      
	    // Then
	    verify(ingredientService, times(1)).findCommadsByRecipeId(anyLong());
	}
	
	@Test
	void testShowIngredient() throws Exception {
		// Given
		IngredientCommand ingredientCommand = new IngredientCommand();
		when(ingredientService.findCommandById(anyLong())).thenReturn(ingredientCommand);
		
		mockMvc.perform(get("/recipe/1/ingredients/show/1"))
			.andExpect(status().isOk())
			.andExpect(view().name("recipe/ingredient/show"))
			.andExpect(model().attributeExists("ingredient"));
		
		
		// Then
		verify(ingredientService, times(1)).findCommandById(anyLong());
		
	}
	
	@Test
	void testCreateIngredient() throws Exception {
		// When
		when(unitOfMeasureService.findAllCommands()).thenReturn(new HashSet<>());
		
		// Then
        mockMvc.perform(get("/recipe/1/ingredients/form"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/ingredient/form"))
                .andExpect(model().attributeExists("ingredient"))
                .andExpect(model().attributeExists("uomList"));
	}
	
	@Test
	void testUpdateIngredient() throws Exception {
		// Given
        IngredientCommand ingredientCommand = new IngredientCommand();

        // When
        when(ingredientService.findCommandById(anyLong())).thenReturn(ingredientCommand);
        when(unitOfMeasureService.findAllCommands()).thenReturn(new HashSet<>());

        // Then
        mockMvc.perform(get("/recipe/1/ingredients/form/2"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/ingredient/form"))
                .andExpect(model().attributeExists("ingredient"))
                .andExpect(model().attributeExists("uomList"));
	}
	
	@Test
	void saveIngredient() throws Exception {
		// Given
        IngredientCommand command = new IngredientCommand();
        command.setId(3L);
        command.setRecipeId(2L);

        // When
        when(ingredientService.saveIngredientCommand(any())).thenReturn(command);

        // Then
        mockMvc.perform(post("/recipe/2/ingredients")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("id", "")
                .param("description", "some string"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/recipe/2/ingredients/show/3"));
	}
	
	@Test
    public void testDeleteIngredient() throws Exception {
		mockMvc.perform(get("/recipe/1/ingredients/delete/1"))
        .andExpect(status().is3xxRedirection())
        .andExpect(view().name("redirect:/recipe/1/ingredients"));
		
		verify(ingredientService).deleteById(anyLong());
    }

}
