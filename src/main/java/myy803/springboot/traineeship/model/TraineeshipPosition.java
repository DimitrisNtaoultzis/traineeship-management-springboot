package myy803.springboot.traineeship.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Table;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
@Table(name="traineeshipPositions")
public class TraineeshipPosition {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;
	String title;
	String description;
	LocalDate fromDate;
	LocalDate toDate;
	String topics;
	String skills;
	boolean isAssigned;
	String studentLogbook;
	boolean passFailGrade;
	boolean evaluationComm;
	
	@OneToOne(mappedBy = "assignedPosition")
	Student student;
	@ManyToOne
    @JoinColumn(name = "professor_username")
	Professor supervisor;
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_username")
	Company company;

	@OneToMany(cascade = CascadeType.ALL)
    @JoinTable(
        name = "traineeship_evaluation",
        joinColumns = @JoinColumn(name = "traineeship_id"),
        inverseJoinColumns = @JoinColumn(name = "evaluation_id")
    )
	List<Evaluation> evaluations = new ArrayList<Evaluation>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDate getFromDate() {
		return fromDate;
	}

	public void setFromDate(LocalDate fromDate) {
		this.fromDate = fromDate;
	}

	public LocalDate getToDate() {
		return toDate;
	}

	public void setToDate(LocalDate toDate) {
		this.toDate = toDate;
	}

	public String getTopics() {
		return topics;
	}

	public void setTopics(String topics) {
		this.topics = topics;
	}

	public String getSkills() {
		return skills;
	}

	public void setSkills(String skills) {
		this.skills = skills;
	}

	public boolean isAssigned() {
		return isAssigned;
	}

	public void setAssigned(boolean isAssigned) {
		this.isAssigned = isAssigned;
	}

	public String getStudentLogbook() {
		return studentLogbook;
	}

	public void setStudentLogbook(String studentLogbook) {
		this.studentLogbook = studentLogbook;
	}

	public boolean isPassFailGrade() {
		return passFailGrade;
	}

	public void setPassFailGrade(boolean passFailGrade) {
		this.passFailGrade = passFailGrade;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Professor getSupervisor() {
		return supervisor;
	}

	public void setSupervisor(Professor supervisor) {
		this.supervisor = supervisor;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public List<Evaluation> getEvaluations() {
		return evaluations;
	}

	public void setEvaluations(List<Evaluation> evaluations) {
		this.evaluations = evaluations;
	}
}
