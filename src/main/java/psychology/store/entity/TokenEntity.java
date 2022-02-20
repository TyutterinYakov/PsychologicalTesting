package psychology.store.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="token")
public class TokenEntity {

	private static final int EXPIRED_SECONDS = 60*60; //One hours expired
	@Id
	private String token = UUID.randomUUID().toString();
	
	private LocalDateTime expiredAt = LocalDateTime.now().plusSeconds(EXPIRED_SECONDS);
	
	private LocalDateTime createdAt = LocalDateTime.now();
	
	@ManyToOne(cascade=CascadeType.REFRESH, fetch=FetchType.EAGER)
	@JoinColumn(name="psychologist_id")
	private PsychologistEntity psychologist;
	
	public TokenEntity() {
		super();
	}


	public TokenEntity(PsychologistEntity psychologist) {
		super();
		this.psychologist = psychologist;
	}


	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public LocalDateTime getExpiredAt() {
		return expiredAt;
	}

	public void setExpiredAt(LocalDateTime expiredAt) {
		this.expiredAt = expiredAt;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public PsychologistEntity getPsychologist() {
		return psychologist;
	}

	public void setPsychologist(PsychologistEntity psychologist) {
		this.psychologist = psychologist;
	}

	
	
	
	
	
}
