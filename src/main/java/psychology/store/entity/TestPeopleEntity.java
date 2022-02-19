package psychology.store.entity;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.lang.NonNull;


@Entity
@Table(name="test_people")
public class TestPeopleEntity {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NonNull
	@ManyToOne(cascade=CascadeType.REFRESH, fetch=FetchType.EAGER)
	private PeopleEntity people;
	@NonNull
	@ManyToOne(cascade=CascadeType.REFRESH, fetch=FetchType.EAGER)
	private TestEntity test;
	@NonNull
	private String answers;
	@NonNull
	private LocalDateTime createAt = LocalDateTime.now();
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
	public LocalDateTime getCreateAt() {
		return createAt;
	}
	public void setCreateAt(LocalDateTime createAt) {
		this.createAt = createAt;
	}
	
	
	
	
}
