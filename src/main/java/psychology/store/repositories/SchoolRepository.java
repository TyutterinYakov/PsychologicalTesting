package psychology.store.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import psychology.store.entities.SchoolEntity;

@Repository
public interface SchoolRepository extends JpaRepository<SchoolEntity, Long>{

	Optional<SchoolEntity> findByNameContainsIgnoreCase(String schoolName);

}
