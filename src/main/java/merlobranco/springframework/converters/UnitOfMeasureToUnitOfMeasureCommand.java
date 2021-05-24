package merlobranco.springframework.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import lombok.Synchronized;
import merlobranco.springframework.commands.UnitOfMeasureCommand;
import merlobranco.springframework.domain.UnitOfMeasure;

@Component
public class UnitOfMeasureToUnitOfMeasureCommand implements Converter<UnitOfMeasure, UnitOfMeasureCommand> {

	@Synchronized
	@Nullable
	@Override
	public UnitOfMeasureCommand convert(UnitOfMeasure unitOfMeasure) {
		if (unitOfMeasure == null) {
			return null;
		}
		
		final UnitOfMeasureCommand uomc = new UnitOfMeasureCommand();
		uomc.setId(unitOfMeasure.getId());
		uomc.setDescription(unitOfMeasure.getDescription());
		return uomc;
	}

}
