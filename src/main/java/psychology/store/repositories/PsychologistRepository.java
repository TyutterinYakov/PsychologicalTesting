package psychology.store.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import psychology.store.entities.PsychologistEntity;

@Repository
public interface PsychologistRepository extends JpaRepository<PsychologistEntity, Long>{

}
