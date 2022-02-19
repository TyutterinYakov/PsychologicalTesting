package psychology.api.service.impl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import psychology.api.domain.PeopleRole;
import psychology.api.dto.PeopleDto;
import psychology.api.exception.NotFoundException;
import psychology.api.factory.PeopleDtoFactory;
import psychology.api.service.PeopleService;
import psychology.api.service.SchoolClassService;
import psychology.store.entity.PeopleEntity;
import psychology.store.entity.SchoolClassEntity;
import psychology.store.repository.PeopleRepository;

@Service
public class PeopleServiceImpl implements PeopleService {

	private final PeopleRepository peopleDao;
	private final PeopleDtoFactory peopleDtoFactory;
	private final SchoolClassService classService;
	
	@Autowired
	public PeopleServiceImpl(PeopleRepository peopleDao, PeopleDtoFactory peopleDtoFactory,
			SchoolClassService classService) {
		super();
		this.peopleDao = peopleDao;
		this.peopleDtoFactory = peopleDtoFactory;
		this.classService = classService;
	}

	@Override
	public void deletePeopleById(Long peopleId) {
		peopleDao.findById(peopleId).orElseThrow(()->
				new NotFoundException(
						String.format(
								"Человек с идентификатором \"%s\" не найден",
								peopleId)
				)
		);
	}

	@Override
	public PeopleDto createPeople(String peopleFio, LocalDate dateOfBirth, PeopleRole role, Long classId) {
		SchoolClassEntity classEntity = classService.getSchoolClassById(classId);
		
		return peopleDtoFactory
				.createPeopleDto(
						new PeopleEntity(
								peopleFio, 
								dateOfBirth, 
								role, 
								classEntity)
				);
	}

	@Override
	public List<PeopleDto> getAllPeopleByFilter(String filter) {
		
		if(filter.isBlank()) {
			return peopleDtoFactory.createListPeopleDto(peopleDao.findByOrderByFioAsc());
		}
		return peopleDtoFactory.createListPeopleDto(peopleDao.findAllByFilter(filter));
	}
	
	
	
	
	
	
	
	
	
	
}
