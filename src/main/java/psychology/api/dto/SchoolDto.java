package psychology.api.dto;

import org.springframework.lang.NonNull;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SchoolDto {

	@JsonProperty("school_id")
	@NonNull
	private Long id;
	@NonNull
	private String name;
	
	public SchoolDto() {
		super();
	}
	public SchoolDto(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
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
	
	
}
