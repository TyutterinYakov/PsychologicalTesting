package psychology.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import psychology.store.entity.TestPeopleEntity;

@Repository
public interface TestPeopleRepository extends JpaRepository<TestPeopleEntity, Long> {

}
