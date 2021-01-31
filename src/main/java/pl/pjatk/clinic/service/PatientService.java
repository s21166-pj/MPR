package pl.pjatk.clinic.service;

import org.springframework.stereotype.Service;
import pl.pjatk.clinic.exception.PeselException;
import pl.pjatk.clinic.model.Patient;
import pl.pjatk.clinic.repository.PatientRepository;
import pl.pjatk.clinic.validators.Validator;

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

    public void deleteById(int id) {
        patientRepository.deleteById(id);
    }

    public void deleteAll() {
        patientRepository.deleteAll();
    }

    public Patient save(Patient patient) {
        if (patientRepository.findPatientByPesel(patient.getPesel()).isPresent()) {
            throw new IllegalArgumentException("There is Patient with this PESEL already");
        } else {
            return patientRepository.save(patient);
        }
    }
}
