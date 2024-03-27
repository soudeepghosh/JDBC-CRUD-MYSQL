package in.soudeep.service;

import java.sql.ResultSet;
import java.sql.SQLException;

import in.soudeep.dto.Student;

public interface IStudentService {
	public int insertRecord(Student student)throws SQLException;
	public ResultSet displayRecord(int sid) throws SQLException;
	public ResultSet displayRecord() throws SQLException;
	public int updateRecord(Student student) throws SQLException;
	public int deleteRecord(int sid) throws SQLException;
	public ResultSet forgotId(String name, int sage, String saddress)throws SQLException;
}
