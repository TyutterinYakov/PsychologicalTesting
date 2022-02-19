package psychology.store.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.lang.NonNull;

@Entity
@Table(name="school_class")
public class SchoolClassEntity {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@NonNull
	private Integer number;
	@NonNull
	private String letter;
	@NonNull
	@ManyToOne(cascade=CascadeType.REFRESH, fetch=FetchType.EAGER)
	private SchoolEntity school;
	@OneToMany(cascade=CascadeType.REMOVE, fetch=FetchType.LAZY, mappedBy="schoolClass")
	private List<PeopleEntity> peoples = new ArrayList<>();
	
	public SchoolClassEntity() {
		super();
	}
	
	public SchoolClassEntity(Integer number, String letter, SchoolEntity school) {
		super();
		this.number = number;
		this.letter = letter;
		this.school = school;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getNumber() {
		return number;
	}
	public void setNumber(Integer number) {
		this.number = number;
	}
	public String getLetter() {
		return letter;
	}
	public void setLetter(String letter) {
		this.letter = letter;
	}
	public SchoolEntity getSchool() {
		return school;
	}
	public void setSchool(SchoolEntity school) {
		this.school = school;
	}

	public List<PeopleEntity> getPeoples() {
		return peoples;
	}

	public void setPeoples(List<PeopleEntity> peoples) {
		this.peoples = peoples;
	}
	
	
	

}
