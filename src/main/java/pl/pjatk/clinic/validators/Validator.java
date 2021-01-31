package pl.pjatk.clinic.validators;

import pl.pjatk.clinic.model.Doctor;
import pl.pjatk.clinic.model.Patient;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Validator {

    public Validator() {
    }

    public List<String> validatePatient(Patient patient) {
        List<String> listOfString = new ArrayList<>();
        listOfString.add(validateName(patient.getName()));
        listOfString.add(validateSurName(patient.getSurname()));
        listOfString.add(validatePesel(patient.getPesel()));

        return listOfString.parallelStream()
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    public List<String> validateDoctor(Doctor doctor) {
        List<String> listOfString = new ArrayList<>();
        listOfString.add(validateName(doctor.getName()));
        listOfString.add(validateSurName(doctor.getSurname()));

        return listOfString.parallelStream()
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    public String validateName(String name) {
        if (!name.isEmpty() && name.matches("[a-zA-Z]+")) {
            return null;
        } else {
            return "Name can consist of only letters or is empty";
        }
    }

    public String validateSurName(String surname) {
        if (!surname.isEmpty() && surname.matches("[a-zA-Z]+")) {
            return null;
        } else {
            return "Surname can consist of only letters or is empty";
        }
    }

    public String validatePesel(String pesel) {
        int[] controlWeight = {1, 3, 7, 9, 1, 3, 7, 9, 1, 3};

        if (pesel.length() != 11) return "Pesel has incorrect length";
        int controlSum = 0;
        for (int i = 0; i < 10; i++)

            controlSum += Integer.parseInt(pesel.substring(i, i + 1)) * controlWeight[i];

        int controlDigit = Integer.parseInt(pesel.substring(10, 11));

        controlSum %= 10;
        controlSum = 10 - controlSum;
        controlSum %= 10;

        if ((controlSum == controlDigit)) {
            return null;
        } else {
            return "Pesel is incorrect";
        }
    }
}
