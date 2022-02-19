package psychology.api.service;

import java.util.Optional;

import psychology.store.entity.TestEntity;

public interface TestService {

	Object createOrUpdateTest(Optional<Long> testId, Long psychologistid, Optional<String> name, Optional<Boolean> isActived);
	
	
	TestEntity findTestById(Long testId);

}
