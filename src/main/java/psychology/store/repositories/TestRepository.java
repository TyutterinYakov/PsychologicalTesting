package psychology.store.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import psychology.store.entities.TestEntity;

@Repository
public interface TestRepository extends JpaRepository<TestEntity, Long> {

}
