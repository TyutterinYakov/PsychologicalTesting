package psychology.store.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import psychology.store.entity.PsychologistEntity;

@Repository
public interface PsychologistRepository extends JpaRepository<PsychologistEntity, Long>{

	Optional<PsychologistEntity> findByLoginAndPassword(String login, String password);
}
