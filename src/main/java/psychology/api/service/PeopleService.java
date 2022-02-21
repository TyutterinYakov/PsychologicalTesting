package psychology.api.service;

import java.time.LocalDate;
import java.util.List;

import psychology.api.domain.PeopleRole;
import psychology.api.dto.PeopleDto;
import psychology.store.entity.PsychologistEntity;

public interface PeopleService {

	void deletePeopleById(Long peopleId, PsychologistEntity psychologistEntity);

	PeopleDto createPeople(String peopleFio, LocalDate dateOfBirth, PeopleRole role, Long classId, PsychologistEntity psychologistEntity);

	List<PeopleDto> getAllPeopleByFilter(String filter);

	Long getPeopleIdByLoginAndPassword(String login, String password);

}
