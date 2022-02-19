package psychology.api.dto;

import org.springframework.lang.NonNull;

public class SchoolClassDto {

	@NonNull
	private Long id;
	@NonNull
	private Integer number;
	@NonNull
	private String letter;
	@NonNull
	private SchoolDto schoolDto;
	
	public SchoolClassDto() {
		super();
	}
	
	public SchoolClassDto(Long id, Integer number, String letter, SchoolDto schoolDto) {
		super();
		this.id = id;
		this.number = number;
		this.letter = letter;
		this.schoolDto = schoolDto;
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

	public SchoolDto getSchoolDto() {
		return schoolDto;
	}

	public void setSchoolDto(SchoolDto schoolDto) {
		this.schoolDto = schoolDto;
	}
	
	
	
	
}
