package pl.pjatk.clinic.service;

import org.springframework.stereotype.Service;
import pl.pjatk.clinic.exception.PatientException;
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

    public Optional<Patient> findById(int id) {
        return patientRepository.findById(id);
    }


    public Optional<Patient> findByPesel(String pesel) throws PatientException {
        if (patientRepository.findPatientByPesel(pesel).isPresent()) {
            return patientRepository.findPatientByPesel(pesel);
        } else {
            throw new PatientException("There is no one with this PESEL");
        }
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

    public void deleteById(int id) throws PatientException {
        if (patientRepository.findById(id).isPresent()) {
            patientRepository.deleteById(id);
        } else {
            throw new PatientException("There is no such patient to delete");
        }
    }

    public void deleteAll() throws PatientException {
        if (patientRepository.findAll().isEmpty()) {
            throw new PatientException("Repository is empty");
        } else {
            patientRepository.deleteAll();
        }
    }

    public Patient save(Patient patient) {
        if (patientRepository.findPatientByPesel(patient.getPesel()).isPresent()) {
            throw new IllegalArgumentException("There is Patient with this PESEL already");
        } else {
            return patientRepository.save(patient);
        }
    }
}
