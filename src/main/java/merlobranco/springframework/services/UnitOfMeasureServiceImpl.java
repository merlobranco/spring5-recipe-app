package merlobranco.springframework.services;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.stereotype.Service;

import merlobranco.springframework.commands.UnitOfMeasureCommand;
import merlobranco.springframework.converters.UnitOfMeasureToUnitOfMeasureCommand;
import merlobranco.springframework.domain.UnitOfMeasure;
import merlobranco.springframework.repositories.UnitOfMeasureRepository;

@Service
public class UnitOfMeasureServiceImpl extends JpaService<UnitOfMeasure, Long> implements UnitOfMeasureService {

	private final UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureToUnitOfMeasureCommand;
	private final UnitOfMeasureRepository unitOfMeasureRepository;

	public UnitOfMeasureServiceImpl(UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureToUnitOfMeasureCommand, UnitOfMeasureRepository unitOfMeasureRepository) {
		super(unitOfMeasureRepository);
		this.unitOfMeasureToUnitOfMeasureCommand = unitOfMeasureToUnitOfMeasureCommand;
		this.unitOfMeasureRepository = unitOfMeasureRepository;
	}

	@Override
	public Set<UnitOfMeasureCommand> findAllCommands() {
		return StreamSupport.stream(unitOfMeasureRepository.findAll()
                .spliterator(), false)
                .map(unitOfMeasureToUnitOfMeasureCommand::convert)
                .collect(Collectors.toSet());
	}

}
