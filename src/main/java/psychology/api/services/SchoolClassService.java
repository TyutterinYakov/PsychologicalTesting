package psychology.api.services;

import java.util.List;

import psychology.api.dto.SchoolClassDto;

public interface SchoolClassService {

	SchoolClassDto createSchoolClass(Long schoolId, Integer classNumber, String classLetter);

	void deleteSchoolClass(Long schoolId, Long classId);

	List<SchoolClassDto> getAllClassesBySchool(Long schoolId);

}
