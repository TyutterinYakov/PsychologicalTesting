package psychology.api.dto;

public class AckDto {

	
	private Boolean status;
	
	
	public AckDto(Boolean status) {
		this.status = status;
	}


	public AckDto() {
		super();
	}


	public static AckDto makeDefault(Boolean status) {
		return new AckDto(status);
	}
	
}
