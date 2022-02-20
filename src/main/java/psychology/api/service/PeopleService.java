package psychology.api.service;

import java.time.LocalDate;
import java.util.List;

import psychology.api.domain.PeopleRole;
import psychology.api.dto.PeopleDto;

public interface PeopleService {

	void deletePeopleById(Long peopleId);

	PeopleDto createPeople(String peopleFio, LocalDate dateOfBirth, PeopleRole role, Long classId);

	List<PeopleDto> getAllPeopleByFilter(String filter);

	Long getPeopleIdByLoginAndPassword(String login, String password);

}
