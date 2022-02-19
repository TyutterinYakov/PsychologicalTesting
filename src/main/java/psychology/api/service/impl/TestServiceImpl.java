package psychology.api.service.impl;


import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import psychology.api.dto.TestDto;
import psychology.api.exception.BadRequestException;
import psychology.api.exception.NotFoundException;
import psychology.api.factory.TestDtoFactory;
import psychology.api.service.PsychologistService;
import psychology.api.service.TestService;
import psychology.store.entity.PsychologistEntity;
import psychology.store.entity.TestEntity;
import psychology.store.repository.TestRepository;

@Service
public class TestServiceImpl implements TestService {
	
	private final TestRepository testDao;
	private final PsychologistService psychologistService;
	private final TestDtoFactory testDtoFactory;
	
	@Autowired
	public TestServiceImpl(TestRepository testDao, PsychologistService psychologistService,
			TestDtoFactory testDtoFactory) {
		super();
		this.testDao = testDao;
		this.psychologistService = psychologistService;
		this.testDtoFactory = testDtoFactory;
	}

	@Override
	@Transactional
	public TestDto createOrUpdateTest(Optional<Long> testId, Long psychologistId,
			Optional<String> name, Optional<Boolean> isActived) {
		if(!testId.isPresent()) {
			name.ifPresentOrElse(null, ()->{
				throw new BadRequestException("Имя теста не может быть пустым");});
			return testDtoFactory.createTestDto(
					testDao.saveAndFlush(
						new TestEntity(
							name.get(), 
							psychologistService.
							findPsychologistById(psychologistId)
							)
						)
					);
		} else {
			TestEntity test = findTestById(testId.get());
			name.ifPresent(test::setName);
			isActived.ifPresent(test::setStarted);
			return testDtoFactory.createTestDto(test);
		}
	}

	@Override
	public TestEntity findTestById(Long testId) {
		return testDao.findById(testId).orElseThrow(()->
				new NotFoundException(String.format(
						"Ой, что-то пошло не так, тест с идентификатором \"%s\" не найден",
						testId)));
	}
	
	
	
	
	
	
	
	

}
