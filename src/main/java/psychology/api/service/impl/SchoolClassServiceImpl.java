package psychology.api.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import psychology.api.dto.SchoolClassDto;
import psychology.api.exception.BadRequestException;
import psychology.api.exception.NotFoundException;
import psychology.api.exception.NotPermissionException;
import psychology.api.factory.SchoolClassDtoFactory;
import psychology.api.service.SchoolClassService;
import psychology.api.service.SchoolService;
import psychology.store.entity.PeopleEntity;
import psychology.store.entity.PsychologistEntity;
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
	public SchoolClassDto createSchoolClass(Long schoolId, Integer classNumber, 
			String classLetter, PsychologistEntity psychologist) {
		SchoolEntity school = schoolService.searchSchoolById(schoolId);
		classDao.findBySchoolAndNumberAndLetterContainsIgnoreCase(
				school, classNumber, classLetter.trim().toUpperCase())
		.ifPresent((schoolOpt)->{
			throw new BadRequestException(
					String.format(
							"Класс \"%s%s\" уже есть в этой школе", 
							classNumber, classLetter
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
										school,
										psychologist
								)
						)
				);
	}

	@Override
	public void deleteSchoolClass(Long schoolId, Long classId, PsychologistEntity psychologist) {
		classDao.deleteById(findSchoolClassByIdAndSchool(classId,
				schoolService.searchSchoolById(schoolId), 
				psychologist).getId());
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
	
	
	
	
	private SchoolClassEntity findSchoolClassByIdAndSchool(Long classId, 
			SchoolEntity school, PsychologistEntity psychologist) {
		return classDao.findByIdAndSchoolAndPsychologist(classId, school, psychologist).orElseThrow(()->
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

	@Override
	public SchoolClassEntity getSchoolClassByIdAndPsychologist(Long classId, PsychologistEntity psychologist) {
		return classDao.findByIdAndPsychologist(classId, psychologist).orElseThrow(()->
		new NotFoundException(String.format(
				"Класс с идентификатором \"%s\" не найден или у вас нет доступа",
				classId))
);
	}

	public SchoolClassEntity getClassByIdAndPsychologist(Long classId,
			PsychologistEntity psychologist) {
		return classDao.findByIdAndPsychologist(classId, psychologist).orElseThrow(()->
				new NotPermissionException(
						"У вас нет прав для просмотра людей из этого класса"));
		
	}

	
	
	
	
}
