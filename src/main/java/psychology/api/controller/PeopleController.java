package psychology.api.controller;

import java.time.LocalDate;
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

import psychology.api.domain.PeopleRole;
import psychology.api.dto.AckDto;
import psychology.api.dto.PeopleDto;
import psychology.api.service.PeopleService;

@Controller
public class PeopleController {

	private final PeopleService peopleService;

	@Autowired
	public PeopleController(PeopleService peopleService) {
		super();
		this.peopleService = peopleService;
	}
	
	private static final String FETCH_PEOPLES = "/api/schools/classes/peoples";
	public static final String CREATE_PEOPLE = "/api/schools/classes/{class_id}/people";
	public static final String DELETE_PEOPLE = "/api/schools/classes/peoples/{people_id}";
	
	
	
	@GetMapping(FETCH_PEOPLES)
	public ResponseEntity<List<PeopleDto>> fetchPeoples(
			@RequestParam(name="filter", required = false, defaultValue="") String filter){
		return ResponseEntity.ok(peopleService.getAllPeopleByFilter(filter));
	}
	
	@PostMapping(CREATE_PEOPLE)
	public ResponseEntity<?> createPeople(
			@RequestParam("fio") String  peopleFio,
			@RequestParam("date_of_birth") LocalDate dateOfBirth, 
			@RequestParam("role") PeopleRole role,
			@PathVariable("class_id") Long classId){
		return new ResponseEntity<>(
				peopleService.createPeople(
						peopleFio.trim(), 
						dateOfBirth, 
						role, 
						classId),
				HttpStatus.CREATED
				);
		
	}
	@DeleteMapping(DELETE_PEOPLE)
	public ResponseEntity<?> deletePeople(
			@PathVariable("people_id") Long peopleId) {
		peopleService.deletePeopleById(peopleId);
		return new ResponseEntity<>(new AckDto(true), HttpStatus.NO_CONTENT);
	}
	
	
	
	
	
	
}
