package psychology.api.service;


import java.util.List;

import psychology.api.dto.SchoolDto;
import psychology.store.entity.SchoolEntity;

public interface SchoolService {

	SchoolDto createSchool(String schoolName);

	List<SchoolDto> getSchoolsByFilter(String filter);

	void deleteSchool(Long schoolId);

	SchoolEntity searchSchoolById(Long schoolId);


}
