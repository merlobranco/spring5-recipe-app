package merlobranco.springframework.converters;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import merlobranco.springframework.commands.IngredientCommand;
//import merlobranco.springframework.commands.UnitOfMeasureCommand;
import merlobranco.springframework.domain.Ingredient;
import merlobranco.springframework.domain.UnitOfMeasure;

class IngredientToIngredientCommandTest {

	public static final Long ID_VALUE = 1L;
	public static final String DESCRIPTION = "Cheeseburger";
	public static final BigDecimal AMOUNT = new BigDecimal("1");
	public static final Long UOM_ID = 2L;
	
	IngredientToIngredientCommand converter;

	@BeforeEach
	void setUp() throws Exception {
		converter = new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand());
	}

	@Test
	void testNullObject() {
		assertNull(converter.convert(null));
	}
	
	@Test
	void testEmptyObject() {
		assertNotNull(converter.convert(new Ingredient()));
	}
	
	@Test
	void testConvert() {
		// Given
		Ingredient ingredient = new Ingredient();
		ingredient.setId(ID_VALUE);
		ingredient.setDescription(DESCRIPTION);
		ingredient.setAmount(AMOUNT);
		UnitOfMeasure uom = new UnitOfMeasure();
		uom.setId(UOM_ID);
		ingredient.setUom(uom);
		
		// When
		IngredientCommand ingredientCommand = converter.convert(ingredient);
		
		// Then
		assertNotNull(ingredientCommand);
		assertNotNull(ingredientCommand.getUom());
		assertEquals(ID_VALUE, ingredientCommand.getId());
		assertEquals(DESCRIPTION, ingredientCommand.getDescription());
		assertEquals(AMOUNT, ingredientCommand.getAmount());
		assertEquals(UOM_ID, ingredientCommand.getUom().getId());
		
	}
	
	@Test
	void testConvertWithNullUOM() {
		// Given
		Ingredient ingredient = new Ingredient();
		ingredient.setId(ID_VALUE);
		ingredient.setDescription(DESCRIPTION);
		ingredient.setAmount(AMOUNT);
		
		// When
		IngredientCommand ingredientCommand = converter.convert(ingredient);
		
		// Then
		assertNotNull(ingredientCommand);
		assertNull(ingredientCommand.getUom());
		assertEquals(ID_VALUE, ingredientCommand.getId());
		assertEquals(DESCRIPTION, ingredientCommand.getDescription());
		assertEquals(AMOUNT, ingredientCommand.getAmount());
	}

}
