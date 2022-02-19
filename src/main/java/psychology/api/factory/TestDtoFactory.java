package psychology.api.factory;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import psychology.api.dto.TestDto;
import psychology.store.entity.TestEntity;

@Component
public class TestDtoFactory {

	
	public TestDto createTestDto(TestEntity test) {
		return new TestDto(
				test.getId(),
				test.getName(),
				test.isStarted(),
				test.getPsychologist()
					.getId()
		);
	}
	
	
	public List<TestDto> createListTestDto(List<TestEntity> tests){
		return tests.stream()
				.map(this::createTestDto)
				.collect(Collectors.toList());
	}
}
