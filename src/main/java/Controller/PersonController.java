package Controller;

import Model.Person;
import Services.PersonServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
public class PersonController {

    private PersonServices service;

    public PersonController(PersonServices service){
        this.service = service;
    }

    @PostMapping("/person")
    public ResponseEntity<Person> create(@Valid @RequestBody Person person){
        return new ResponseEntity<>(service.create(person), HttpStatus.CREATED);
    }

    @GetMapping("/person/{id}")
    public @ResponseBody ResponseEntity<Person> getPerson(@PathVariable Long id){
        return new ResponseEntity<>(service.getPerson(id), HttpStatus.OK);
    }

    @PutMapping("/person/update")
    public ResponseEntity<Person> updatePerson(Long id, Person person){
        return new ResponseEntity<>(service.update(id, person), HttpStatus.OK);
    }

    @GetMapping("/person")
    public ResponseEntity<Iterable<Person>> getPersonList(){
        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
    }

    @DeleteMapping("/person/{id}")
    public ResponseEntity<Boolean> deletePerson(@PathVariable Long id){
        return new ResponseEntity<>(service.delete(id), HttpStatus.OK);
    }

}
