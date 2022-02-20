package psychology.store.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import psychology.store.entity.PsychologistEntity;
import psychology.store.entity.TokenEntity;

@Repository
public interface TokenRepository extends JpaRepository<TokenEntity, String>{

	Optional<TokenEntity> findByPsychologist(PsychologistEntity psychologist);

	Optional<TokenEntity> findByToken(String token);

}
