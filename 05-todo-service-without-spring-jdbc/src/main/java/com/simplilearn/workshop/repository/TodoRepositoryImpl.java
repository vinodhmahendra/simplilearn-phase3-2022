package com.simplilearn.workshop.repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.simplilearn.workshop.domain.Todo;
import com.simplilearn.workshop.utils.MySQLDatabaseConnectionUtil;

public class TodoRepositoryImpl implements TodoRepository{
	@Override
	public List<Todo> findAll() {
		List<Todo> todos = new ArrayList<>();
		
		try {
			Connection connection = MySQLDatabaseConnectionUtil.getConnection();
			
			Statement statement = connection.createStatement();
			
			String selectQuery = "select * from todos";
			
			ResultSet rs = statement.executeQuery(selectQuery);
			
			while (rs.next()) {
				Todo todo =new Todo();
				todo.setId(rs.getLong("id"));
				todo.setDescription(rs.getString("description"));
				todo.setUsername(rs.getString("username"));
				todo.setTargetDate(rs.getDate("targetDate"));
				todo.setDone(rs.getBoolean("isDone"));
				
				todos.add(todo);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return todos;
	}

	@Override
	public Todo save(Todo theTodo) {
		
		// obtain the connection
		// obtain a statement
		// prepare the statement 
		// execute the statement
		return null;
	}

	@Override
	public Todo deleteById(long theId) {
		return null;
	}

	@Override
	public Todo findById(long theId) {
		return null;
	}

}
