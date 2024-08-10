package com.flipkart.business;


import com.flipkart.bean.Admin;
import com.flipkart.bean.GradeCard;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Course;
import com.flipkart.dao.AdminDaoOps;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class AdminOperations2 {
	private List<Admin> admins;
	private List<Course> courseCatalogue;
	private List<Professor> professors;
	private List<Integer> approvedStudents;
	private Map<Integer, GradeCard> gradeCards;
	private ProfessorOperations professorOps;
	private AdminDaoOps adminDaoOps;


	public AdminOperations2() {


		admins = new ArrayList<>();
		approvedStudents = new ArrayList<>();
		professorOps = new ProfessorOperations("admin");
		adminDaoOps = new AdminDaoOps();

		courseCatalogue = new ArrayList<>();

	}


	public Admin findAdminByUsername(String userName) {
		for (Admin admin : admins) {
			if (admin.getUserName().equals(userName)) {
				return admin;
			}
		}
		return null;

	}
//	public Student findStudentById(int studentId) {
//		// Return the student object based on studentId
//
//		for (Student student : students) {
//			if (student.getStudentID() == studentId) {
//				return student;
//			}
//		}
//		return null; // Or handle the case when the student is not found
//	}

	public void approveStudentRegistration(int studentId) {
		approvedStudents.add(studentId);
		System.out.println("Student registration approved for student ID: " + studentId);
	}

	public List<Course> getCourseCatalogue() {

		return courseCatalogue;
	}

	public void addCourse(String course_name, String courseID) {
		Course course = new Course(courseID, course_name, null, 10, 10, true);
		courseCatalogue.add(course);
		System.out.println("Course added successfully");
	}

	public void removeCourse(String courseID) {
		courseCatalogue.removeIf(course -> course.getCourseID().equals(courseID));
		System.out.println("Course removed successfully");
	}

	public Integer addProfessor(String username, String professorName, String role, String password, String department, String designation) {

		return adminDaoOps.addProfessor(username, professorName, role, password, department, designation);

	}

	public void removeProfessor(int professorID) {
//		professorOps = new ProfessorOperations();
		professors = professorOps.getProfessors();
//		professors.removeIf(professor -> professor.getInstructorID().equals(professorID));
//		System.out.println("Prof removed successfully");
		professors.removeIf(professor -> professor.getProfessorId().equals(professorID));
		System.out.println("Professor removed successfully");
	}

	Float CalculateCgpa(GradeCard gc) {
		return null;
	}

	GradeCard generateGradeCard(int studentID) {
		return null;

	}

	public Course findCourseById(String courseID) {
		for (Course course : courseCatalogue) {
			if (course.getCourseID().equals(courseID)) {
				return course;
			}
		}
		return null;
	}

	public void sendFeePayNotification() {
	}

	public void PaymentCompletionNotification() {

	}

	public void viewApprovedStudents() {
		for (Integer studentID : approvedStudents) {
			System.out.println(studentID);
		}
	}

	public void showUnapprovedStudents() {
		System.out.println("The list of unapproved students is:");
		adminDaoOps.printUnapprovedStudents();
		System.out.println("Enter the student id you wish to approve:");
		Scanner sc = new Scanner(System.in);
		int studentId = sc.nextInt();
		adminDaoOps.approveOneStudent(studentId);
	}
}
