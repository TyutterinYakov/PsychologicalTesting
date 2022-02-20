package psychology.api.dto;

import java.time.LocalDateTime;

public class TestPeopleDto {

	private Long id;
	private PeopleDto peopleDto;
	private String answers;
	private LocalDateTime createAt;
	
	public TestPeopleDto(Long id, PeopleDto peopleDto, String answers, LocalDateTime createAt) {
		super();
		this.id = id;
		this.peopleDto = peopleDto;
		this.answers = answers;
		this.createAt = createAt;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public PeopleDto getPeopleDto() {
		return peopleDto;
	}
	public void setPeopleDto(PeopleDto peopleDto) {
		this.peopleDto = peopleDto;
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
