package psychology.store.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import psychology.store.entity.PsychologistEntity;
import psychology.store.entity.TestEntity;
import psychology.store.entity.TestPeopleEntity;

@Repository
public interface TestPeopleRepository extends JpaRepository<TestPeopleEntity, Long> {

	List<TestPeopleEntity> findAllByTestAndPsychologist(TestEntity test, PsychologistEntity psychologist);

}
