package psychology.api.service.impl;

import java.time.LocalDateTime;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import psychology.api.exception.BadRequestException;
import psychology.api.exception.NotFoundException;
import psychology.api.exception.NotPermissionException;
import psychology.api.service.AuthorizationService;
import psychology.store.entity.PsychologistEntity;
import psychology.store.entity.TokenEntity;
import psychology.store.repository.PsychologistRepository;
import psychology.store.repository.TokenRepository;

@Service
public class AuthorizationServiceImpl implements AuthorizationService {

	
	private final PsychologistRepository psychologistDao;
	private final TokenRepository tokenDao;

	@Autowired
	public AuthorizationServiceImpl(PsychologistRepository psychologistDao, TokenRepository tokenDao) {
		super();
		this.psychologistDao = psychologistDao;
		this.tokenDao = tokenDao;
	}

	public String getAuthorization(String login, String password) {
		PsychologistEntity psychologist = psychologistDao.findByLoginAndPassword(login, password).orElseThrow(()->
				new BadRequestException("Неверные данные для входа"));
		Optional<TokenEntity> tokenOptional = tokenDao.findByPsychologist(psychologist);
		
		return checkTokenFromPsychologist(tokenOptional ,psychologist);
	}
	
	
	private String checkTokenFromPsychologist(Optional<TokenEntity> tokenOptional,
			PsychologistEntity psychologist) {
		
		if(tokenOptional.isPresent()) {
			if(tokenOptional.get().getExpiredAt().compareTo(LocalDateTime.now())!=-1) {
				return tokenOptional.get().getToken();
			} else {
				tokenDao.deleteById(tokenOptional.get().getToken());
			}
		} 
		return tokenDao.saveAndFlush(new TokenEntity(psychologist)).getToken();	
	}
	
	
	
	
	public boolean checkToken(HttpServletRequest request) {
		String token = request.getHeader("Authorization");
		if(token.isBlank()) {
			throw new NotPermissionException("Токен не найден. Пройдите авторизацию повторно");
		}
		TokenEntity tokenEntity = tokenDao.findById(token)
				.orElseThrow(()->new NotPermissionException("Токена не существует"));
		if(tokenEntity.getExpiredAt().compareTo(LocalDateTime.now())!=-1) {
			 return true;
		} else {
			tokenDao.deleteById(token);
			throw new NotPermissionException("Время токена истекло");
		}
		
		 
	} 
	
	
	
	
}
