package in.soudeep.controller;

import java.sql.ResultSet;
import java.sql.SQLException;

import in.soudeep.dto.Student;
import in.soudeep.factory.StudentFactory;

public class StudentControllerImpl implements IStudentController {
	
	

	@Override
	public int insertRecord(Student student) throws SQLException {
		return StudentFactory.getStudentService().insertRecord(student);
	}

	@Override
	public ResultSet displayRecord(int sid) throws SQLException {
		return StudentFactory.getStudentService().displayRecord(sid);
	}

	@Override
	public int updateRecord(Student student) throws SQLException {
		return StudentFactory.getStudentService().updateRecord(student);
	}

	@Override
	public ResultSet displayRecord() throws SQLException {
		return StudentFactory.getStudentService().displayRecord();
	}

	@Override
	public int deleteRecord(int sid) throws SQLException {
		return StudentFactory.getStudentService().deleteRecord(sid);
	}

	@Override
	public ResultSet forgotId(String name, int sage, String saddress) throws SQLException {
		return StudentFactory.getStudentService().forgotId(name, sage, saddress);
	}

}
