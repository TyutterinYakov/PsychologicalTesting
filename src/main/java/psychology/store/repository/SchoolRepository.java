package psychology.store.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import psychology.store.entity.SchoolEntity;

@Repository
public interface SchoolRepository extends JpaRepository<SchoolEntity, Long>{

	List<SchoolEntity> findAllByNameStartsWithIgnoreCase(String schoolName);

	Optional<SchoolEntity> findByNameContainsIgnoreCase(String schoolName);

	@Query("select s from SchoolEntity s "
			+ "where lower(s.name) like lower(concat('%', :filter, '%')) "
			+ "order by s.name desc ")
	List<SchoolEntity> findAllByFilter(@Param("filter") String filter);

}
