package psychology.api.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import psychology.api.exceptions.BadRequestException;
import psychology.api.services.SchoolService;
import psychology.store.entities.SchoolEntity;
import psychology.store.repositories.SchoolRepository;

@Service
public class SchoolServiceImpl implements SchoolService {

	private final SchoolRepository schoolDao;
	
	@Autowired
	public SchoolServiceImpl(SchoolRepository schoolDao) {
		super();
		this.schoolDao = schoolDao;
	}

	@Override
	public SchoolEntity createSchool(String schoolName) {
		schoolDao.findByNameContainsIgnoreCase(schoolName)
			.ifPresent((school)->{throw new BadRequestException(
					"Такая школа уже есть в базе");});
		return schoolDao.saveAndFlush(new SchoolEntity(schoolName));
	}

	
	
	
	
}

