package edu.jsp.ems.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;

@Entity
@Cacheable
public class Task {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "my_seq")
	@SequenceGenerator(name = "my_seq", initialValue = 10, allocationSize = 2, sequenceName = "my_custom_seq")
	private int id;
	private String name;
	private LocalDate startDate;
	private LocalDate endDate;

	@ManyToMany
	List<Employee> employees;

	public Task() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

}