package psychology.store.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.lang.NonNull;

@Entity
@Table(name="psychologist")
public class PsychologistEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@NonNull
	@Column(length=300)
	private String fio;
	@NonNull
	@Column(length=60)
	private String login;
	@NonNull
	@Column(length=60)
	private String password;
	@OneToMany(cascade=CascadeType.REMOVE, fetch=FetchType.LAZY, mappedBy="psychologist")
	private List<TestEntity> tests = new ArrayList<>();
	@OneToMany(cascade=CascadeType.REMOVE, fetch=FetchType.LAZY, mappedBy="psychologist")
	private List<SchoolClassEntity> schoolClasses = new ArrayList<>();
	
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
	public List<TestEntity> getTests() {
		return tests;
	}
	public void setTests(List<TestEntity> tests) {
		this.tests = tests;
	}
	public List<SchoolClassEntity> getSchoolClasses() {
		return schoolClasses;
	}
	public void setSchoolClasses(List<SchoolClassEntity> schoolClasses) {
		this.schoolClasses = schoolClasses;
	}
	
	
	
	
	
}
