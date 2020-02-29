/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import entities.Person;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author allan
 */
public class PersonsDTO {
    List<PersonDTO> all = new ArrayList<PersonDTO>();

    public void addPersonsArray(List<Person> persons){
        all = new ArrayList<PersonDTO>();
        for (Person person : persons) {
            all.add(new PersonDTO(person));
            
        }
                
        
    }
    
    public void addPerson(Person p){
        all.add(new PersonDTO(p));
    }   
    
    public List<PersonDTO> getAll() {
        return all;
    }
    
    
}
