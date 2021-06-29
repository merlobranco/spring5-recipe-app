package merlobranco.springframework.services;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import merlobranco.springframework.commands.IngredientCommand;
import merlobranco.springframework.converters.IngredientToIngredientCommand;
import merlobranco.springframework.domain.Ingredient;
import merlobranco.springframework.repositories.IngredientRepository;

@ExtendWith(MockitoExtension.class)
class IngredientServiceImplTest {
	
public static final Long ID = 1L;
	
	@Mock
	IngredientRepository ingredientRepository;
	
	@InjectMocks
	IngredientServiceImpl ingredientService;
	
	@Mock
    IngredientToIngredientCommand ingredientToIngredientCommand;
	
	Ingredient returnIngredient;

	@BeforeEach
	void setUp() throws Exception {
		returnIngredient = new Ingredient();
		returnIngredient.setId(ID);
	}
	
	@Test
	void testFindCommandById() {
		// Given
        when(ingredientRepository.findById(anyLong())).thenReturn(Optional.of(returnIngredient));
        IngredientCommand ingredientCommand = new IngredientCommand();
        ingredientCommand.setId(ID);
        when(ingredientToIngredientCommand.convert(any())).thenReturn(ingredientCommand);

        // When
        IngredientCommand ingredientCommandById = ingredientService.findCommandById(ID);

        // Then
        assertNotNull(ingredientCommandById, "Null ingredient returned");
        verify(ingredientRepository, times(1)).findById(anyLong());
        verify(ingredientToIngredientCommand, times(1)).convert(any());
	}

}
