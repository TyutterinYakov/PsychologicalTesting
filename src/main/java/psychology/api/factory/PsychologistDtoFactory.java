package psychology.api.factory;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import psychology.api.dto.PsychologistDto;
import psychology.store.entity.PsychologistEntity;

@Component
public class PsychologistDtoFactory {

	
	
	public PsychologistDto createPsychologistDto(PsychologistEntity entity) {
		
		return new PsychologistDto(
				entity.getId(), 
				entity.getFio());
	}
	
	
	
	public List<PsychologistDto> createListPsychologistDto(List<PsychologistEntity> entities){
		
		return entities
				.stream()
				.map(this::createPsychologistDto)
				.collect(Collectors.toList());
	}
}
