package merlobranco.springframework.converters;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import merlobranco.springframework.commands.CategoryCommand;
import merlobranco.springframework.domain.Category;

class CategoryCommandToCategoryTest {
	
	public static final Long ID_VALUE = 1L;
    public static final String DESCRIPTION = "Description";
    
    CategoryCommandToCategory conveter;

	@BeforeEach
	void setUp() throws Exception {
		conveter = new CategoryCommandToCategory();
	}
	
	@Test
    void testNullObject() throws Exception {
        assertNull(conveter.convert(null));
    }
	
	@Test
    void testEmptyObject() throws Exception {
        assertNotNull(conveter.convert(new CategoryCommand()));
    }

	@Test
	void testConvert() {
		// Given
        CategoryCommand categoryCommand = new CategoryCommand();
        categoryCommand.setId(ID_VALUE);
        categoryCommand.setDescription(DESCRIPTION);

        // When
        Category category = conveter.convert(categoryCommand);

        // Then
        assertEquals(ID_VALUE, category.getId());
        assertEquals(DESCRIPTION, category.getDescription());
	}

}
