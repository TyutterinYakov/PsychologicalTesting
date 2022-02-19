package psychology.api.factory;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import psychology.api.dto.SchoolDto;
import psychology.store.entities.SchoolEntity;

@Component
public class SchoolDtoFactory {

	public SchoolDto createShoolDto(SchoolEntity entity) {
		return new SchoolDto(
				entity.getId(),
				entity.getName());
				
	}
	
	public List<SchoolDto> createListSchoolDto(List<SchoolEntity> schoolsList){
		return schoolsList
				.stream()
				.map(this::createShoolDto)
				.collect(Collectors.toList());
	}
	
	
	
}
