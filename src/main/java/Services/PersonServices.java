package Services;

import Model.Person;
import Repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonServices {

    @Autowired
    private PersonRepository repository;

//    public PersonServices(PersonRepository repository){
//        this.repository = repository;
//    }

    public List<Person> getAll(){
        return (List<Person>) repository.findAll();
    }

    public Person getPerson(Long id){
        return repository.findOne(id);
    }

    public Person create(Person person){
        return repository.save(person);
    }

    public Person update(Long id, Person person){
        Person originalPerson = repository.findOne(id);
        originalPerson.setFirstName(person.getFirstName());
        originalPerson.setLastName(person.getLastName());
        return repository.save(originalPerson);
    }

    public Boolean delete(Long id){
        repository.delete(id);
        return true;
    }


}
