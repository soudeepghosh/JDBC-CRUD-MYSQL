package in.soudeep.repository;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import in.soudeep.dto.Student;
import in.soudeep.utility.JdbcUtil;

public class StudentRepoImpl implements IStudentRepo {
	
	private static Connection connection = null;
	PreparedStatement pstmt = null;
	private static final String INSERT_QUERY = "INSERT INTO student(`sid`,`sname`,`sage`,`saddress`) values(?,?,?,?)";
	private static final String SELECT_QUERY_USING_ID = "SELECT sid, sname, sage, saddress FROM student where sid = ?";
	private static final String UPDATE_QUERY = "UPDATE student set sname = ? , sage = ? , saddress = ? where sid = ?";
	private static final String SELECT_STAR_QUERY = "SELECT sid, sname, sage, saddress FROM student";
	private static final String DELETE_QUERY = "DELETE FROM student WHERE sid = ?";
	private static final String SQL_SELECT_WITHOUT_ID = "SELECT sid, sname, sage, saddress FROM student where sname = ? AND sage = ? AND saddress = ?";

	static {
		try {
			connection = JdbcUtil.getDBConection();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public int insertRecord(Student student) throws SQLException {
		
		
		if (connection != null) {
			pstmt = connection.prepareStatement(INSERT_QUERY);
		}
		if (pstmt != null) {
			pstmt.setInt(1, student.getSid());
			pstmt.setString(2, student.getSname());
			pstmt.setInt(3, student.getSage());
			pstmt.setString(4, student.getSaddress());
		}
		
		return pstmt.executeUpdate();
	}

	@Override
	public ResultSet displayRecord(int sid) throws SQLException {
		if (connection != null)
			pstmt = connection.prepareStatement(SELECT_QUERY_USING_ID);
		if (pstmt != null) {
			pstmt.setInt(1, sid);
		}
		return pstmt.executeQuery();
	}
	
	@Override
	public int updateRecord(Student student)throws SQLException {
		if (connection != null) {
			pstmt = connection.prepareStatement(UPDATE_QUERY);
		}
		if (pstmt != null) {
			pstmt.setString(1, student.getSname());
			pstmt.setInt(2, student.getSage());
			pstmt.setString(3, student.getSaddress());
			pstmt.setInt(4, student.getSid());
		}
		
		return pstmt.executeUpdate();
	}

	@Override
	public ResultSet displayRecord() throws SQLException {
		Statement stmt = null;
		
		if (connection != null)
			stmt = connection.createStatement();
		
		return stmt.executeQuery(SELECT_STAR_QUERY);
	}

	@Override
	public int deleteRecord(int sid) throws SQLException {
		PreparedStatement pstmt = null;
		if (connection != null) {
			pstmt = connection.prepareStatement(DELETE_QUERY);
		}
		if (pstmt != null) {
			pstmt.setInt(1, sid);
		}
		return pstmt.executeUpdate();
	}

	@Override
	public ResultSet forgotId(String name, int sage, String saddress)throws SQLException {
		PreparedStatement pstmt = null;
		if (connection != null) {
			pstmt = connection.prepareStatement(SQL_SELECT_WITHOUT_ID);
			pstmt.setString(1, name);
			pstmt.setInt(2, sage);
			pstmt.setString(3, saddress);
		}
		return pstmt.executeQuery();
	}
	
	

}
