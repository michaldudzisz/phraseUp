package com.phraseUp.phraseUpServer.dao;

import com.phraseUp.phraseUpServer.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("userDao")
public class UserDBAccessService implements UserDao {

	private final JdbcTemplate jdbcTemplate;

	@Autowired
	public UserDBAccessService(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

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
		final String sql = "SELECT id, name FROM users WHERE id = ?";
		User user = jdbcTemplate.queryForObject(sql, new Object[]{id},
				((resultSet, i) -> {
					UUID userId = UUID.fromString(resultSet.getString("id"));
					String name = resultSet.getString("name");
					return new User(userId, name);
				}));
		return Optional.ofNullable(user);
	}

	@Override
	public List<User> selectAllUsers() {
		String sql = "SELECT id, name FROM users";
		return jdbcTemplate.query(sql, (resultSet, i) -> {
			UUID id = UUID.fromString(resultSet.getString("id"));
			String name = resultSet.getString("name");
			return new User(id, name);
		});
	}
}
