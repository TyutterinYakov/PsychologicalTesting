package psychology.api.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.icu.text.Transliterator;

import psychology.api.domain.PeopleRole;
import psychology.api.dto.PeopleDto;
import psychology.api.exception.BadRequestException;
import psychology.api.exception.NotFoundException;
import psychology.api.factory.PeopleDtoFactory;
import psychology.api.service.PeopleService;
import psychology.api.service.SchoolClassService;
import psychology.store.entity.PeopleEntity;
import psychology.store.entity.SchoolClassEntity;
import psychology.store.repository.PeopleRepository;

@Service
public class PeopleServiceImpl implements PeopleService {

	private static final Logger logger = LoggerFactory.getLogger(PeopleServiceImpl.class);
	private static final String CYRILLIC_TO_LATIN = "Russian-Latin/BGN";
	private static final Transliterator toLatinTrans = 
			Transliterator.getInstance(CYRILLIC_TO_LATIN);
	
	private final PeopleRepository peopleDao;
	private final PeopleDtoFactory peopleDtoFactory;
	private final SchoolClassService classService;
	
	@Autowired
	public PeopleServiceImpl(PeopleRepository peopleDao, PeopleDtoFactory peopleDtoFactory,
			SchoolClassService classService) {
		super();
		this.peopleDao = peopleDao;
		this.peopleDtoFactory = peopleDtoFactory;
		this.classService = classService;
	}

	@Override
	public void deletePeopleById(Long peopleId) {
		peopleDao.findById(peopleId).orElseThrow(()->
				new NotFoundException(
						String.format(
								"Человек с идентификатором \"%s\" не найден",
								peopleId)
				)
		);
	}

	@Override
	public PeopleDto createPeople(String peopleFio, LocalDate dateOfBirth, PeopleRole role, Long classId) {
		SchoolClassEntity classEntity = classService.getSchoolClassById(classId);
		
		String login;
		String password;
		if(peopleFio.isBlank()) {
			throw new BadRequestException("Поле ФИО не должно быть пустым!");
		} else {
			String[] concatFio = peopleFio.trim().split(" ");
			if(concatFio.length<2) {
				throw new BadRequestException("Введите все данные ФИО");
			} else {
				StringBuilder sb = new StringBuilder();
				for(String f : concatFio) {
					sb.append(toLatinTrans.transliterate(f.trim()));
				}
				login = sb.toString();
				password = UUID.randomUUID().toString().substring(0, 7); 
			}
		}
		
		logger.info(String.format("Login: \"%s\" ", login));
		logger.info(String.format("Password: \"%s\" ", password));
		
		
		
		return peopleDtoFactory
				.createPeopleDto(
						peopleDao.saveAndFlush(new PeopleEntity(
								peopleFio, 
								dateOfBirth,
								login,
								password,
								role, 
								classEntity))
				);
	}

	@Override
	public List<PeopleDto> getAllPeopleByFilter(String filter) {
		
		if(filter.isBlank()) {
			return peopleDtoFactory.createListPeopleDto(peopleDao.findByOrderByFioAsc());
		}
		return peopleDtoFactory.createListPeopleDto(peopleDao.findAllByFilter(filter));
	}

	@Override
	public Long getPeopleIdByLoginAndPassword(String login, String password) {
		PeopleEntity people = peopleDao.findByLoginAndPassword(login, password)
				.orElseThrow(()->
					new NotFoundException(
						"Пользователь с такими данными не найден"
					)
				);
		return people.getId();
	}
	
	
	
	
	
	
	
	
	
	
}
