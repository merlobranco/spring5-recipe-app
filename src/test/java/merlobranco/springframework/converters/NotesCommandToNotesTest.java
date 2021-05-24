package merlobranco.springframework.converters;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import merlobranco.springframework.commands.NotesCommand;
import merlobranco.springframework.domain.Notes;

class NotesCommandToNotesTest {
	
	public static final Long ID_VALUE = 1L;
    public static final String RECIPE_NOTES = "Notes";
    
    NotesCommandToNotes converter;

	@BeforeEach
	void setUp() throws Exception {
		converter = new NotesCommandToNotes();
	}

	@Test
	void testNullObject() {
		assertNull(converter.convert(null));
	}
	
	@Test
	void testEmptyObject() {
		assertNotNull(converter.convert(new NotesCommand()));
	}
	@Test
	void testConvert() {
		// Given
		NotesCommand notesCommand = new NotesCommand();
		notesCommand.setId(ID_VALUE);
		notesCommand.setRecipeNotes(RECIPE_NOTES);
		
		// When
		Notes notes = converter.convert(notesCommand);
		
		// Then
		assertEquals(ID_VALUE, notes.getId());
		assertEquals(RECIPE_NOTES, notes.getRecipeNotes());
	}

}
