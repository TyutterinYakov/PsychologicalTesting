package psychology.store.entity;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.lang.NonNull;

import psychology.api.domain.PeopleRole;

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
	private String login;
	@NonNull
	private String password;
	@NonNull
	@Enumerated(EnumType.STRING)
	private PeopleRole role;
	@NonNull
	@ManyToOne(fetch=FetchType.EAGER, cascade=CascadeType.REFRESH)
	private SchoolClassEntity schoolClass;
	@OneToMany(fetch=FetchType.LAZY, cascade=CascadeType.REMOVE, mappedBy="people")
	List<TestPeopleEntity> testsPeoples;
	
	
	public PeopleEntity() {
		super();
	}

	public Long getId() {
		return id;
	}
	public PeopleEntity(String fio, LocalDate dateOfBirth, String login, String password, PeopleRole role,
			SchoolClassEntity schoolClass) {
		super();
		this.fio = fio;
		this.dateOfBirth = dateOfBirth;
		this.login = login;
		this.password = password;
		this.role = role;
		this.schoolClass = schoolClass;
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
	public List<TestPeopleEntity> getTestsPeoples() {
		return testsPeoples;
	}
	public void setTestsPeoples(List<TestPeopleEntity> testsPeoples) {
		this.testsPeoples = testsPeoples;
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
