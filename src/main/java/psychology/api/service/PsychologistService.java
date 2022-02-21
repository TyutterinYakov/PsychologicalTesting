package psychology.api.service;


import java.util.List;
import java.util.Optional;

import psychology.api.dto.PeopleDto;
import psychology.api.dto.PsychologistDto;
import psychology.api.dto.TestPeopleDto;
import psychology.store.entity.PsychologistEntity;

public interface PsychologistService {

	PsychologistEntity findPsychologistById(Long psychologistId);

	List<TestPeopleDto> getTestResults(Long testId, PsychologistEntity psychologist);

	List<PsychologistDto> getPsychologistById(Optional<Long> psychologistId);

	List<PeopleDto> getPeoplesByClass(Long classId, PsychologistEntity psychologist);

}
