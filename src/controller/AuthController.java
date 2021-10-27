package controller;

import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import model.auth.User;

public class AuthController {

	public Set<Long> getAuthedCityIds(HttpServletRequest request) {
		return ((User) request.getAttribute("currentUser")).getCities()
			.stream()
			.map(c -> c.getId())
			.collect(Collectors.toSet());
	}
	
}
