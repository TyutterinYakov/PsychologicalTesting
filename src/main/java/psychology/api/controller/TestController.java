package psychology.api.controller;

import java.util.Optional;

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
import psychology.api.service.TestService;

@Controller
public class TestController {

	private final TestService testService;

	@Autowired
	public TestController(TestService testService) {
		super();
		this.testService = testService;
	}
	
	private static final String FETCH_TEST = "/api/psychologist/tests/{psychologist_id}";
	public static final String CREATE_OR_UPDATE_TEST = "/api/tests";
	public static final String DELETE_TEST = "/api/tests/{test_id}";
	
	@GetMapping(FETCH_TEST)
	public ResponseEntity<?> fetchTest(
			@PathVariable(name="psychologist_id", required = false) Optional<Long> psychologistId,
			@RequestParam(name="filter", required = false) Optional<String> filter){
		return ResponseEntity.ok(testService.findAllTestByNameAndPsychologist(filter, psychologistId));
	}
	
	
	@PostMapping(CREATE_OR_UPDATE_TEST)
	public ResponseEntity<?> createOrUpdateTest(
			@RequestParam(required=false, name="test_id") Optional<Long> testId,
			@RequestParam(name="psychologist_id") Long psychologistid,
			@RequestParam(name="name", required = false) Optional<String> name,
			@RequestParam(name="isActived", required = false) Optional<Boolean> isActived){
		return ResponseEntity.ok(testService.createOrUpdateTest(testId, psychologistid, name, isActived));
	}
	
	@DeleteMapping(DELETE_TEST)
	public ResponseEntity<?> deleteTest(@PathVariable("test_id") Long testId){
		testService.deleteTestById(testId);
		return new ResponseEntity<>(new AckDto(true), HttpStatus.NO_CONTENT);
		
	}
	
	
	
	
}
