package psychology.api.dto;

public class PsychologistDto {

	
	private Long id;
	private String fio;
	
	public PsychologistDto(Long id, String fio) {
		super();
		this.id = id;
		this.fio = fio;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFio() {
		return fio;
	}
	public void setFio(String fio) {
		this.fio = fio;
	}
	
	
	
	
}
