package psychology.store.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import psychology.store.entity.PsychologistEntity;
import psychology.store.entity.TestEntity;

@Repository
public interface TestRepository extends JpaRepository<TestEntity, Long> {

	
	List<TestEntity> findByPsychologist(PsychologistEntity psychologist);

	@Query("select t from TestEntity t where lower(t.name) like lower(concat('%', :filter, '%')) and t.psychologist=:psychologist order by t.name")
	List<TestEntity> findTestByFilterAndPsychologist(String string, PsychologistEntity psychologist);

	@Query("select t from TestEntity t where lower(t.name) like lower(concat('%', :filter, '%')) order by t.name")
	List<TestEntity> findByFilter(String string);

	Optional<TestEntity> findByIdAndPsychologist(Long testId, PsychologistEntity psychologist);

}
