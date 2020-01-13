package com.phraseUp.phraseUpServer.service;

import com.phraseUp.phraseUpServer.model.LogInData;
import com.phraseUp.phraseUpServer.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

	private final UserDao userDao;

	@Autowired
	public UserService(@Qualifier("userDao") UserDao userDao) {
		this.userDao = userDao;
	}

	public int addUser(LogInData log) {
		return userDao.insertUser(log);
	}

	public List<User> getAllUsers() {
		return userDao.selectAllUsers();
	}

	public Optional<User> getUserByUsername(String username) {
		return userDao.selectUserByUsername(username);
	}

	public Optional<User> getUserById(UUID id) {
		return userDao.selectUserById(id);
	}

	public int updateUser(UUID id, User user) {
		return userDao.updateUserById(id, user);
	}

	public int deleteUser(UUID id) {
		return userDao.deleteUserById(id);
	}

	public Boolean checkLogs(LogInData log) {
		return userDao.checkLogs(log);
	}
}
