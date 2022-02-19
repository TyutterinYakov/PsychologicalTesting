package psychology.api.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import psychology.api.exception.NotFoundException;
import psychology.api.service.PsychologistService;
import psychology.store.entity.PsychologistEntity;
import psychology.store.repository.PsychologistRepository;

@Service
public class PsychologistServiceImpl implements PsychologistService {

	
	private final PsychologistRepository psychologistDao;
	
	@Autowired
	public PsychologistServiceImpl(PsychologistRepository psychologistDao) {
		super();
		this.psychologistDao = psychologistDao;
	}


	@Override
	public PsychologistEntity findPsychologistById(Long psychologistId) {
		return psychologistDao.findById(psychologistId).orElseThrow(()->
			new NotFoundException(String.format(
					"Психолог с индетификатором \"%s\" не найден", 
					psychologistId))
		);
	}
	
	
	

}
