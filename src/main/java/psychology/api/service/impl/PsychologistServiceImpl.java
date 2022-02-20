package psychology.api.service.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
import psychology.api.service.TestService;
import psychology.store.entity.PsychologistEntity;
import psychology.store.entity.TestEntity;
import psychology.store.repository.PsychologistRepository;
import psychology.store.repository.TestPeopleRepository;
import psychology.store.repository.TestRepository;

@Service
public class PsychologistServiceImpl implements PsychologistService {

	
	private final TestService testService;
	private final PsychologistRepository psychologistDao;
	private final TestPeopleRepository testPeopleDao;
	private final TestPeopleDtoFactory testPeopleDtoFactory;
	private final PsychologistDtoFactory psychologistDtoFactory;
	private final SchoolClassServiceImpl classService;
	private final PeopleDtoFactory peopleDtoFactory;

	@Autowired
	public PsychologistServiceImpl(TestService testService, PsychologistRepository psychologistDao,
			TestPeopleRepository testPeopleDao, TestPeopleDtoFactory testPeopleDtoFactory,
			PsychologistDtoFactory psychologistDtoFactory, SchoolClassServiceImpl classService,
			PeopleDtoFactory peopleDtoFactory) {
		super();
		this.testService = testService;
		this.psychologistDao = psychologistDao;
		this.testPeopleDao = testPeopleDao;
		this.testPeopleDtoFactory = testPeopleDtoFactory;
		this.psychologistDtoFactory = psychologistDtoFactory;
		this.classService = classService;
		this.peopleDtoFactory = peopleDtoFactory;
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
		PsychologistEntity psychologist =  findPsychologistById(psychologistId);
		TestEntity test = testService.findTestById(testId);
		
		return testPeopleDtoFactory.createListTestPeopleDto(
				testPeopleDao.findAllByTestAndPsychologist(test, psychologist));
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
	
	
	
	
	

}
