package psychology.api.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import psychology.api.dto.SchoolClassDto;
import psychology.api.exception.BadRequestException;
import psychology.api.exception.NotFoundException;
import psychology.api.factory.SchoolClassDtoFactory;
import psychology.api.service.SchoolClassService;
import psychology.api.service.SchoolService;
import psychology.store.entity.SchoolClassEntity;
import psychology.store.entity.SchoolEntity;
import psychology.store.repository.SchoolClassRepository;

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
	public List<SchoolClassDto> getAllClassesBySchool(
			Long schoolId, Integer classNumber, String classLetter) {
		List<SchoolClassEntity> classes = schoolService.searchSchoolById(schoolId).getSchoolClasses();
		if(classNumber!=0) {
			 classes = schoolService.searchSchoolById(schoolId)
				.getSchoolClasses().stream()
					.filter(cl->(cl.getNumber()
									.equals(classNumber))
					).collect(Collectors.toList());
			 if(!classLetter.isBlank()) {
				 classes = classes.stream()
						 	.filter(cl->(cl.getLetter()
						 			.equalsIgnoreCase(
						 					classLetter.trim()))
						 	).collect(Collectors.toList());
			 }
		
		} else if(!classLetter.isBlank()) {
			classes = classes.stream()
					.filter(cl->
						(classLetter.trim()
								.equals(cl.getLetter()))
					).collect(Collectors.toList());
		}
		
		
		return classFactory.createListSchoolClassesDto(classes);
	}
	
	
	
	
	private SchoolClassEntity findSchoolClassByIdAndSchool(Long classId, SchoolEntity school) {
		return classDao.findByIdAndSchool(classId, school).orElseThrow(()->
				new NotFoundException(String.format("Класс с идентификатором \"%s\" не найден", classId)));
	}
	
	@Override
	public SchoolClassEntity getSchoolClassById(Long classId) {
		return classDao.findById(classId).orElseThrow(()->
				new NotFoundException(String.format(
						"Класс с идентификатором \"%s\" не найден",
						classId))
		);
	}

	
	
	
	
}
