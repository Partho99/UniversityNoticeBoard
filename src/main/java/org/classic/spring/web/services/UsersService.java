package org.classic.spring.web.services;

import java.util.List;

import org.classic.spring.web.dao.User;
import org.classic.spring.web.dao.UsersDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

@Service("usersService")
public class UsersService {

	private UsersDao usersDao;

	@Autowired
	public void setUsersDao(UsersDao usersDao) {
		this.usersDao = usersDao;
	}

	public void createUser(User user) {
		usersDao.createUser(user);

	}

	public boolean exists(String username) {

		return usersDao.exists(username);
	}
	

	@Secured("ROLE_ADMIN")
	public List<User> getAllUsers() {
		return usersDao.getAllUsers();
	}

}
