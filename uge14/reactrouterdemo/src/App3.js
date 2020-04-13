import React, {useState} from "react";
import "./App.css";
import PersonList from "./PersonList"
import NewPerson from "./NewPerson"
import uuid from "uuid/v1";

function App() {
  const initialData = [
    { id: uuid(), name: "Allan" },
    { id: uuid(), name: "Thomas" },
    { id: uuid(), name: "Tobias" },
    { id: uuid(), name: "Nina" },
    { id: uuid(), name: "Carol" }

  ]
  const [persons, setPersons] = useState(initialData);
  const [newPerson, setNewPerson] = useState({ id: "", name: "" });

  const addPerson = person => {
    if (person.id === "") { // id=-1 Indicates a new object
      person.id = uuid();
      persons.push(person);
    } else {//if id != "", it's an existing todo. Find it and add changes
      let personToEdit = persons.find(t => t.id === person.id);
      personToEdit.name = person.name;
    }
    setPersons([...persons]);
    setNewPerson({id:"", name:""})
  };
  
  const deletePerson = (index) => {
    let newPersonList = persons.filter(element =>
      {if(element.id != index) {
        return element;
      }}
      )
    setPersons([...newPersonList])
  }

  const editPerson = (index, text) => {
    setNewPerson({id:"" + index, name:""+text})
  }


  return (
    <div className="container outer">
      <h3 style={{ textAlign: "left", marginBottom:15 }}>
        Total Persons: {persons.length}
      </h3>

      <div className="row">
        <div className="col-6 allTodos">
          <PersonList
           persons={persons} 
           deletePerson={deletePerson}
           editPerson={editPerson}
           />
        </div>
        <div className="col-5 new-todo">
          <NewPerson
            addPerson={addPerson}
            nextPerson={newPerson}            
          />
        </div>
      </div>
    </div>
  );
}
export default App;
