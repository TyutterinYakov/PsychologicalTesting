package psychology.api.service;

import java.util.List;
import java.util.Optional;

import psychology.api.dto.TestDto;
import psychology.store.entity.PsychologistEntity;
import psychology.store.entity.TestEntity;

public interface TestService {

	TestDto createOrUpdateTest(Optional<Long> testId, Long psychologistid, Optional<String> name, Optional<Boolean> isActived);
	
	
	TestEntity findTestByIdAndPsychologist(Long testId, PsychologistEntity psychologist);

	List<TestDto> findAllTestByNameAndPsychologist(Optional<String> filter, Optional<Long> psychologistId);


	void deleteTestById(Long testId, PsychologistEntity psychologist);

}
