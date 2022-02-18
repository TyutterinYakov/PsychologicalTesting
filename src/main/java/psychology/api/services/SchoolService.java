package psychology.api.services;

import org.springframework.http.ResponseEntity;

import psychology.store.entities.SchoolEntity;

public interface SchoolService {

	SchoolEntity createSchool(String schoolName);

}
