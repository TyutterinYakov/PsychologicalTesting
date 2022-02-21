package psychology.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import psychology.store.entity.TestAnswerEntity;

@Repository
public interface TestAnswerRepository extends JpaRepository<TestAnswerEntity, Long>{

}
