package psychology.store.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.lang.NonNull;

@Entity
@Table(name="test")
public class TestEntity {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NonNull
	private String name;
	@NonNull
	private boolean isStarted = false;
	@NonNull
	@ManyToOne
	private PsychologistEntity psychologist;
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
	public boolean isStarted() {
		return isStarted;
	}
	public void setStarted(boolean isStarted) {
		this.isStarted = isStarted;
	}
	public PsychologistEntity getPsychologist() {
		return psychologist;
	}
	public void setPsychologist(PsychologistEntity psychologist) {
		this.psychologist = psychologist;
	}
	
	
	
	

}
