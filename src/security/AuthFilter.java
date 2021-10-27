package security;

import java.util.Base64;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import model.auth.User;
import repository.IUserRepository;

@Component
public class AuthFilter extends HandlerInterceptorAdapter {

	@Autowired
    private IUserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public User getUser(HttpServletRequest request) {
		String[] auth = new String(Base64.getDecoder().decode(request.getHeader("Authorization").replaceFirst("Basic ", ""))).split(":");

		User user;

		try {
			user = userRepository.selectByUsername(auth[0]);
		} catch (Exception e) {
			return null;
		}

		if (passwordEncoder.matches(auth[1], user.getPassword()))
			return user;

		return null;
	}

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {

		User user = getUser(request);

		if (user == null) {
			response.setStatus(401);
			response.getWriter().write("{\"error\": \"Authentication error. Please ensure your username:password are valid\"}");
			return false;
		}

		request.setAttribute("currentUser", user);

		return true;
	}
}