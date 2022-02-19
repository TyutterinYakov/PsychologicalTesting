package psychology.api.dto;

import java.time.LocalDate;


import org.springframework.lang.NonNull;

import psychology.api.domain.PeopleRole;


public class PeopleDto {
	
	@NonNull
	private Long id;
	@NonNull
	private String fio;
	@NonNull
	private LocalDate dateOfBirth;
	@NonNull
	private PeopleRole role;
	@NonNull
	private Long schoolClassId;

	public PeopleDto(Long id, String fio, LocalDate dateOfBirth, PeopleRole role, Long schoolClassId) {
		super();
		this.id = id;
		this.fio = fio;
		this.dateOfBirth = dateOfBirth;
		this.role = role;
		this.schoolClassId = schoolClassId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFio() {
		return fio;
	}

	public void setFio(String fio) {
		this.fio = fio;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public PeopleRole getRole() {
		return role;
	}

	public void setRole(PeopleRole role) {
		this.role = role;
	}

	public Long getSchoolClassId() {
		return schoolClassId;
	}

	public void setSchoolClassId(Long schoolClassId) {
		this.schoolClassId = schoolClassId;
	}


	
	
	
	
	
}
