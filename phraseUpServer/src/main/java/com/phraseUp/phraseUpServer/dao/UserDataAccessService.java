package com.phraseUp.phraseUpServer.dao;

import com.phraseUp.phraseUpServer.model.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("userDao")
public class UserDataAccessService implements UserDao {

	private static List<User> DB = new ArrayList<>();

	@Override
	public boolean insertUser(UUID id, User user) {
		DB.add(new User(id, user.getName())); // niepotrzebnie jakoś to się dzieje
		System.out.println(user.getName());
		return true;
	}

	@Override
	public List<User> selectAllUsers() {
		return DB;
	}

	@Override
	public Optional<User> selectUserById(UUID id) {
		return DB.stream().filter(user -> user.getId().equals(id)).findFirst();
	}

	@Override
	public boolean deleteUserById(UUID id) {
		Optional<User> userMaybe = selectUserById(id);
		if (userMaybe.isEmpty())
			return false; // jakoś to obsłużyć
		DB.remove(userMaybe.get());
		return true;
	}

	@Override
	public boolean updateUserById(UUID id, User updatedUser) {
		Optional<User> userMaybe = selectUserById(id);
		if (userMaybe.isEmpty())
			return false;

		userMaybe.map(user -> {
			int indexOfUserToUpdate = DB.indexOf(user);
			DB.set(indexOfUserToUpdate, new User(id, updatedUser.getName()));
			return null;
		});

		return true;
	}

}
