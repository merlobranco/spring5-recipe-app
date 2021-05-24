package merlobranco.springframework.converters;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import merlobranco.springframework.commands.UnitOfMeasureCommand;
import merlobranco.springframework.domain.UnitOfMeasure;

class UnitOfMeasureCommandToUnitOfMeasureTest {
	
	public static final Long ID_VALUE = 1L;
    public static final String DESCRIPTION = "Description";
    
    UnitOfMeasureCommandToUnitOfMeasure converter;

	@BeforeEach
	void setUp() throws Exception {
		converter = new UnitOfMeasureCommandToUnitOfMeasure();
	}
	
	@Test
	void testNullObject() {
		assertNull(converter.convert(null));
	}
	
	@Test
	void testEmptyObject() {
		assertNotNull(converter.convert(new UnitOfMeasureCommand()));
	}
	
	@Test
	void testConvert() {
		// Given
		UnitOfMeasureCommand uomCommand = new UnitOfMeasureCommand();
		uomCommand.setId(ID_VALUE);
		uomCommand.setDescription(DESCRIPTION);
		
		// When
		UnitOfMeasure uom = converter.convert(uomCommand);
		
		// Then
		assertEquals(ID_VALUE, uom.getId());
		assertEquals(DESCRIPTION, uom.getDescription());
	}

}
