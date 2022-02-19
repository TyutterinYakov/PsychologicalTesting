package psychology.api.dto;

import org.springframework.lang.NonNull;

public class TestDto {
	@NonNull
	private Long testId;
	@NonNull
	private String name;
	@NonNull
	private boolean isStarted;
	@NonNull
	private Long psychologistId;
	
	
	
	public TestDto() {
		super();
	}
	public TestDto(Long testId, String name, boolean isStarted, Long psychologistId) {
		super();
		this.testId = testId;
		this.name = name;
		this.isStarted = isStarted;
		this.psychologistId = psychologistId;
	}
	public Long getTestId() {
		return testId;
	}
	public void setTestId(Long testId) {
		this.testId = testId;
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
	public Long getPsychologistId() {
		return psychologistId;
	}
	public void setPsychologistId(Long psychologistId) {
		this.psychologistId = psychologistId;
	}
	
	
	
}
