package pl.pjatk.clinic.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String surname;
    private String pesel;
    private Date dateOfConsultation;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    public Patient() {
    }

    public Patient(String name, String surname, String pesel, Date dateOfConsultation, Doctor doctor) {
        this.name = name;
        this.surname = surname;
        this.pesel = pesel;
        this.dateOfConsultation = dateOfConsultation;
        this.doctor = doctor;
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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    public Date getDateOfConsultation() {
        return dateOfConsultation;
    }

    public void setDateOfConsultation(Date dateOfConsultation) {
        this.dateOfConsultation = dateOfConsultation;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", pesel='" + pesel + '\'' +
                ", dateOfConsultation=" + dateOfConsultation +
                ", doctor=" + doctor +
                '}';
    }
}
