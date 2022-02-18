package psychology.store.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.lang.NonNull;

@Entity
@Table(name="question")
public class QuestionEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@NonNull
	private Integer questionOrder;
	@NonNull
	private String text;
	@NonNull
	@ManyToOne
	private TestEntity test;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getQuestionOrder() {
		return questionOrder;
	}
	public void setQuestionOrder(Integer questionOrder) {
		this.questionOrder = questionOrder;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public TestEntity getTest() {
		return test;
	}
	public void setTest(TestEntity test) {
		this.test = test;
	}
	
	
	
	
}
