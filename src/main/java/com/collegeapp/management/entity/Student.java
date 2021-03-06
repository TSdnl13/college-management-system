package com.collegeapp.management.entity;

import org.springframework.data.domain.Persistable;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Entity
@Table(name = "students")
public class Student implements Persistable<String> {

    @Id
    @Column(name = "id", columnDefinition = "CHAR(7)")
    private String id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "dni", nullable = false)
    private String dni;

    @Column(name = "email", nullable = false)
    private String email;

    private String telephone;
    private String address;

    @Column(name = "dob", nullable = false)
    private LocalDate dob;

    @Transient
    private Integer age;

    @Transient
    private boolean isNew = true;

    @ManyToMany(mappedBy = "students")
    private List<Classroom> classrooms;

    @OneToMany(mappedBy = "studentId", cascade = {CascadeType.ALL})
    private List<StudentExam> exams;

    public Student() {
    }

    public Student(String id,
                   String firstName,
                   String lastName,
                   String dni,
                   String email,
                   String telephone,
                   String address,
                   LocalDate dob) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dni = dni;
        this.email = email;
        this.telephone = telephone;
        this.address = address;
        this.dob = dob;
    }

    @Override
    public boolean isNew() {
        return this.isNew;
    }

    @PrePersist
    @PostLoad
    void markNotNew() {
        this.isNew = false;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public Integer getAge() {
        return Period.between(this.dob, LocalDate.now()).getYears();
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setClassrooms(List<Classroom> classrooms) {
        this.classrooms = classrooms;
    }

    public List<StudentExam> getExams() {
        return exams;
    }

    public void setExams(List<StudentExam> exams) {
        this.exams = exams;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id='" + id + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dni='" + dni + '\'' +
                ", email='" + email + '\'' +
                ", telephone='" + telephone + '\'' +
                ", address='" + address + '\'' +
                ", dob=" + dob +
                '}';
    }
}