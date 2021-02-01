package pl.pjatk.clinic.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.pjatk.clinic.exception.PatientException;
import pl.pjatk.clinic.model.Patient;
import pl.pjatk.clinic.service.PatientService;
import pl.pjatk.clinic.validators.Validator;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/patient")
public class PatientController {

    private PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping
    public ResponseEntity<List<Patient>> findAll() {
        return ResponseEntity.ok(patientService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Patient>> findById(@PathVariable int id) throws PatientException {
        Optional<Patient> findById = patientService.findById(id);
        if (findById.isPresent()) {
            return ResponseEntity.ok(findById);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @GetMapping("/bypesel/{pesel}")
    public ResponseEntity<Optional<Patient>> findByPesel(@PathVariable String pesel) throws PatientException {
        Optional<Patient> byPesel = patientService.findByPesel(pesel);
        if (byPesel.isPresent()) {
            return ResponseEntity.ok(byPesel);
        } else {
            throw new PatientException("No match found");
        }
    }

    @PostMapping
    public ResponseEntity<Patient> save(@RequestBody Patient patient) throws PatientException {
        Validator validator = new Validator();
        List<String> messages;
        messages = validator.validatePatient(patient);
        if (messages.isEmpty()) {
            return ResponseEntity.ok(patientService.save(patient));
        } else {
            throw new PatientException(messages.toString());
        }
    }

    @PutMapping("/{pesel}")
    public ResponseEntity<Patient> update(@PathVariable String pesel, @RequestBody Patient patient) {
        return ResponseEntity.ok(patientService.update(pesel, patient));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) throws PatientException {
        patientService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity<List<Patient>> deleteAll() throws PatientException {
        patientService.deleteAll();
        return ResponseEntity.ok().build();
    }
}
