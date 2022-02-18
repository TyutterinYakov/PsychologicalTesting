package psychology.store.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import psychology.store.entities.TestPeopleEntity;

@Repository
public interface TestPeopleRepository extends JpaRepository<TestPeopleEntity, Long> {

}
