package com.simplilearn.workshop.repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.simplilearn.workshop.domain.Todo;

@Repository(value = "todoRepository")
//@Component(value = "todoRepository")
public class TodoRepositoryImpl implements TodoRepository{
	
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public TodoRepositoryImpl(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public int getTodosCount() {
		int count = this.jdbcTemplate.queryForObject("select count(*) from todos", Integer.class);
		return count;
	}

	private final RowMapper<Todo> todoRowMapper  = (resultSet,rowNum) -> {
		Todo todo =new Todo();
		todo.setId(resultSet.getLong("id"));
		todo.setDescription(resultSet.getString("description"));
		todo.setUsername(resultSet.getString("username"));
		todo.setTargetDate(resultSet.getDate("targetDate"));
		todo.setDone(resultSet.getBoolean("isDone"));
		return  todo;
	};
	
	@Override
	public List<Todo> findAll() {
		List<Todo> todos = this.jdbcTemplate.query("select * from  todos",todoRowMapper);
		return todos;
	}

	@Override
	public Todo save(Todo theTodo) {
////		GeneratedKeyHolder holder = new GeneratedKeyHolder();
//		int result = this.jdbcTemplate.update("",theTodo.getUsername()
//				,theTodo.getDescription(),theTodo.isDone(),theTodo.getTargetDate(),holder);
//		long primaryKey = holder.getKey().longValue();
//		System.out.println("ID : " + primaryKey);
		
		GeneratedKeyHolder holder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
		    @Override
		    public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
		        PreparedStatement statement = con.prepareStatement("insert into todos (username,description,isDone,targetDate) values (?,?,?,?) ", Statement.RETURN_GENERATED_KEYS);
		        statement.setString(1, theTodo.getUsername());
		        statement.setString(2, theTodo.getDescription());
		        statement.setBoolean(3, theTodo.isDone());
		        statement.setDate(4, new Date(10));
		        return statement;
		    }
		}, holder);

		long primaryKey = holder.getKey().longValue();
		System.out.println("ID : " + primaryKey);
		return null;
	}

	@Override
	public Todo deleteById(long theId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Todo findById(long theId) {
		Todo theTodo = this.jdbcTemplate.queryForObject("select * from todos where id =?", todoRowMapper, theId);
		return theTodo;
	}
	
	

}
