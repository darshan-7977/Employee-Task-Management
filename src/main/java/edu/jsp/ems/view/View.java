package edu.jsp.ems.view;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

import edu.jsp.ems.controller.Controller;
import edu.jsp.ems.controller.EmployeeNotFoundException;
import edu.jsp.ems.controller.TaskNotFoundException;
import edu.jsp.ems.model.Employee;
import edu.jsp.ems.model.Task;

public class View {

	Scanner scanner = new Scanner(System.in);
	Controller controller = new Controller();
	boolean loop = true;

	public static void main(String[] args) {
		View view = new View();
		while (view.loop) {
			view.mainView();
		}
	}

	public static void displayMenu() {
		System.out.println("1. Add Employee");
		System.out.println("2. Remove Employee");
		System.out.println("3. Search Employee");
		System.out.println("4. Get All Employees");
		System.out.println("5. Update Employee");
		System.out.println("6. Add Task");
		System.out.println("7. Remove Task");
		System.out.println("8. Search Task");
		System.out.println("9. Get All Tasks");
		System.out.println("10. Update Task");
		System.out.println("11. Assign Task to Employee");
		System.out.println("0. Exit");
	}

	public void mainView() {
		displayMenu();
		System.out.println("Enter your choice: ");
		int choice = scanner.nextInt();
		scanner.nextLine();

		switch (choice) {
		case 1:
			addEmployee();
			break;
		case 2:
			removeEmployee();
			break;
		case 3:
			searchEmployee();
			break;
		case 4:
			getAllEmployee();
			break;
		case 5:
			updateEmployeeMenu();
			updateEmployeeView();
			break;
		case 6:
			addTask();
			break;
		case 7:
			removeTask();
			break;
		case 8:
			searchTask();
			break;
		case 9:
			getAllTasks();
			break;
		case 10:
			updateTaskMenu();
			updateTaskView();
			break;
		case 11:
			assignTask();
			break;
		case 0:
			scanner.close();
			this.loop = false;
			System.out.println("Exiting...\n\t-----Thank-You-----");
			break;
		default:
			System.out.println("Invalid choice. Please choose a valid option.");
			break;
		}
	}

	public static void updateEmployeeMenu() {
		System.out.println("1. Update all");
		System.out.println("2. Update Employee Name");
		System.out.println("3. Update age");
		System.out.println("4. Update salary");
		System.out.println("5. Go to Main Menu");
	}

	public void updateEmployeeView() {
		System.out.println("Enter your choice: ");
		int choice = scanner.nextInt();
		scanner.nextLine();
		switch (choice) {
		case 1:
			updateEmployeeDetails();
			break;
		case 2:
			updateEmployeeName();
			break;
		case 3:
			updateEmployeeAge();
			break;
		case 4:
			updateEmployeeSalary();
			break;
		case 5:
			displayMenu();
			break;
		default:
			System.out.println("Invalid choice. Please choose a valid option.");
			break;
		}
	}

	public static void updateTaskMenu() {
		System.out.println("1. Update all");
		System.out.println("2. Update task name");
		System.out.println("3. Update start date");
		System.out.println("4. Extend the deadline");
		System.out.println("5. Go to Main Menu");
	}

	public void updateTaskView() {
		System.out.println("Enter your choice: ");
		int choice = scanner.nextInt();
		scanner.nextLine();
		switch (choice) {
		case 1:
			updateTaskDetails();
			break;
		case 2:
			updateTaskName();
			break;
		case 3:
			updateTaskStartDate();
			break;
		case 4:
			updateTaskEndDate();
			break;
		case 5:
			displayMenu();
			break;
		default:
			System.out.println("Invalid choice. Please choose a valid option.");
			break;
		}
	}

	public void addEmployee() {
		try {
			Employee employee = new Employee();
			System.out.println("Enter Employee name: ");
			employee.setName(scanner.nextLine());
			System.out.println("Enter Employee age: ");
			employee.setAge(scanner.nextInt());
			scanner.nextLine();
			System.out.println("Enter Employee salary: ");
			employee.setSalary(scanner.nextDouble());

			if (controller.addEmployee(employee)) {
				System.out.println("Employees saved");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void removeEmployee() {
		try {
			System.out.println("Enter Employee id: ");
			int id = scanner.nextInt();
			if (controller.removeEmployee(id)) {
				System.out.println("Employee removed successfully");
			}
		} catch (EmployeeNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	public void searchEmployee() {
		try {
			System.out.println("Enter the id of the employee to be found: ");
			int id = scanner.nextInt();
			Employee employee = controller.searchEmployee(id);
			if (employee != null) {
				System.out.println("Employee ID: " + employee.getId() + "\nEmployee name: " + employee.getName()
						+ "\nEmployee age: " + employee.getAge() + "\nEmployee date of joining: " + employee.getDOJ()
						+ "\nEmployee salary: " + employee.getSalary() + "\nEmployee Tasks: " + employee.getTasks());
			}
		} catch (EmployeeNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void getAllEmployee() {
		try {
			List<Employee> employees = controller.getAllEmployee();
			if (!employees.isEmpty()) {
				for (Employee employee : employees) {
					System.out.println("Employee ID: " + employee.getId() + "\nEmployee name: " + employee.getName()
							+ "\nEmployee age: " + employee.getAge() + "\nEmployee date of joining: "
							+ employee.getDOJ() + "\nEmployee salary: " + employee.getSalary() + "\nEmployee Tasks: "
							+ employee.getTasks() + "\n-------------------------------------");
				}
			}
		} catch (EmployeeNotFoundException e) {
			System.out.println(e.getMessage());
		}
	}

	public void addTask() {
		try {
			Task task = new Task();
			System.out.println("Enter Task name: ");
			task.setName(scanner.nextLine());
			System.out.println("Enter Starting date of the task (year-month-date): ");
			String startDate = scanner.nextLine();
			LocalDate date = LocalDate.parse(startDate);
			task.setStartDate(date);
			System.out.println("Enter the Deadline to be set (year-month-date): ");
			String endDate = scanner.nextLine();
			LocalDate date2 = LocalDate.parse(endDate);
			task.setEndDate(date2);

			if (controller.addTask(task)) {
				System.out.println("Task added successfully");
			} else {
				System.out.println("Something went wrong");
			}
		} catch (DateTimeParseException e) {
			System.out.println("Invalid date format, Please enter the date in the specified format.");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void removeTask() {
		try {
			System.out.println("Enter Task id: ");
			int id = scanner.nextInt();
			if (controller.removeTask(id)) {
				System.out.println("Task removed successfully");
			}
		} catch (TaskNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void searchTask() {
		try {
			System.out.println("Enter the id of the task to be found: ");
			int id = scanner.nextInt();
			Task task = controller.searchTask(id);
			if (task != null) {
				System.out.println("Task ID: " + task.getId() + "\nTask name: " + task.getName()
						+ "\nTask starting date: " + task.getStartDate() + "\nTask end date: " + task.getEndDate()
						+ "\nEmployees assigned to this above task: " + task.getEmployees());
			}
		} catch (TaskNotFoundException e) {
			System.out.println(e.getMessage());
		}
	}

	public void getAllTasks() {
		try {
			List<Task> tasks = controller.getAllTasks();
			if (!tasks.isEmpty()) {
				for (Task task : tasks) {
					System.out.println("Task ID: " + task.getId() + "\nTask name: " + task.getName() + "\nStart date: "
							+ task.getStartDate() + "\nDeadline: " + task.getEndDate()
							+ "\nEmployees assigned to this task" + task.getEmployees()
							+ "\n-------------------------------------");
				}
			}
		} catch (TaskNotFoundException e) {
			System.out.println(e.getMessage());
		}
	}

	public void assignTask() {
		try {
			System.out.println("Enter task ID to assign: ");
			int tid = scanner.nextInt();
			if (controller.checkTaskAvailability(tid)) {
				System.out.println("Enter employee ID to assign task: ");
				int empid = scanner.nextInt();
				controller.checkEmployeeAvailability(empid);
				if (controller.assignTask(tid, empid)) {
					System.out.println("Task assigned successfully");
				}
			}
		} catch (EmployeeNotFoundException e) {
			e.getMessage();
		} catch (TaskNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void updateEmployeeName() {
		try {
			System.out.println("Enter the Employee ID: ");
			int id = scanner.nextInt();
			scanner.nextLine();
			if (controller.checkEmployeeAvailability(id)) {
				System.out.println("Enter new employee name: ");
				String newName = scanner.nextLine();
				if (controller.updateEmployeeName(id, newName)) {
					System.out.println("Employee name updated Successfully");
				}
			}
		} catch (EmployeeNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	public void updateEmployeeAge() {
		try {
			System.out.println("Enter the Employee ID: ");
			int id = scanner.nextInt();
			if (controller.checkEmployeeAvailability(id)) {
				System.out.println("Enter new employee age: ");
				int newAge = scanner.nextInt();
				if (controller.updateEmployeeAge(id, newAge)) {
					System.out.println("Employee age updated Successfully");
				}
			}
		} catch (EmployeeNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void updateEmployeeSalary() {
		try {
			System.out.println("Enter the Employee ID: ");
			int id = scanner.nextInt();
			if (controller.checkEmployeeAvailability(id)) {
				System.out.println("Enter the new Salary: ");
				double newSalary = scanner.nextDouble();
				if (controller.updateEmployeeSalary(id, newSalary)) {
					System.out.println("Salary updated successfully");
				}
			}
		} catch (EmployeeNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void updateTaskName() {
		try {
			System.out.println("Enter the task ID: ");
			int id = scanner.nextInt();
			scanner.nextLine();
			if (controller.checkTaskAvailability(id)) {
				System.out.println("Enter the task new name: ");
				String newName = scanner.nextLine();
				if (controller.updateTaskName(id, newName)) {
					System.out.println("Task name updated successfully.");
				}
			}
		} catch (TaskNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void updateTaskStartDate() {
		try {
			System.out.println("Enter the task ID: ");
			int id = scanner.nextInt();
			scanner.nextLine();
			if (controller.checkTaskAvailability(id)) {
				System.out.println("Enter the new Starting date of the task (year-month-date): ");
				String startDate = scanner.nextLine();
				LocalDate date = LocalDate.parse(startDate);
				if (controller.updateTaskStartDate(id, date)) {
					System.out.println("New starting date of the task updated");
				}
			}
		} catch (TaskNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void updateTaskEndDate() {
		try {
			System.out.println("Enter the task ID: ");
			int id = scanner.nextInt();
			scanner.nextLine();
			if (controller.checkTaskAvailability(id)) {
				System.out.println("Enter the new Deadline of the task (year-month-date): ");
				String newEndDate = scanner.nextLine();
				LocalDate date = LocalDate.parse(newEndDate);
				if (controller.updateTaskDeadline(id, date)) {
					System.out.println("New Deadline of the existing task updated");
				}
			}
		} catch (TaskNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void updateEmployeeDetails() {
		try {
			Employee employee = new Employee();
			System.out.println("Enter the Employee ID: ");
			int id = scanner.nextInt();
			scanner.nextLine();
			if (controller.checkEmployeeAvailability(id)) {
				System.out.println("Enter employee new name: ");
				employee.setName(scanner.nextLine());
				System.out.println("Enter employee new age: ");
				employee.setAge(scanner.nextInt());
				scanner.nextLine();
				System.out.println("Enter employee new salary");
				employee.setSalary(scanner.nextDouble());
				if (controller.updateEmployeeDetails(id, employee)) {
					System.out.println("Employee details updated successfully");
				}
			}
		} catch (EmployeeNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void updateTaskDetails() {
		try {
			Task task = new Task();
			System.out.println("Enter the task ID: ");
			int id = scanner.nextInt();
			scanner.nextLine();
			if (controller.checkTaskAvailability(id)) {
				System.out.println("Enter the task new name: ");
				task.setName(scanner.nextLine());
				System.out.println("Enter the new Starting date of the task (year-month-date): ");
				String startDate = scanner.nextLine();
				LocalDate date = LocalDate.parse(startDate);
				task.setStartDate(date);
				System.out.println("Enter the new Deadline of the task (year-month-date): ");
				String newEndDate = scanner.nextLine();
				LocalDate date2 = LocalDate.parse(newEndDate);
				task.setEndDate(date2);
				if (controller.updateTaskDetails(id, task)) {
					System.out.println("Task new details updated successfully");
				}
			}
		} catch (TaskNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}