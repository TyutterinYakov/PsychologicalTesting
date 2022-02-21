package psychology.api.util;

import java.time.LocalDateTime;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import psychology.store.repository.TokenRepository;

@Component
public class TokenCleaner {

	private static final Logger logger = LoggerFactory.getLogger(TokenCleaner.class);
	
	private TokenRepository tokenDao;
	
	
	@Autowired
	public TokenCleaner(TokenRepository tokenDao) {
		super();
		this.tokenDao = tokenDao;
	}


	@Transactional
	@Scheduled(cron="0 * * * * *")
	public void clean() {
		tokenDao.deleteAllByExpiredAtBefore(LocalDateTime.now());
		logger.info("Tokens deleted");
	}
	
}
