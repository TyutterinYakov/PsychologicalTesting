package psychology.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import psychology.api.dto.PeopleDto;
import psychology.api.service.PsychologistService;

@Controller
public class PsychologistController {

	private final PsychologistService psychologistService;

	@Autowired
	public PsychologistController(PsychologistService psychologistService) {
		super();
		this.psychologistService = psychologistService;
	}
	
	private static final String FETCH_PSYCHOLOGIST = "/api/psychologist";
	public static final String CREATE_PSYCHOLOGIST = "/api/psychologist";
	public static final String DELETE_PSYCHOLOGIST = "/api/psychologist/{psychologist_id}";
	
	
	
	@GetMapping(FETCH_PSYCHOLOGIST)
	public ResponseEntity<List<PeopleDto>> fetchPsychologist(){
		return null;
	}
	
	@PostMapping(CREATE_PSYCHOLOGIST)
	public ResponseEntity<?> createPsychologist(){
		return null;
	}
	@DeleteMapping(DELETE_PSYCHOLOGIST)
	public ResponseEntity<?> deletePeople(){
		return null;
	}
	
	
}
