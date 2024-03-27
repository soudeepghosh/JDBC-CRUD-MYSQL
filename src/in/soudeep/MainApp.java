package in.soudeep;



import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import in.soudeep.controller.IStudentController;
import in.soudeep.dto.Student;
import in.soudeep.factory.StudentFactory;

public class MainApp {
	
	public static void main(String[] args) {
		IStudentController controller = StudentFactory.getStudentController();
		try (Scanner scanner = new Scanner(System.in)) {
			while(true) {
				System.out.println();
				System.out.println("---------------------------------");
				System.out.println("\tStudent Database");
				System.out.println("---------------------------------");
				
				System.out.println("\t1. Display");
				System.out.println("\t2. Insertion");
				System.out.println("\t3. Updation");
				System.out.println("\t4. Deletion");
				System.out.println("\t5. Forgot ID ?");
				System.out.println("\t0. Exit");
				
				System.out.println("---------------------------------");
				System.out.println("Enter your choice :: ");
				int choice = scanner.nextInt();
				switch (choice){
				// Display
				case 1: {
					System.out.println("---------------------------------");
					System.out.println("\tDiplay Records");
					System.out.println("---------------------------------");
					System.out.println("\t--------------------------------------");
					System.out.println("\t1. Display Records using Student ID");
					System.out.println("\t2. Display All the Student Records");
					System.out.println("\t--------------------------------------");
					System.out.println("Enter your choice :: ");
					int subChoice = scanner.nextInt();
					
					switch (subChoice) {
					case 1: {
						
						System.out.println("Enter Student ID to display details :: ");
						int sid = scanner.nextInt();
						ResultSet result = null;
						try {
							System.out.println("----------------");
							System.out.println("Student Details ");
							System.out.println("----------------");
							
							result = controller.displayRecord(sid);
							
							if(result.next()) {
								System.out.println("---------------------------------");
								System.out.println("SID\tSNAME\tSAGE\tSADDRESS");
								System.out.println("---------------------------------");
								System.out.println(result.getInt(1)+"\t"+result.getString(2)+"\t"+result.getInt(3)+"\t"+result.getString(4));
								System.out.println("---------------------------------");
							} else {
								System.out.println("Id not found...");
							}
						} catch (SQLException e) {
							System.out.println("Something went wrong...");
						} finally {
							try {
								if (result != null)
									result.close();
							} catch (SQLException e) {
								e.printStackTrace();
							}
						}
						
						break;
					} 
					case 2: {
						ResultSet result = null;
						try {
							System.out.println("---------------------------------");
							System.out.println("\tStudent Details ");
							System.out.println("---------------------------------");
							
							result = controller.displayRecord();
							
							System.out.println("SID\tSNAME\tSAGE\tSADDRESS");
							System.out.println("---------------------------------");
							while(result.next()) {
								
								System.out.println(result.getInt(1)+"\t"+result.getString(2)+"\t"+result.getInt(3)+"\t"+result.getString(4));
							}
							System.out.println("---------------------------------");
						} catch (SQLException e) {
							System.out.println("Something went wrong...");
						} finally {
							try {
								if (result != null)
									result.close();
							} catch (SQLException e) {
								e.printStackTrace();
							}
						}
						
						break;
					}
					
					default:
						System.out.println("Wrong choice...");
					}
					
					break;
				}
				// Insertion
				case 2: {
					System.out.println("---------------------------------");
					System.out.println("\tInsert Records");
					System.out.println("---------------------------------");
					Student student = new Student();
					System.out.print("Enter the sid :: ");
					Integer sid = scanner.nextInt();

					System.out.print("Enter the sname:: ");
					String sname = scanner.next();

					System.out.print("Enter the sage:: ");
					int sage = scanner.nextInt();

					System.out.print("Enter the saddress:: ");
					String saddress = scanner.next();
					
					student.setSid(sid);
					student.setSname(sname);
					student.setSage(sage);
					student.setSaddress(saddress);
					
					try {
						int rowAffected = controller.insertRecord(student);
						if(rowAffected!=0) {
							System.out.println("Insertion Successful...");
						}else {
							System.out.println("Insertion failure...");
						}
					} catch (SQLException e) {
						System.out.println("Something went wrong..");
					}

					break;
				}
				// Updation
				case 3: {
					System.out.println("---------------------------------");
					System.out.println("\tUpdate Records");
					System.out.println("---------------------------------");
					Student student = new Student();
					ResultSet result = null;
					System.out.println("Enter the sid :: ");
					int sid = scanner.nextInt();
					String oldName = null;
					int oldAge = 0;
					String oldAddress = null;
					boolean isValidId = false; 
					boolean isValidRespose = true; 
					int rowAffected = 0;
					
					student.setSid(sid);
					try {
						result = controller.displayRecord(sid);
						if(result.next()) {
							isValidId = true;
							oldName = result.getString(2);
							oldAge = result.getInt(3);
							oldAddress = result.getString(4);
						} 
					} catch (SQLException e) {
						e.printStackTrace();
					} catch (Exception e) {
						e.printStackTrace();
					} finally {
						try {
							if (result != null)
								result.close();
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
					if(isValidId) {
						
						// sname
						if (oldName != null)
							System.out.println("Student name :: "+ oldName);
						
						System.out.println("Do you want to change the name [YES/NO]::");
						String changeSname = scanner.next();
						if(changeSname.equalsIgnoreCase("yes")) {
							System.out.println("Enter new name ::");
							String sname = scanner.next();
							student.setSname(sname);
							
						} else if(changeSname.equalsIgnoreCase("no")) {
							student.setSname(oldName);
						} else {
							isValidRespose = false;
							System.out.println("Invalid Response...");
						}
						
						//sage
						if (oldAge != 0)
							System.out.println("Student age :: "+ oldAge);
						
						System.out.println("Do you want to change the age [YES/NO]::");
						String changeAge = scanner.next();
						if(changeAge.equalsIgnoreCase("yes")) {
							System.out.println("Enter new age ::");
							int sage = scanner.nextInt();
							student.setSage(sage);
							
						} else if(changeAge.equalsIgnoreCase("no")) {
							student.setSage(oldAge);
						} else {
							isValidRespose = false;
							System.out.println("Invalid Response...");
						}
						
						//saddress
						if (oldAddress != null)
							System.out.println("Student address :: "+ oldAddress);
						
						System.out.println("Do you want to change the address [YES/NO]::");
						String changeAddress = scanner.next();
						if(changeAddress.equalsIgnoreCase("yes")) {
							System.out.println("Enter new address ::");
							String saddress = scanner.next();
							student.setSaddress(saddress);
							
						} else if(changeAddress.equalsIgnoreCase("no")) {
							student.setSaddress(oldAddress);
						} else {
							isValidRespose = false;
							System.out.println("Invalid Response...");
						}
						
						try {
							if (isValidRespose)
								rowAffected = controller.updateRecord(student);
							if(rowAffected!=0) {
								System.out.println("Updation Successful...");
							}else {
								System.out.println("Updation failure...");
							}
						} catch (SQLException e) {
							e.printStackTrace();
						} catch (Exception e) {
							e.printStackTrace();
						}

					} else {
						System.out.println("Invalid Student Id...");
					}
					
					break;
				}
				case 4: {
					System.out.println("---------------------------------");
					System.out.println("\tDelete Record");
					System.out.println("---------------------------------");
					System.out.println("Enter Student ID to delete record :: ");
					int sid = scanner.nextInt();
					ResultSet result = null;
					int rowAffected = 0;
					try {
						result = controller.displayRecord(sid);
						if(result.next()) {
							System.out.println("---------------------------------");
							System.out.println("SID\tSNAME\tSAGE\tSADDRESS");
							System.out.println("---------------------------------");
							System.out.println(result.getInt(1)+"\t"+result.getString(2)+"\t"+result.getInt(3)+"\t"+result.getString(4));
							System.out.println("---------------------------------");
						} else {
							System.out.println("Id not found...");
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					} finally {
						try {
							if (result != null)
								result.close();
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
						
					System.out.println("Do you want to delete this record [YES/NO] :: ");
					String deleteThis = scanner.next();
					if(deleteThis.equalsIgnoreCase("yes")) {
						try {
							rowAffected = controller.deleteRecord(sid);
							if(rowAffected!=0) {
								System.out.println("Deletion Successful...");
							}else {
								System.out.println("Deletion failure...");
							}
						} catch (SQLException e) {
							e.printStackTrace();
						} catch (Exception e) {
							e.printStackTrace();
						}
					} else if(deleteThis.equalsIgnoreCase("no")) {
						System.out.println("Deletion cancelled...");
					} else {
						System.out.println("Wrong choice...");
					}
							
					break;
				}
				case 5: {
					ResultSet result = null;
					System.out.println("---------------------------------");
					System.out.println("\tForgot ID ?");
					System.out.println("---------------------------------");
					System.out.println("Enter name :: ");
					String sname = scanner.next();
					System.out.println("Enter age :: ");
					int sage = scanner.nextInt();
					System.out.println("Enter address :: ");
					String saddress = scanner.next();
					
					try {
						result = controller.forgotId(sname, sage, saddress);
						if(result.next()) {
							System.out.println("This is your details...");
							System.out.println("---------------------------------");
							System.out.println("SID\tSNAME\tSAGE\tSADDRESS");
							System.out.println("---------------------------------");
							System.out.println(result.getInt(1)+"\t"+result.getString(2)+"\t"+result.getInt(3)+"\t"+result.getString(4));
							System.out.println("---------------------------------");
						} else {
							System.out.println("Record not found...");
						}
					} catch (SQLException e) {
						e.printStackTrace();
					} catch (Exception e) {
						e.printStackTrace();
					} finally {
						try {
							if (result != null)
								result.close();
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
					break;
				}
				case 0: {
					System.out.println("Successfully Exited..");
					System.exit(0);
				}
				default:
					System.out.println("Wrong choice...");
				}
			}
		}
	}

}
