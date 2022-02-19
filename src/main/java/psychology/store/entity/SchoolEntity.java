package psychology.store.entity;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.lang.NonNull;

@Entity
@Table(name="school")
public class SchoolEntity {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@NonNull
	private String name;
	@OneToMany(mappedBy="school", fetch=FetchType.LAZY, cascade = CascadeType.REMOVE)
	private List<SchoolClassEntity> schoolClasses = new LinkedList<>();
	
	public SchoolEntity() {
		super();
	}
	public SchoolEntity(String name) {
		super();
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
	public List<SchoolClassEntity> getSchoolClasses() {
		return schoolClasses;
	}
	public void setSchoolClasses(List<SchoolClassEntity> schoolClasses) {
		this.schoolClasses = schoolClasses;
	}
	
	

}
