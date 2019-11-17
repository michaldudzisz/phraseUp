package com.phraseUp.phraseUpServer.dao;

import com.phraseUp.phraseUpServer.model.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class UserDBAccessService implements UserDao {
	@Override
	public boolean insertUser(UUID id, User user) {
		return false;
	}

	@Override
	public boolean deleteUserById(UUID id) {
		return false;
	}

	@Override
	public boolean updateUserById(UUID id, User user) {
		return false;
	}

	@Override
	public Optional<User> selectUserById(UUID id) {
		return Optional.empty();
	}

	@Override
	public List<User> selectAllUsers() {
		return null;
	}
}
