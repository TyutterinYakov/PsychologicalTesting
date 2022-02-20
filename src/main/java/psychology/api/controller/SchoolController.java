package psychology.api.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import psychology.api.dto.AckDto;
import psychology.api.dto.SchoolDto;
import psychology.api.service.AuthorizationService;
import psychology.api.service.SchoolService;

@RequestMapping
@RestController
public class SchoolController {
private static final Logger logger = LoggerFactory.getLogger(SchoolController.class);
	
	private final SchoolService schoolService;
	private final AuthorizationService authorizationService;

	@Autowired
	public SchoolController(SchoolService schoolService, AuthorizationService authorizationService) {
		super();
		this.schoolService = schoolService;
		this.authorizationService = authorizationService;
	}

	public static final String CREATE_SCHOOL= "/api/school/{school_name}";
	public static final String FETCH_SCHOOLS = "/api/schools";
	public static final String DELETE_SCHOOL = "/api/school/{school_id}";
	
	@PostMapping(CREATE_SCHOOL)
	public ResponseEntity<?> createSchool(@PathVariable("school_name") String schoolName, HttpServletRequest request){
		authorizationService.checkToken(request);
		return new ResponseEntity<>(
				schoolService.createSchool(schoolName),
				HttpStatus.CREATED);
	}

	@GetMapping(FETCH_SCHOOLS)
	public ResponseEntity<List<SchoolDto>> fetchSchools(
			@RequestParam(name="filter", required = false) Optional<String> filter) {
		
		return ResponseEntity.ok(schoolService.getSchoolsByFilter(filter));
		
	}
	 
	@DeleteMapping(DELETE_SCHOOL)
	public ResponseEntity<AckDto> deleteSchool(@PathVariable("school_id") Long schoolId, 
			HttpServletRequest request){
		authorizationService.checkToken(request);
		schoolService.deleteSchool(schoolId);
		return new ResponseEntity<>(new AckDto(true), HttpStatus.NO_CONTENT);
	}
	
}
