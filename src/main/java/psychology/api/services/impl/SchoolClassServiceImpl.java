package psychology.api.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import psychology.api.dto.SchoolClassDto;
import psychology.api.exceptions.BadRequestException;
import psychology.api.exceptions.NotFoundException;
import psychology.api.factory.SchoolClassDtoFactory;
import psychology.api.services.SchoolClassService;
import psychology.api.services.SchoolService;
import psychology.store.entities.SchoolClassEntity;
import psychology.store.entities.SchoolEntity;
import psychology.store.repositories.SchoolClassRepository;

@Service
public class SchoolClassServiceImpl implements SchoolClassService{

	private final SchoolClassRepository classDao;
	private final SchoolClassDtoFactory classFactory;
	private final SchoolService schoolService;

	@Autowired
	public SchoolClassServiceImpl(SchoolClassRepository classDao, SchoolClassDtoFactory classFactory,
			SchoolService schoolService) {
		super();
		this.classDao = classDao;
		this.classFactory = classFactory;
		this.schoolService = schoolService;
	}

	@Override
	public SchoolClassDto createSchoolClass(Long schoolId, Integer classNumber, String classLetter) {
		SchoolEntity school = schoolService.searchSchoolById(schoolId);
		classDao.findBySchoolAndNumberAndLetterContainsIgnoreCase(
				school, classNumber, classLetter.trim().toUpperCase())
		.ifPresent((schoolOpt)->{
			throw new BadRequestException(
					String.format(
							"Класс \"%s\" уже есть в этой школе", 
							classNumber+classLetter
					)
			);
		});
		return classFactory
				.createSchoolClassDto(
						classDao.saveAndFlush(
								new SchoolClassEntity(
										classNumber, 
										classLetter
											.strip()
											.toUpperCase(), 
										school
								)
						)
				);
	}

	@Override
	public void deleteSchoolClass(Long schoolId, Long classId) {
		findSchoolClassByIdAndSchool(classId, schoolService.searchSchoolById(schoolId));
		classDao.deleteById(classId);
	}
	
	@Override
	public List<SchoolClassDto> getAllClassesBySchool(Long schoolId) {
		
		return classFactory
				.createListSchoolClassesDto(
						schoolService.searchSchoolById(schoolId)
						.getSchoolClasses()
				);
	}
	
	
	
	
	private SchoolClassEntity findSchoolClassByIdAndSchool(Long classId, SchoolEntity school) {
		return classDao.findByIdAndSchool(classId, school).orElseThrow(()->
				new NotFoundException(String.format("Класс с идентификатором \"%s\" не найден", classId)));
	}

	
	
	
	
}
