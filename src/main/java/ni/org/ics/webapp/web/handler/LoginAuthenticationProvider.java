package ni.org.ics.webapp.web.handler;

import ni.org.ics.webapp.users.dao.UserDetailsDao;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

public class LoginAuthenticationProvider extends DaoAuthenticationProvider {
	
	UserDetailsDao userDetailsDao;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {

		try {
			Authentication auth = super.authenticate(authentication);
			// if reach here, means login success, else exception will be thrown
			// reset the user_attempts
			userDetailsDao.resetFailAttempts(auth.getName());
			userDetailsDao.insertNewAccess(auth);
			//Se solicita que las credenciales no caduquen. 10-05-2019
			//userDetailsDao.checkCredentialsDate(auth.getName());
			return auth;

		} catch (BadCredentialsException e) {
			userDetailsDao.updateFailAttempts(authentication.getName());
			throw e;
		} catch (LockedException e) {
			throw e;
		} 
	}

	public UserDetailsDao getUserDetailsDao() {
		return userDetailsDao;
	}

	public void setUserDetailsDao(UserDetailsDao userDetailsDao) {
		this.userDetailsDao = userDetailsDao;
	}

}