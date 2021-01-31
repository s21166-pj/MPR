package pl.pjatk.clinic.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Patient {

    @Id
    @GeneratedValue
    private int id;
    private String name;
    private String surname;
    private String pesel;

    public Patient() {
    }

    public Patient(String name, String surname, String pesel) {
        this.name = name;
        this.surname = surname;
        this.pesel = pesel;
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

    @Override
    public String toString() {
        return "Patient{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", pesel=" + pesel +
                '}';
    }
}
