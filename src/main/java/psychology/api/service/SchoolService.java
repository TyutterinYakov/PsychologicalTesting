package psychology.api.service;


import java.util.List;
import java.util.Optional;

import psychology.api.dto.SchoolDto;
import psychology.store.entity.SchoolEntity;

public interface SchoolService {

	SchoolDto createSchool(String schoolName);

	List<SchoolDto> getSchoolsByFilter(Optional<String> filter);

	void deleteSchool(Long schoolId);

	SchoolEntity searchSchoolById(Long schoolId);


}
