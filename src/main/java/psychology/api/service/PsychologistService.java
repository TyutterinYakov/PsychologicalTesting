package psychology.api.service;


import psychology.store.entity.PsychologistEntity;

public interface PsychologistService {

	PsychologistEntity findPsychologistById(Long psychologistId);

}
