package psychology.store.entities;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.lang.NonNull;

import psychology.api.domains.PeopleRole;

@Entity
@Table(name="people")
public class PeopleEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@NonNull
	private String fio;
	@NonNull
	private LocalDate dateOfBirth;
	@NonNull
	@Enumerated(EnumType.STRING)
	private PeopleRole role;
	@NonNull
	@ManyToOne
	private SchoolClassEntity schoolClass;
	
	
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
	public SchoolClassEntity getSchoolClass() {
		return schoolClass;
	}
	public void setSchoolClass(SchoolClassEntity schoolClass) {
		this.schoolClass = schoolClass;
	}
	
	
	
	
}
