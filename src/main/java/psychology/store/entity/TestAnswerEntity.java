package psychology.store.entity;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.lang.NonNull;

@Entity
@Table(name="test_answer")
public class TestAnswerEntity {

	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@NonNull
	@ManyToOne(cascade=CascadeType.REFRESH, fetch=FetchType.EAGER)
	private PeopleEntity people;
	@NonNull
	@ManyToOne(cascade=CascadeType.REFRESH, fetch=FetchType.EAGER)
	private TestEntity test;
	@Column(length=20000)
	@NonNull
	private String answers;
	private LocalDateTime createdAt = LocalDateTime.now();
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public PeopleEntity getPeople() {
		return people;
	}
	public void setPeople(PeopleEntity people) {
		this.people = people;
	}
	public TestEntity getTest() {
		return test;
	}
	public void setTest(TestEntity test) {
		this.test = test;
	}
	public String getAnswers() {
		return answers;
	}
	public void setAnswers(String answers) {
		this.answers = answers;
	}
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	
	
	
	
	
}
