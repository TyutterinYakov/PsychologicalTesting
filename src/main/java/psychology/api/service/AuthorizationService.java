package psychology.api.service;

import javax.servlet.http.HttpServletRequest;

public interface AuthorizationService {

	public String getAuthorization(String login, String password);
	public boolean checkToken(HttpServletRequest request);
}
