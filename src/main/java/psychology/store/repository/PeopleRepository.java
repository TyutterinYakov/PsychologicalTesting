package psychology.store.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import psychology.api.dto.PeopleDto;
import psychology.store.entity.PeopleEntity;

@Repository
public interface PeopleRepository extends JpaRepository<PeopleEntity, Long>{

	@Query("select p from PeopleEntity p where lower(p.fio) like lower(concat('%', :filter, '%'))")
	List<PeopleEntity> findAllByFilter(String filter);

	List<PeopleEntity> findByOrderByFioAsc();
}
