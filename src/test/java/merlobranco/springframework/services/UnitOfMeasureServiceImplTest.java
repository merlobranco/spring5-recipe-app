package merlobranco.springframework.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import merlobranco.springframework.commands.UnitOfMeasureCommand;
import merlobranco.springframework.converters.UnitOfMeasureToUnitOfMeasureCommand;
import merlobranco.springframework.domain.UnitOfMeasure;
import merlobranco.springframework.repositories.UnitOfMeasureRepository;

@ExtendWith(MockitoExtension.class)
class UnitOfMeasureServiceImplTest {
	
	@Mock
	public UnitOfMeasureRepository unitOfMeasureRepository;
	
	@InjectMocks
	UnitOfMeasureServiceImpl unitOfMeasureService;
	
	@Mock
	UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureToUnitOfMeasureCommand;
	
	public static final Long ID = 1L;
	
	UnitOfMeasure unitOfMeasure;
	Set<UnitOfMeasure> uomData;
	UnitOfMeasureCommand uomCommand;
	

	@BeforeEach
	void setUp() throws Exception {
		unitOfMeasure = new UnitOfMeasure();
		unitOfMeasure.setId(ID);
		uomData = new HashSet<>();
		uomData.add(unitOfMeasure);
		uomCommand = new UnitOfMeasureCommand();
		uomCommand.setId(ID);
	}

	@Test
	void testFindAllCommands() {
		// Given
		when(unitOfMeasureRepository.findAll()).thenReturn(uomData);
        when(unitOfMeasureToUnitOfMeasureCommand.convert(any())).thenReturn(uomCommand);
		
        // When
		Set<UnitOfMeasureCommand> uomCommands = unitOfMeasureService.findAllCommands();
		
		// Then
		assertEquals(uomCommands.size(), 1);
		verify(unitOfMeasureRepository, times(1)).findAll();
		verify(unitOfMeasureToUnitOfMeasureCommand, times(1)).convert(any());
	}

}
