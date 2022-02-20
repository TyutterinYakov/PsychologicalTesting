package psychology.api.factory;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import psychology.api.dto.PeopleDto;
import psychology.store.entity.PeopleEntity;

@Component
public class PeopleDtoFactory {

	public PeopleDto createPeopleDto(PeopleEntity people) {
		return new PeopleDto(
				people.getId(), 
				people.getFio(), 
				people.getDateOfBirth(), 
				people.getRole(), 
				people.getSchoolClass().getId(),
				people.getLogin(),
				people.getPassword()
				);
	}
	
	public List<PeopleDto> createListPeopleDto(List<PeopleEntity> peoples){
		return peoples
				.stream()
				.map(this::createPeopleDto)
				.collect(Collectors.toList());
	}

	
	
}
