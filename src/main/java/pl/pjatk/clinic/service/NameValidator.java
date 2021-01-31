package pl.pjatk.clinic.service;

public class NameValidator {

    public static boolean isValidName(String name) {
        return !name.isEmpty() && name.matches("[a-zA-Z]+");
    }
}
