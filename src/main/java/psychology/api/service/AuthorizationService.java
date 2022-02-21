package psychology.api.service;

import javax.servlet.http.HttpServletRequest;

import psychology.store.entity.PsychologistEntity;

public interface AuthorizationService {

	public String getAuthorization(String login, String password);
	public PsychologistEntity checkTokenReturnPsychologist(HttpServletRequest request);
}
