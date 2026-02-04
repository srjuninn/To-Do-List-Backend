package com.List.ToDo.entities;

import java.time.LocalDate;

import com.List.ToDo.dto.TaskDTO;

import jakarta.persistence.*;

@Entity
@Table(name = "task")
//	Atributes
public class TaskEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(length = 100)
	private String name;
	@Column(length = 2000)
	private String description;
	private Status status;
	private LocalDate beginDate;
	private LocalDate endDate;

//	Getters and Setters
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public LocalDate getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(LocalDate beginDate) {
		this.beginDate = beginDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}
//	Constructor without arguments

	public TaskEntity() {
		super();
	}
//	Constructor with arguments

	public TaskEntity(String name, String description, Status status, LocalDate beginDate, LocalDate endDate) {
		super();
		this.name = name;
		this.description = description;
		this.status = status;
		this.beginDate = beginDate;
		this.endDate = endDate;
	}

	public TaskEntity(TaskDTO dto) {
		super();
		this.name = dto.getName();
		this.description = dto.getDescription();
		this.status = Status.PENDING;
		this.beginDate = LocalDate.now();
	}

	@ManyToOne
	@JoinColumn(name = "user_id")
	private UserEntity user;
}
