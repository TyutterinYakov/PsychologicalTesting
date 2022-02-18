package psychology.store.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import psychology.store.entities.SchoolClassEntity;

@Repository
public interface SchoolClassRepository extends JpaRepository<SchoolClassEntity, Long> {

}
