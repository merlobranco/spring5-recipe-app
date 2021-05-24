package merlobranco.springframework.converters;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import merlobranco.springframework.commands.CategoryCommand;
import merlobranco.springframework.domain.Category;

class CategoryToCategoryCommandTest {
	
	public static final Long ID_VALUE = 1L;
    public static final String DESCRIPTION = "Description";
    
    public CategoryToCategoryCommand converter;
    
    
	@BeforeEach
	void setUp() throws Exception {
		converter = new CategoryToCategoryCommand();
	}

	@Test
	void testNullObject() {
		assertNull(converter.convert(null));
	}
	
	@Test
	void testEmptyObject() {
		assertNotNull(converter.convert(new Category()));
	}
	
	@Test
	void testConvert() {
		// Given
		Category category = new Category();
		category.setId(ID_VALUE);
		category.setDescription(DESCRIPTION);
		
		// When
		CategoryCommand categoryCommand = converter.convert(category);
		
		// Then
		assertEquals(ID_VALUE, categoryCommand.getId());
		assertEquals(DESCRIPTION, categoryCommand.getDescription());
	}

}
