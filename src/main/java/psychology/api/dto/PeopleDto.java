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
	@NonNull
	private String login;
	@NonNull
	private String password;

	
	
	
	
	public PeopleDto(Long id, String fio, LocalDate dateOfBirth, PeopleRole role, Long schoolClassId, String login,
			String password) {
		super();
		this.id = id;
		this.fio = fio;
		this.dateOfBirth = dateOfBirth;
		this.role = role;
		this.schoolClassId = schoolClassId;
		this.login = login;
		this.password = password;
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

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


	
	
	
	
	
}
