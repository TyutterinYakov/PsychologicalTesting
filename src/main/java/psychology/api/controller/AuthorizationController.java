package psychology.api.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import psychology.api.service.AuthorizationService;

@Controller
public class AuthorizationController {

	private static final Logger logger = LoggerFactory.getLogger(AuthorizationController.class);
	private final AuthorizationService authorizationService;
	
	@Autowired
	public AuthorizationController(AuthorizationService authorizationService) {
		super();
		this.authorizationService = authorizationService;
	}

	public static final String GET_AUTHORIZATION_TOKEN = "/api/psychologist/authprizations";
	
	@PostMapping(GET_AUTHORIZATION_TOKEN)
	public ResponseEntity<?> getAuthorizationToken(
			@RequestParam("login") String login,
			@RequestParam("password") String password){
		String token = authorizationService.getAuthorization(login, password);
		logger.info(token);
		HttpHeaders header = new HttpHeaders();
		header.add("Authorization", token);
		return new ResponseEntity<>(header,HttpStatus.CREATED);
	}
	
	
	
	
	
}
