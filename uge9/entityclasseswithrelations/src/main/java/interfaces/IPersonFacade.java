/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import dto.PersonDTO;
import dto.PersonsDTO;
import entities.Address;
import exceptions.MissingInputException;
import exceptions.PersonNotFoundException;

/**
 *
 * @author allan
 */
public interface IPersonFacade {

    public PersonDTO addPerson(String fName, String lName, String phone, Address address) throws MissingInputException;

    public PersonDTO deletePerson(int id) throws PersonNotFoundException;

    public PersonDTO getPerson(int id) throws PersonNotFoundException;

    public PersonsDTO getAllPersons();

    public PersonDTO editPerson(PersonDTO p) throws PersonNotFoundException, MissingInputException;
}
