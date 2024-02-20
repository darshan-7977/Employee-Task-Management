package edu.jsp.ems.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import edu.jsp.ems.model.Employee;
import edu.jsp.ems.model.Task;

public class Controller {

	EntityManagerFactory factory = Persistence.createEntityManagerFactory("ems");
	EntityManager manager = factory.createEntityManager();
	EntityTransaction transaction = manager.getTransaction();

	public boolean addEmployee(Employee employee) {
		transaction.begin();
		manager.persist(employee);
		transaction.commit();
		return true;
	}

	public boolean removeEmployee(int id) {
		Employee employee = manager.find(Employee.class, id);
		if (employee != null) {
			for (Task task : new ArrayList<>(employee.getTasks())) {
				task.getEmployees().remove(employee);
				manager.merge(task);
			}
			transaction.begin();
			manager.remove(employee);
			transaction.commit();
			return true;
		} else {
			throw new EmployeeNotFoundException("Employee with ID " + id + " not found.");

		}
	}

	public Employee searchEmployee(int id) {
		Employee employee = manager.find(Employee.class, id);
		if (employee != null) {
			return employee;
		} else {
			throw new EmployeeNotFoundException("Employee with ID " + id + " not found.");
		}
	}

	public List<Employee> getAllEmployee() {
		String query = "select e from Employee e";
		Query query2 = manager.createQuery(query);
		@SuppressWarnings("unchecked")
		List<Employee> employees = (List<Employee>) query2.getResultList();
		if (employees != null && !employees.isEmpty()) {
			return employees;
		} else {
			throw new EmployeeNotFoundException("No Employees Found");
		}
	}

	public boolean addTask(Task task) {
		if (task != null) {
			transaction.begin();
			manager.persist(task);
			transaction.commit();
			return true;
		} else {
			return false;
		}
	}

	public boolean removeTask(int id) {
		Task task = manager.find(Task.class, id);
		if (task != null) {
			for (Employee employee : task.getEmployees()) {
				employee.getTasks().remove(task);
				manager.merge(employee);
			}
			transaction.begin();
			manager.remove(task);
			transaction.commit();
			return true;
		} else {
			throw new TaskNotFoundException("Task with ID " + id + " not found.");
		}
	}

	public List<Task> getAllTasks() {
		String query = "select t from Task t";
		Query query2 = manager.createQuery(query);
		@SuppressWarnings("unchecked")
		List<Task> tasks = (List<Task>) query2.getResultList();
		if (tasks != null) {
			return tasks;
		} else {
			throw new TaskNotFoundException("No Tasks Found..");
		}
	}

	public Task searchTask(int id) {
		Task task = manager.find(Task.class, id);
		if (task != null) {
			return task;
		} else {
			throw new TaskNotFoundException("Task with ID " + id + " not found.");
		}
	}

	public boolean assignTask(int tid, int empid) {
		Employee employee = manager.find(Employee.class, empid);
		Task task = manager.find(Task.class, tid);
		List<Task> tasks = new ArrayList<Task>();
		tasks.add(task);
		List<Employee> employees = new ArrayList<Employee>();
		employees.add(employee);

		if (employee != null && task != null) {
			employee.setTasks(tasks);
			task.setEmployees(employees);
			transaction.begin();
			manager.merge(employee);
			manager.merge(task);
			transaction.commit();
			return true;
		} else {
			throw new EmployeeNotFoundException("No Employees Found");
		}
	}

	public boolean updateEmployeeName(int id, String name) {
		Employee employee = manager.find(Employee.class, id);
		if (employee != null) {
			employee.setName(name);
			transaction.begin();
			manager.merge(employee);
			transaction.commit();
			return true;
		} else {
			throw new EmployeeNotFoundException("Employee with ID " + id + " not found.");
		}
	}

	public boolean updateEmployeeAge(int id, int newAge) {
		Employee employee = manager.find(Employee.class, id);
		if (employee != null) {
			employee.setAge(newAge);
			transaction.begin();
			manager.merge(employee);
			transaction.commit();
			return true;
		} else {
			throw new EmployeeNotFoundException("Employee with ID " + id + " not found.");
		}
	}

	public boolean updateEmployeeSalary(int id, double newSalary) {
		Employee employee = manager.find(Employee.class, id);
		if (employee != null) {
			employee.setSalary(newSalary);
			transaction.begin();
			manager.merge(employee);
			transaction.commit();
			return true;
		} else {
			throw new EmployeeNotFoundException("Employee with ID " + id + " not found.");
		}
	}

	public boolean updateTaskName(int id, String name) {
		Task task = manager.find(Task.class, id);
		if (task != null) {
			task.setName(name);
			transaction.begin();
			manager.merge(task);
			transaction.commit();
			return true;
		} else {
			throw new TaskNotFoundException("Task with ID " + id + " not found");
		}
	}

	public boolean updateTaskStartDate(int id, LocalDate date) {
		Task task = manager.find(Task.class, id);
		if (task != null) {
			task.setStartDate(date);
			transaction.begin();
			manager.merge(task);
			transaction.commit();
			return true;
		} else {
			throw new TaskNotFoundException("Task with ID " + id + " not found");
		}
	}

	public boolean updateTaskDeadline(int id, LocalDate date) {
		Task task = manager.find(Task.class, id);
		if (task != null) {
			task.setEndDate(date);
			transaction.begin();
			manager.merge(task);
			transaction.commit();
			return true;
		} else {
			throw new TaskNotFoundException("Task with ID " + id + " not found");
		}
	}

	public boolean updateEmployeeDetails(int id, Employee employee) {
		Controller controller = new Controller();
		if (employee != null) {
			controller.updateEmployeeName(id, employee.getName());
			controller.updateEmployeeAge(id, employee.getAge());
			controller.updateEmployeeSalary(id, employee.getSalary());
			return true;
		} else {
			throw new EmployeeNotFoundException("Employee with ID " + id + " not found.");
		}
	}

	public boolean updateTaskDetails(int id, Task task) {
		Controller controller = new Controller();
		if (task != null) {
			controller.updateTaskName(id, task.getName());
			controller.updateTaskStartDate(id, task.getStartDate());
			controller.updateTaskDeadline(id, task.getEndDate());
			return true;
		} else {
			throw new TaskNotFoundException("Task with ID " + id + " not found");
		}
	}

	public boolean checkEmployeeAvailability(int id) {
		Employee employee = manager.find(Employee.class, id);
		if (employee != null) {
			return true;
		} else {
			throw new EmployeeNotFoundException("Employee with ID " + id + " not found.");
		}
	}

	public boolean checkTaskAvailability(int id) {
		Task task = manager.find(Task.class, id);
		if (task != null) {
			return true;
		} else {
			throw new TaskNotFoundException("Task with ID " + id + " not found");
		}
	}

}