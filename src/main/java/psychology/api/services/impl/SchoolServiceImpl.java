package psychology.api.services.impl;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import psychology.api.dto.SchoolDto;
import psychology.api.exceptions.BadRequestException;
import psychology.api.exceptions.NotFoundException;
import psychology.api.factory.SchoolDtoFactory;
import psychology.api.services.SchoolService;
import psychology.store.entities.SchoolEntity;
import psychology.store.repositories.SchoolRepository;

@Service
public class SchoolServiceImpl implements SchoolService {

	private final SchoolRepository schoolDao;
	private final SchoolDtoFactory schoolFactory;
	
	@Autowired
	public SchoolServiceImpl(SchoolRepository schoolDao, SchoolDtoFactory schoolFactory) {
		super();
		this.schoolDao = schoolDao;
		this.schoolFactory = schoolFactory;
	}

	@Override
	public SchoolDto createSchool(String schoolName) {
		if(schoolName.isBlank()) {
			throw new BadRequestException("Не указано название школы");
		}
		schoolDao.findByNameContainsIgnoreCase(schoolName)
			.ifPresent((school)->{throw new BadRequestException(
					String.format(
							"Школа с названием \"%s\" уже есть в базе", 
							schoolName
					)
			);
			});
		return schoolFactory.createShoolDto(schoolDao.saveAndFlush(new SchoolEntity(schoolName)));
	}

	@Override
	public List<SchoolDto> getSchoolsByFilter(String filter) {
		boolean isFiltered = !filter.isBlank();
		List<SchoolEntity> schools = new LinkedList<>();
		if(isFiltered) {
			schools = schoolDao.findAllByFilter(filter);
		} else {
			schools = schoolDao.findAll();
		}
		return schoolFactory
				.createListSchoolDto(schools);
	}

	@Override
	public void deleteSchool(Long schoolId) {
		searchSchoolById(schoolId);
		schoolDao.deleteById(schoolId);
	}
	
	@Override
	public SchoolEntity searchSchoolById(Long schoolId) {
		if(schoolId==0||schoolId==null) {
			throw new BadRequestException("Идентификатор школы не может быть пустым или равным 0");
		}
		return schoolDao.findById(schoolId).orElseThrow(()->
				new NotFoundException(String.format("Школа с id \"%s\" не найдена", schoolId)));
	}
	
	
	
	
	private SchoolEntity searchSchoolByName(String schoolName) {
		if(schoolName.isBlank()) {
			throw new BadRequestException("Имя школы не может быть пустым");
		}
		return schoolDao.findByNameContainsIgnoreCase(schoolName).orElseThrow(()->
					new NotFoundException(String.format(
							"Школы с названием \"%s\" нет в базе", 
							schoolName
					))
				);
	}
	


	
	
	
	
}

