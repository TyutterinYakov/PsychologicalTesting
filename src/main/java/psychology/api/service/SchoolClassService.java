package psychology.api.service;

import java.util.List;

import psychology.api.dto.SchoolClassDto;
import psychology.store.entity.SchoolClassEntity;

public interface SchoolClassService {

	SchoolClassDto createSchoolClass(Long schoolId, Integer classNumber, String classLetter);

	void deleteSchoolClass(Long schoolId, Long classId);

	List<SchoolClassDto> getAllClassesBySchool(Long schoolId, Integer classNumber, String classLetter);

	SchoolClassEntity getSchoolClassById(Long classId);

}
