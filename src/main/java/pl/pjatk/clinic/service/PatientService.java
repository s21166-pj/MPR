package pl.pjatk.clinic.service;

import org.springframework.stereotype.Service;
import pl.pjatk.clinic.exception.PeselException;
import pl.pjatk.clinic.model.Patient;
import pl.pjatk.clinic.repository.PatientRepository;

import java.util.List;

@Service
public class PatientService {

    private PatientRepository patientRepository;

    public List<Patient> findAll() {
        return patientRepository.findAll();
    }

    public Patient save(Patient patient) throws PeselException {
        if (checkPesel(patient.getPesel())) {
            return patientRepository.save(patient);
        } else {
            throw new PeselException();
        }
    }

    public static boolean checkPesel(String pesel) {
        int[] controlWeight = {1, 3, 7, 9, 1, 3, 7, 9, 1, 3};

        if (pesel.length() != 11) return false;
        int controlSum = 0;
        for (int i = 0; i < 10; i++)

            controlSum += Integer.parseInt(pesel.substring(i, i + 1)) * controlWeight[i];

        int controllDigit = Integer.parseInt(pesel.substring(10, 11));

        controlSum %= 10;
        controlSum = 10 - controlSum;
        controlSum %= 10;

        return (controlSum == controllDigit);
    }
}
