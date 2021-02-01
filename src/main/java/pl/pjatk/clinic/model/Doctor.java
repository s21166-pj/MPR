package pl.pjatk.clinic.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String surname;

    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL)
    private List<Patient> patientList = new ArrayList<>();

    public Doctor() {
    }

    public Doctor(String name, String surname, List<Patient> patientList) {
        this.name = name;
        this.surname = surname;
        this.patientList = patientList;
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

    @Override
    public String toString() {
        return "Doctor{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                '}';
    }
}
