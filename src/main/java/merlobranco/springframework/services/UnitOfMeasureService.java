package merlobranco.springframework.services;

import java.util.Set;

import merlobranco.springframework.commands.UnitOfMeasureCommand;
import merlobranco.springframework.domain.UnitOfMeasure;

public interface UnitOfMeasureService extends CrudService<UnitOfMeasure, Long> {
	
	Set<UnitOfMeasureCommand> findAllCommands();

}
