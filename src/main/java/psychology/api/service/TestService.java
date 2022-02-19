package psychology.api.service;

import java.util.List;
import java.util.Optional;

import psychology.api.dto.TestDto;
import psychology.store.entity.TestEntity;

public interface TestService {

	TestDto createOrUpdateTest(Optional<Long> testId, Long psychologistid, Optional<String> name, Optional<Boolean> isActived);
	
	
	TestEntity findTestById(Long testId);

	List<TestDto> findAllTestByNameAndPsychologist(Optional<String> filter, Optional<Long> psychologistId);


	void deleteTestById(Long testId);

}
