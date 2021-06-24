package merlobranco.springframework.converters;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import merlobranco.springframework.commands.IngredientCommand;
import merlobranco.springframework.commands.UnitOfMeasureCommand;
import merlobranco.springframework.domain.Ingredient;

class IngredientCommandToIngredientTest {
	
	public static final Long ID_VALUE = 1L;
	public static final String DESCRIPTION = "Cheeseburger";
	public static final BigDecimal AMOUNT = new BigDecimal("1");
	public static final Long UOM_ID = 2L;
	
	IngredientCommandToIngredient converter;

	@BeforeEach
	void setUp() throws Exception {
		converter = new IngredientCommandToIngredient(new UnitOfMeasureCommandToUnitOfMeasure());
	}

	@Test
	void testNullObject() {
		assertNull(converter.convert(null));
	}
	
	@Test
	void testEmptyObject() {
		assertNotNull(converter.convert(new IngredientCommand()));
	}
	
	@Test
	void testConvert() {
		// Given
		IngredientCommand ingredientCommand = new IngredientCommand();
		ingredientCommand.setId(ID_VALUE);
		ingredientCommand.setDescription(DESCRIPTION);
		ingredientCommand.setAmount(AMOUNT);
		UnitOfMeasureCommand uomCommand = new UnitOfMeasureCommand();
		uomCommand.setId(UOM_ID);
		ingredientCommand.setUom(uomCommand);
		
		// When
		Ingredient ingredient = converter.convert(ingredientCommand);
		
		// Then
		assertNotNull(ingredient);
		assertNotNull(ingredient.getUom());
		assertEquals(ID_VALUE, ingredient.getId());
		assertEquals(DESCRIPTION, ingredient.getDescription());
		assertEquals(AMOUNT, ingredient.getAmount());
		assertEquals(UOM_ID, ingredient.getUom().getId());
		
	}
	
	@Test
	void testConvertWithNullUOM() {
		// Given
		IngredientCommand ingredientCommand = new IngredientCommand();
		ingredientCommand.setId(ID_VALUE);
		ingredientCommand.setDescription(DESCRIPTION);
		ingredientCommand.setAmount(AMOUNT);
		
		// When
		Ingredient ingredient = converter.convert(ingredientCommand);
		
		// Then
		assertNotNull(ingredient);
		assertNull(ingredient.getUom());
		assertEquals(ID_VALUE, ingredient.getId());
		assertEquals(DESCRIPTION, ingredient.getDescription());
		assertEquals(AMOUNT, ingredient.getAmount());
	}

}
