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


@Entity
@Table(name="test")
public class TestEntity {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String name;
	private boolean isStarted = false;
	@ManyToOne(cascade=CascadeType.REFRESH, fetch=FetchType.EAGER)
	private PsychologistEntity psychologist;
	@OneToMany(cascade=CascadeType.REMOVE, fetch=FetchType.LAZY, mappedBy="test")
	private List<QuestionEntity> questions = new ArrayList<>();
	@OneToMany(cascade=CascadeType.REMOVE, fetch=FetchType.LAZY)
	private List<TestPeopleEntity> peoples;
	
	public TestEntity() {
		super();
	}
	public TestEntity(Long id, String name, PsychologistEntity psychologist) {
		super();
		this.id = id;
		this.name = name;
		this.psychologist = psychologist;
	}
	public TestEntity(String name, PsychologistEntity psychologist) {
		super();
		this.name = name;
		this.psychologist = psychologist;
	}
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isStarted() {
		return isStarted;
	}
	public void setStarted(boolean isStarted) {
		this.isStarted = isStarted;
	}
	public PsychologistEntity getPsychologist() {
		return psychologist;
	}
	public void setPsychologist(PsychologistEntity psychologist) {
		this.psychologist = psychologist;
	}
	public List<QuestionEntity> getQuestions() {
		return questions;
	}
	public void setQuestions(List<QuestionEntity> questions) {
		this.questions = questions;
	}
	public List<TestPeopleEntity> getPeoples() {
		return peoples;
	}
	public void setPeoples(List<TestPeopleEntity> peoples) {
		this.peoples = peoples;
	}
	
	
	
	

}
