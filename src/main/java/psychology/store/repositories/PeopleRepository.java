package psychology.store.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import psychology.store.entities.PeopleEntity;

@Repository
public interface PeopleRepository extends JpaRepository<PeopleEntity, Long>{

}
