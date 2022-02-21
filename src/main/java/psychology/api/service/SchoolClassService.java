package psychology.api.service;

import java.util.List;

import psychology.api.dto.SchoolClassDto;
import psychology.store.entity.PsychologistEntity;
import psychology.store.entity.SchoolClassEntity;

public interface SchoolClassService {

	SchoolClassDto createSchoolClass(Long schoolId, Integer classNumber, String classLetter, PsychologistEntity psychologist);

	void deleteSchoolClass(Long schoolId, Long classId, PsychologistEntity psychologist);

	List<SchoolClassDto> getAllClassesBySchool(Long schoolId, Integer classNumber, String classLetter);

	SchoolClassEntity getSchoolClassById(Long classId);

	SchoolClassEntity getSchoolClassByIdAndPsychologist(Long classId, PsychologistEntity psychologist);

	SchoolClassEntity getClassByIdAndPsychologist(Long classId, PsychologistEntity psychologist);

}
