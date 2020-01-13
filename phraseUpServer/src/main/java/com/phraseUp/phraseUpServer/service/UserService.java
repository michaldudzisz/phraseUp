package com.phraseUp.phraseUpServer.service;

import com.phraseUp.phraseUpServer.model.LogInData;
import com.phraseUp.phraseUpServer.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

	private final UserDao userDao;

	@Autowired
	public UserService(@Qualifier("userDao") UserDao userDao) {
		this.userDao = userDao;
	}

	public int addUser(User user) {
		return userDao.insertUser(user);
	}

	public List<User> getAllUsers() {
		return userDao.selectAllUsers();
	}

	public Optional<User> getUserByUsername(String username) {
		return userDao.selectUserByUsername(username);
	}

	public int updateUser(String username, User user) {
		return userDao.updateUserByUsername(username, user);
	}

	public int deleteUser(String username) {
		return userDao.deleteUserByUsername(username);
	}

	public Boolean checkLogs(LogInData log) {
		return userDao.checkLogs(log);
	}
}
