package psychology.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import psychology.api.dto.AckDto;
import psychology.api.dto.SchoolDto;
import psychology.api.services.SchoolService;

@Controller
public class SchoolController {

	private final SchoolService schoolService;
	
	@Autowired
	public SchoolController(SchoolService schoolService) {
		super();
		this.schoolService = schoolService;
	}
	private static final String CREATE_SCHOOL= "/api/school/{school_name}";
	private static final String FETCH_SCHOOLS = "/api/school";
	private static final String DELETE_SCHOOL = "/api/school/{school_id}";
	
	@PostMapping(CREATE_SCHOOL)
	public ResponseEntity<SchoolDto> createSchool(@PathVariable("school_name") String schoolName){
		return ResponseEntity.ok(schoolService.createSchool(schoolName));
	}

	@GetMapping(FETCH_SCHOOLS)
	public ResponseEntity<List<SchoolDto>> fetchSchools(
			@RequestParam String filter) {
		
		return ResponseEntity.ok(schoolService.getSchoolsByFilter(filter));
		
	}
	 
	@DeleteMapping(DELETE_SCHOOL)
	public ResponseEntity<AckDto> deleteSchool(@PathVariable("school_id") Long schoolId){
		schoolService.deleteSchool(schoolId);
		return ResponseEntity.ok(new AckDto(true));
	}
	
}
