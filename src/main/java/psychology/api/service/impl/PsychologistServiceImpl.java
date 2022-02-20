package psychology.api.service.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import psychology.api.dto.PeopleDto;
import psychology.api.dto.PsychologistDto;
import psychology.api.dto.TestPeopleDto;
import psychology.api.exception.NotFoundException;
import psychology.api.factory.PeopleDtoFactory;
import psychology.api.factory.PsychologistDtoFactory;
import psychology.api.factory.TestPeopleDtoFactory;
import psychology.api.service.PsychologistService;
import psychology.store.entity.PsychologistEntity;
import psychology.store.entity.TestEntity;
import psychology.store.repository.PsychologistRepository;
import psychology.store.repository.TestPeopleRepository;
import psychology.store.repository.TestRepository;

@Service
public class PsychologistServiceImpl implements PsychologistService {

	
	private final PsychologistRepository psychologistDao;
	private final TestPeopleRepository testPeopleDao;
	private final TestPeopleDtoFactory testPeopleDtoFactory;
	private final PsychologistDtoFactory psychologistDtoFactory;
	private final SchoolClassServiceImpl classService;
	private final PeopleDtoFactory peopleDtoFactory;
	private final TestRepository testDao;

	@Autowired
	public PsychologistServiceImpl(PsychologistRepository psychologistDao, TestPeopleRepository testPeopleDao,
			TestPeopleDtoFactory testPeopleDtoFactory, PsychologistDtoFactory psychologistDtoFactory,
			SchoolClassServiceImpl classService, PeopleDtoFactory peopleDtoFactory, TestRepository testDao) {
		super();
		this.psychologistDao = psychologistDao;
		this.testPeopleDao = testPeopleDao;
		this.testPeopleDtoFactory = testPeopleDtoFactory;
		this.psychologistDtoFactory = psychologistDtoFactory;
		this.classService = classService;
		this.peopleDtoFactory = peopleDtoFactory;
		this.testDao = testDao;
	}

	@Override
	public PsychologistEntity findPsychologistById(Long psychologistId) {
		return psychologistDao.findById(psychologistId).orElseThrow(()->
			new NotFoundException(String.format(
					"Психолог с индентификатором \"%s\" не найден", 
					psychologistId))
		);
	}

	@Override
	public List<TestPeopleDto> getTestResults(Long testId, Long psychologistId) {
		findPsychologistById(psychologistId);
		return testPeopleDtoFactory.createListTestPeopleDto(
				testPeopleDao.findAllByTest(findTestById(testId)));
	}

	@Override
	public List<PsychologistDto> getPsychologistById(Optional<Long> psychologistId) {
		List <PsychologistEntity> psychologists = new LinkedList<>();
		psychologistId.ifPresentOrElse((p)->{
			psychologistDao.findById(p)
			.ifPresent(ps->{
				psychologists.add(ps);});},
				()->	
					psychologists.addAll(
						psychologistDao.findAll()
					)
		);
		return psychologistDtoFactory
				.createListPsychologistDto(
						psychologists);
	}

	@Override
	public List<PeopleDto> getPeoplesByClass(Long classId, Long psychologistId) {
		findPsychologistById(psychologistId);
		return peopleDtoFactory
				.createListPeopleDto(
					classService
					.getSchoolClassById(classId)
					.getPeoples());
	}
	
	
	private TestEntity findTestById(Long testId) {
		return testDao.findById(testId).orElseThrow(()->
			new NotFoundException(String.format("Тест с идентификатором \"%s\" не найден", testId))
		);
	}
	
	
	
	
	

}
