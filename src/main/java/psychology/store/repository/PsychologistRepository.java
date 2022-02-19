package psychology.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import psychology.store.entity.PsychologistEntity;

@Repository
public interface PsychologistRepository extends JpaRepository<PsychologistEntity, Long>{

}
