package com.phraseUp.phraseUpServer.service;

import com.phraseUp.phraseUpServer.model.LogInData;
import com.phraseUp.phraseUpServer.model.User;

import java.util.List;
import java.util.Optional;

public interface UserDao {
	int insertUser(User user);

	int deleteUserByUsername(String Username);

	int updateUserByUsername(String Username, User username);

	Optional<User> selectUserByUsername(String Username);

	List<User> selectAllUsers();

	Boolean checkLogs(LogInData log);
}
