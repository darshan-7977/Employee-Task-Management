package edu.jsp.ems.controller;

@SuppressWarnings("serial")
public class TaskNotFoundException extends RuntimeException {

	public TaskNotFoundException(String message) {
		super(message);
	}

}
