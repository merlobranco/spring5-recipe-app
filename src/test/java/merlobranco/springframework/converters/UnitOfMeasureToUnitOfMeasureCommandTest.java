package merlobranco.springframework.converters;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import merlobranco.springframework.commands.UnitOfMeasureCommand;
import merlobranco.springframework.domain.UnitOfMeasure;

class UnitOfMeasureToUnitOfMeasureCommandTest {
	
	public static final Long ID_VALUE = 1L;
    public static final String DESCRIPTION = "Description";
    
    UnitOfMeasureToUnitOfMeasureCommand converter;

	@BeforeEach
	void setUp() throws Exception {
		converter = new UnitOfMeasureToUnitOfMeasureCommand();
	}

	@Test
	void testNullObject() {
		assertNull(converter.convert(null));
	}
	
	@Test
	void testEmptyObject() {
		assertNotNull(converter.convert(new UnitOfMeasure()));
	}
	
	@Test
	void testConvert() {
		// Given
		UnitOfMeasure uom = new UnitOfMeasure();
		uom.setId(ID_VALUE);
		uom.setDescription(DESCRIPTION);
		
		// When
		UnitOfMeasureCommand uomCommand = converter.convert(uom);
		
		// Then
		assertEquals(ID_VALUE, uomCommand.getId());
		assertEquals(DESCRIPTION, uomCommand.getDescription());
	}

}
