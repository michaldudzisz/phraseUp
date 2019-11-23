package com.phraseUp.phraseUpServer.dao;

import com.phraseUp.phraseUpServer.model.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserDao {
	/**
	 * @param id unique id of user
	 * @param user user that is to be added to database
	 * @return true if adding has been done right, otherwise false
	 */
	int insertUser(UUID id, User user);

	default int insertUser(User user) {
		UUID id = UUID.randomUUID(); // CHANGE this somehow later !!!
		return insertUser(id, user);
	}

	int deleteUserById(UUID id);

	int updateUserById(UUID id, User user);

	Optional<User> selectUserById(UUID id);

	List<User> selectAllUsers();
}
