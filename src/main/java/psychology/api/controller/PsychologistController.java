package psychology.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import psychology.api.dto.TestPeopleDto;
import psychology.api.service.PsychologistService;

@Controller
public class PsychologistController {

	private final PsychologistService psychologistService;

	@Autowired
	public PsychologistController(PsychologistService psychologistService) {
		super();
		this.psychologistService = psychologistService;
	}
	
	private static final String GET_TEST_RESULTS = "/api/psychologist/{psychologist_id}/tests/{test_id}/results";
	public static final String GET_PSYCHOLOGISTS = "/api/psychologist/{psychologist_id}";
	public static final String GET_PEOPLES_BY_CLASS = "/api/psychologist/{psychologist_id}/schools/classes/{class_id}";
	
	
	
	@GetMapping(GET_TEST_RESULTS)
	public ResponseEntity<List<TestPeopleDto>> fetchPsychologist(
			@PathVariable("psychologist_id") Long psychologistId,
			@PathVariable("test_id") Long testId){
		return ResponseEntity.ok(psychologistService.getTestResults(testId, psychologistId));
	}
	
	@PostMapping(GET_PSYCHOLOGISTS)
	public ResponseEntity<?> getPsychologists(
			@PathVariable(name="psychologist_id", required = false) Optional<Long> psychologistId){
		return ResponseEntity.ok(psychologistService.getPsychologistById(psychologistId));
	}
	@DeleteMapping(GET_PEOPLES_BY_CLASS)
	public ResponseEntity<?> getPeoplesByClass(
			@PathVariable("class_id") Long classId,
			@PathVariable("psychologist_id") Long psychologistId){
		
		return  ResponseEntity.ok(psychologistService.getPeoplesByClass(classId, psychologistId));
	}
	
	
}
