package psychology.api.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import psychology.api.dto.TestPeopleDto;
import psychology.api.service.AuthorizationService;
import psychology.api.service.PsychologistService;

@RestController
@RequestMapping
public class PsychologistController {

	private final PsychologistService psychologistService;
	private final AuthorizationService authorizationService;

	@Autowired
	public PsychologistController(PsychologistService psychologistService, 
			AuthorizationService authorizationService) {
		super();
		this.psychologistService = psychologistService;
		this.authorizationService = authorizationService;
	}
	private static final String GET_TEST_RESULTS = "/api/psychologist/{psychologist_id}/tests/{test_id}/results";
	public static final String GET_PSYCHOLOGISTS = "/api/psychologist/{psychologist_id}";
	public static final String GET_PEOPLES_BY_CLASS = "/api/psychologist/{psychologist_id}/schools/classes/{class_id}";
	
	
	@GetMapping(GET_TEST_RESULTS)
	public ResponseEntity<List<TestPeopleDto>> fetchPsychologist(
			@PathVariable("test_id") Long testId,
			HttpServletRequest request){
		return ResponseEntity.ok(psychologistService.getTestResults(testId,
				authorizationService.checkTokenReturnPsychologist(request)));
	}
	
	@PostMapping(GET_PSYCHOLOGISTS)
	public ResponseEntity<?> getPsychologists(
			@PathVariable(name="psychologist_id", required = false) Optional<Long> psychologistId){
		return ResponseEntity.ok(psychologistService.getPsychologistById(psychologistId));
	}
	@GetMapping(GET_PEOPLES_BY_CLASS)
	public ResponseEntity<?> getPeoplesByClass(
			@PathVariable("class_id") Long classId,
			HttpServletRequest request){
		return  ResponseEntity.ok(psychologistService.getPeoplesByClass(classId, 
				authorizationService.checkTokenReturnPsychologist(request)));
	}
	
	
}
