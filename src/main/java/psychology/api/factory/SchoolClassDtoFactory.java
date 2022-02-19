package psychology.api.factory;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import psychology.api.dto.SchoolClassDto;
import psychology.store.entity.SchoolClassEntity;

@Component
public class SchoolClassDtoFactory {

	private final SchoolDtoFactory schoolFactory;
	
	@Autowired
	public SchoolClassDtoFactory(SchoolDtoFactory schoolFactory) {
		super();
		this.schoolFactory = schoolFactory;
	}



	public SchoolClassDto createSchoolClassDto(SchoolClassEntity classEntity) {
		return new SchoolClassDto(
				classEntity.getId(),
				classEntity.getNumber(),
				classEntity.getLetter(),
				schoolFactory.createShoolDto(
						classEntity.getSchool()));
	}
	
	public List<SchoolClassDto> createListSchoolClassesDto(List<SchoolClassEntity> listClasses){
		return listClasses
				.stream()
				.map(this::createSchoolClassDto)
				.collect(Collectors.toList());
	}
}
