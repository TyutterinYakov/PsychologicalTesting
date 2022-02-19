package psychology.store.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import psychology.store.entities.SchoolClassEntity;
import psychology.store.entities.SchoolEntity;

@Repository
public interface SchoolClassRepository extends JpaRepository<SchoolClassEntity, Long> {

	Optional<SchoolClassEntity> findBySchoolAndNumberAndLetterContainsIgnoreCase(SchoolEntity school, Integer classNumber, String classLetter);

	Optional<SchoolClassEntity> findByIdAndSchool(Long classId, SchoolEntity school);

}
