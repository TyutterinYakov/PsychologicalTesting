package psychology.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import psychology.api.dto.AckDto;
import psychology.api.dto.SchoolClassDto;
import psychology.api.service.SchoolClassService;

@Controller
public class SchoolClassController {

	
	private final SchoolClassService classService;

	@Autowired
	public SchoolClassController(SchoolClassService classService) {
		super();
		this.classService = classService;
	}
	
	private static final String FETCH_SCHOOL_CLASSES = "/api/schools/{school_id}/classes";
	public static final String CREATE_SCHOOL_CLASS = "/api/schools/classes/{school_id}/{class_number}/{class_letter}";
	public static final String DELETE_SCHOOL_CLASS = "/api/schools/classes/{school_id}/{class_id}";
	
	
	
	@GetMapping(FETCH_SCHOOL_CLASSES)
	public ResponseEntity<List<SchoolClassDto>> fetchSchoolClasses(
			@PathVariable("school_id") Long schoolId,
			@RequestParam(name="class_number", required = false, defaultValue="0") Integer classNumber,
			@RequestParam(name="class_letter", required = false, defaultValue = "") String classLetter){
		return ResponseEntity.ok(classService.getAllClassesBySchool(schoolId, classNumber, classLetter));
	}
	
	@PostMapping(CREATE_SCHOOL_CLASS)
	public ResponseEntity<?> createSchoolClass(
			@PathVariable("school_id") Long schoolId,
			@PathVariable("class_number") Integer classNumber, 
			@PathVariable("class_letter") String classLetter){
		return new ResponseEntity<>(
				classService.createSchoolClass(
						schoolId, 
						classNumber, 
						classLetter),
				HttpStatus.CREATED
				);
		
	}
	@DeleteMapping(DELETE_SCHOOL_CLASS)
	public ResponseEntity<?> deleteSchoolClass(
			@PathVariable("school_id") Long schoolId, 
			@PathVariable("class_id") Long classId) {
		classService.deleteSchoolClass(schoolId, classId);
		return new ResponseEntity<>(new AckDto(true), HttpStatus.NO_CONTENT);
	}
	
	
	
	
	
}
