package psychology.api.service.impl;


import java.util.ArrayList;
import java.util.List;
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
		PsychologistEntity psychologist = psychologistService.findPsychologistById(psychologistId);
		if(!testId.isPresent()) {
			name.ifPresentOrElse(null, ()->{
				throw new BadRequestException("Имя теста не может быть пустым");});
			return testDtoFactory.createTestDto(
					testDao.saveAndFlush(
						new TestEntity(
							name.get(),
							psychologist
							)
						)
					);
		} else {
			TestEntity test = findTestByIdAndPsychologist(testId.get(), psychologist);
			name.ifPresent(test::setName);
			isActived.ifPresent(test::setStarted);
			return testDtoFactory.createTestDto(test);
		}
	}

	@Override
	public TestEntity findTestByIdAndPsychologist(Long testId, PsychologistEntity psychologist) {
		return testDao.findByIdAndPsychologist(testId, psychologist).orElseThrow(()->
				new NotFoundException(String.format(
						"Ой, что-то пошло не так, тест с идентификатором \"%s\" "
						+ "не найден или он не принадлежит вам",
						testId)));
	}

	@Override
	public List<TestDto> findAllTestByNameAndPsychologist(Optional<String> filter, Optional<Long> psychologistId) {
		List <TestEntity> tests = new ArrayList<>();
		if(psychologistId.isPresent()) {
			PsychologistEntity psychologist =  psychologistService.findPsychologistById(psychologistId.get());
			if(filter.isPresent()) {
				tests = testDao.findTestByFilterAndPsychologist(filter.get(), psychologist);
			} else {
				tests = testDao.findByPsychologist(psychologist);
			}
		} else if(filter.isPresent()) {
			tests = testDao.findByFilter(filter.get());
		} else {
			tests = testDao.findAll();
		}
		return testDtoFactory.createListTestDto(tests);
		
	}

	@Override
	public void deleteTestById(Long testId, PsychologistEntity psychologist) {
		testDao.deleteById(
				findTestByIdAndPsychologist(
						testId, psychologist).getId()
				);
	}

	
	
	
	
	
	
	
	

}
