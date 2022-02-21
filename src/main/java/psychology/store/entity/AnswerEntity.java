package psychology.store.entity;

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
@Table(name="answer")
public class AnswerEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@NonNull
	private Integer answerOrder;
	@NonNull
	@Column(length = 50)
	private String text;
	@NonNull
	@ManyToOne(cascade=CascadeType.REFRESH, fetch=FetchType.EAGER)
	private QuestionEntity question;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getAnswerOrder() {
		return answerOrder;
	}
	public void setAnswerOrder(Integer answerOrder) {
		this.answerOrder = answerOrder;
	}
	public QuestionEntity getQuestion() {
		return question;
	}
	public void setQuestion(QuestionEntity question) {
		this.question = question;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	
	
	
	
	
}
