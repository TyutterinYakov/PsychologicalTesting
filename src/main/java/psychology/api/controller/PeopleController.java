package psychology.api.controller;

import java.time.LocalDate;
import java.util.List;

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

import psychology.api.domain.PeopleRole;
import psychology.api.dto.AckDto;
import psychology.api.dto.PeopleDto;
import psychology.api.service.AuthorizationService;
import psychology.api.service.PeopleService;

@RestController
@RequestMapping
public class PeopleController {

	private static final Logger logger = LoggerFactory.getLogger(PeopleController.class);
	
	private final PeopleService peopleService;
	private final AuthorizationService authorizationService;

	@Autowired
	public PeopleController(PeopleService peopleService, AuthorizationService authorizationService) {
		super();
		this.peopleService = peopleService;
		this.authorizationService = authorizationService;
	}
	
	
	private static final String FETCH_PEOPLES = "/api/schools/classes/peoples";
	public static final String CREATE_PEOPLE = "/api/schools/classes/{class_id}/people";
	public static final String DELETE_PEOPLE = "/api/schools/classes/peoples/{people_id}";
	public static final String GET_PEOPLE_ID_BY_LOGIN_AND_PASSWORD = "/api/schools/classes/peoples";
	
	@GetMapping(FETCH_PEOPLES)
	public ResponseEntity<List<PeopleDto>> fetchPeoples(
			@RequestParam(name="filter", required = false, defaultValue="") String filter, HttpServletRequest request){
		authorizationService.checkToken(request);
		return ResponseEntity.ok(peopleService.getAllPeopleByFilter(filter));
	}
	
	@PostMapping(CREATE_PEOPLE)
	public ResponseEntity<?> createPeople(
			@RequestParam("fio") String  peopleFio,
			@RequestParam("date_of_birth") LocalDate dateOfBirth, 
			@RequestParam("role") PeopleRole role,
			@PathVariable("class_id") Long classId,
			HttpServletRequest request){
		authorizationService.checkToken(request);
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
	public ResponseEntity<AckDto> deletePeople(
			@PathVariable("people_id") Long peopleId, HttpServletRequest request){
		authorizationService.checkToken(request);
		peopleService.deletePeopleById(peopleId);
		return new ResponseEntity<>(new AckDto(true), HttpStatus.NO_CONTENT);
	}
	
	@PostMapping(GET_PEOPLE_ID_BY_LOGIN_AND_PASSWORD)
	public ResponseEntity<?> getPeopleByLoginAndPassword(
			@RequestParam("login") String login,
			@RequestParam("password") String password){
		return ResponseEntity.ok(peopleService.getPeopleIdByLoginAndPassword(login, password));
	}
	
	
	
	
	
	
}
