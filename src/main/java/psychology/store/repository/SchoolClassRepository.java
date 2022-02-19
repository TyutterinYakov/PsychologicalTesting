package psychology.store.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import psychology.store.entity.SchoolClassEntity;
import psychology.store.entity.SchoolEntity;

@Repository
public interface SchoolClassRepository extends JpaRepository<SchoolClassEntity, Long> {

	Optional<SchoolClassEntity> findBySchoolAndNumberAndLetterContainsIgnoreCase(SchoolEntity school, Integer classNumber, String classLetter);

	Optional<SchoolClassEntity> findByIdAndSchool(Long classId, SchoolEntity school);

}
