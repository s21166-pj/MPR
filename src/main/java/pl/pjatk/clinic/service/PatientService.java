package pl.pjatk.clinic.service;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.pjatk.clinic.exception.PeselException;
import pl.pjatk.clinic.model.Patient;
import pl.pjatk.clinic.repository.PatientRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PatientService {

    private PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public List<Patient> findAll() {
        return patientRepository.findAll();
    }

    public Optional<Patient> findByPesel(String pesel) {
        return patientRepository.findPatientByPesel(pesel);
    }

    public Patient update(String pesel, Patient updatedPatient) {
        Optional<Patient> patientOptional = patientRepository.findPatientByPesel(pesel);
        if (patientOptional.isPresent()) {
            Patient patient = patientOptional.get();
            patient.setName(updatedPatient.getName());
            patient.setSurname(updatedPatient.getSurname());
            //To change, pesel shouldn't be something u can change
            patient.setPesel(updatedPatient.getPesel());
            return patientRepository.save(patient);
        } else {
            return null;
        }
    }

    public Patient save(Patient patient) throws PeselException {
        //checks name, surname and pesel for validation
        //also prevents from saving patient with the same pesel no.
        if (!NameValidator.isValidName(patient.getName())) {
            throw new IllegalArgumentException("Name can consist of only letters or is empty");
        } else if (!NameValidator.isValidName(patient.getSurname())) {
            throw new IllegalArgumentException("Surname can consist of only letters or is empty");
        } else if (!checkPesel(patient.getPesel())) {
            throw new PeselException();
        } else if (patientRepository.findPatientByPesel(patient.getPesel()).isPresent()) {
            throw new IllegalArgumentException("Patient with this pesel already exists");
        } else {
            return patientRepository.save(patient);
        }
    }

    public static boolean checkPesel(String pesel) {
        int[] controlWeight = {1, 3, 7, 9, 1, 3, 7, 9, 1, 3};

        if (pesel.length() != 11) return false;
        int controlSum = 0;
        for (int i = 0; i < 10; i++)

            controlSum += Integer.parseInt(pesel.substring(i, i + 1)) * controlWeight[i];

        int controlDigit = Integer.parseInt(pesel.substring(10, 11));

        controlSum %= 10;
        controlSum = 10 - controlSum;
        controlSum %= 10;

        return (controlSum == controlDigit);
    }
}
