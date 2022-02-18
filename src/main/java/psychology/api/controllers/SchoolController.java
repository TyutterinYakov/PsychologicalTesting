package psychology.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

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
	
	@PostMapping(CREATE_SCHOOL)
	public ResponseEntity<?> createSchool(@PathVariable("school_name") String schoolName){
		return ResponseEntity.ok(schoolService.createSchool(schoolName));
	}
	
	
}
