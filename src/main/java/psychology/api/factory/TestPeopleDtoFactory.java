package psychology.api.factory;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import psychology.api.dto.TestPeopleDto;
import psychology.store.entity.TestPeopleEntity;

@Component
public class TestPeopleDtoFactory {

	private final PeopleDtoFactory peopleDtoFactory;
	
	@Autowired
	public TestPeopleDtoFactory(PeopleDtoFactory peopleDtoFactory) {
		super();
		this.peopleDtoFactory = peopleDtoFactory;
	}

	public TestPeopleDto createTestPeopleDto(TestPeopleEntity entity) {
		
		return new TestPeopleDto(
				entity.getId(), 
				peopleDtoFactory.createPeopleDto(entity.getPeople()), 
				entity.getAnswers(), 
				entity.getCreateAt()
				);
	}
	
	public List<TestPeopleDto> createListTestPeopleDto(List<TestPeopleEntity> entities) {
		return entities.stream()
				.map(this::createTestPeopleDto)
				.collect(Collectors.toList());
	}
}
