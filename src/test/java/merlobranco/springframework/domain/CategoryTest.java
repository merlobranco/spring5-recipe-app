package merlobranco.springframework.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CategoryTest {
	
	Category category;

	@BeforeEach
	void setUp() throws Exception {
		category = new Category();
	}

	@Test
	void testGetId() {
		Long id = 4l;
		category.setId(id);
		assertEquals(id, category.getId());
	}

	@Test
	void testGetDescription() {
		String description = "Description";
		category.setDescription(description);
		assertEquals(description, category.getDescription());
	}
}
