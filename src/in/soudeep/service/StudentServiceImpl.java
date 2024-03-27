package in.soudeep.service;

import java.sql.ResultSet;
import java.sql.SQLException;

import in.soudeep.dto.Student;
import in.soudeep.factory.StudentFactory;

public class StudentServiceImpl implements IStudentService {

	@Override
	public int insertRecord(Student student) throws SQLException {
		// abstraction
		return StudentFactory.getStudentRepo().insertRecord(student);
	}

	@Override
	public ResultSet displayRecord(int sid) throws SQLException {
		return StudentFactory.getStudentRepo().displayRecord(sid);
	}

	@Override
	public int updateRecord(Student student) throws SQLException {
		return StudentFactory.getStudentRepo().updateRecord(student);
	}

	@Override
	public ResultSet displayRecord() throws SQLException {
		return StudentFactory.getStudentRepo().displayRecord();
	}

	@Override
	public int deleteRecord(int sid) throws SQLException {
		return StudentFactory.getStudentRepo().deleteRecord(sid);
	}

	@Override
	public ResultSet forgotId(String name, int sage, String saddress) throws SQLException {
		return StudentFactory.getStudentRepo().forgotId(name, sage, saddress);
	}

}
