package in.soudeep.factory;

import in.soudeep.controller.IStudentController;
import in.soudeep.controller.StudentControllerImpl;
import in.soudeep.repository.IStudentRepo;
import in.soudeep.repository.StudentRepoImpl;
import in.soudeep.service.IStudentService;
import in.soudeep.service.StudentServiceImpl;

public class StudentFactory {
	private static StudentRepoImpl studentRepo = null;
	private static StudentServiceImpl studentService = null;
	private static StudentControllerImpl studentController = null;
	
	public static IStudentRepo getStudentRepo() {
		if (studentRepo == null) {
			studentRepo = new StudentRepoImpl();
		}
		return studentRepo;
	}
	
	public static IStudentService getStudentService() {
		if (studentService == null) {
			studentService = new StudentServiceImpl();
		}
		return studentService;
	}
	
	public static IStudentController getStudentController() {
		if (studentController == null) {
			studentController = new StudentControllerImpl();
		}
		return studentController;
	}
}
