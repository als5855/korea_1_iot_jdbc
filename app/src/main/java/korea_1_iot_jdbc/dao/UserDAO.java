package korea_1_iot_jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import korea_1_iot_jdbc.db.DBConnection;
import korea_1_iot_jdbc.entitiy.User;

// DAO (Data Access Object)
// : DB와 같은 영구 저장소에 접그나는 객체
// : 데이터 처리 로직과 DB 작업을 분리하는 역할

//DAO 패턴
// : DB와의 CRUD 작업을 처리

// UserDAO 클래스 정의
// : User Entity에서 
public class UserDAO {
	// 아이디를 기준으로 사용자 정보를 가져오는 메서드
	public User getUserById(int id) throws SQLException {
		Connection connection = DBConnection.getConnection();
		
		// 실행할 SQL문 작성 : id를 조건으로 
		String sql = "SELECT * FROM user WHERE id = ? ";
		
		//SQL 쿼리를 실행하기 위해 PreparedStatement 객체를 생성
		PreparedStatement statement = connection.prepareStatement(sql);
		
		// 실행 객체에 파라키터 값으 설정
		statement.setInt(1, id);
		
		//SQL쿼리를 
		ResultSet resultSet = statement.executeQuery();
		
		User user = null;
		if(resultSet.next()) {
			user = new User(resultSet.getInt("id")
					, resultSet.getString("name")
					, resultSet.getString("email"));
		}
		resultSet.close();
		statement.close();
		connection.close();
		
		return user;
	}
	
	public List<User> findAll() throws SQLException {
		// DB 연결을 가져옴
		Connection connection = DBConnection.getConnection();
		
		// 모든 사용자 정보를 조회하는 SQL 쿼리문
		String sql = "SELECT * FROM user";
		
		// PreparedStatement 객체를 생성
		PreparedStatement statement = connection.prepareStatement(sql);
		
		//쿼리를 실행하고 결과를 ResultSet으로 전달받음
		ResultSet resultSet = statement.executeQuery();
		
		// User객체를 저장할 리스트 생성
		List<User> users = new ArrayList<User>();
		
		// 결과집합에서 다음 행이 있는 동안 반복
		while (resultSet.next()) {
			User user = new User(
						resultSet.getInt("id"),
						resultSet.getString("name"),
						resultSet.getNString("email")
					);
					users.add(user);
		}
		resultSet.close();
		statement.close();
		connection.close();
		
		return users;
	}
	
	public void addUser (User user) throws SQLException {
		Connection connection = DBConnection.getConnection();
		String sql = "INSERT INTO user (name, email) VALUES (?, ?)";
		
		PreparedStatement statment = connection.prepareStatement(sql);
		
		statment.setString(1, user.getName());
		statment.setString(2, user.getEmail());
		
		statment.executeUpdate();
		
		statment.close();
		connection.close();
	}
	
	public void updateUser(User user) throws SQLException {
		Connection connection = DBConnection.getConnection();
		
		boolean updateName = user.getName() != null && !user.getName().isEmpty();	
		boolean updateEmail = user.getEmail() != null && !user.getEmail().isEmpty();	
		
		// SQL 쿼리 작성
		// cf) StringBuilder
		//		 	: 자바에서 가변 문자열 만드는 클래스
		StringBuilder sql = new StringBuilder("UPDATE user SET ");
		
		if(updateName) {
			sql.append("name = ?, ");
		}
		if(updateEmail) {
			sql.append("email = ?, ");
		}
		if(updateName || updateEmail) {
		sql.deleteCharAt(sql.length() - 2);
		}
		sql.append("WHERE id = ?");
		
		PreparedStatement statment = connection.prepareStatement(sql.toString());
		
		int parameterIndex = 1;
		if(updateName) {
			statment.setString(parameterIndex++, user.getName());
		}
		if (updateEmail) {
			statment.setString(parameterIndex++,	user.getEmail());
		}
		
		statment.setInt(parameterIndex, user.getId());
		
		statment.executeQuery();
		
		statment.close();
		connection.close();
	}
	
	public void deleteUser(int id) throws SQLException {
		try (Connection connetion = DBConnection.getConnection()) {
			String sql = "DELETE FROM user WHERE id = ? ";
			
			PreparedStatement statement = connetion.prepareStatement(sql);
			statement.setInt(1, id);
			statement.executeUpdate();
			
			statement.close();
			connetion.close();
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	
}
