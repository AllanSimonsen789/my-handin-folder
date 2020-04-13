import React from 'react';
import PropTypes from 'prop-types';

const PersonList = (props) => {
  const { persons, editPerson, deletePerson } = props
  return (
    <React.Fragment>
      <h2>Complete persons</h2>
      <table>
        <thead>
          <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Edit/Delete</th>
          </tr>
        </thead>
        <tbody>
          {persons.map(person => (
            <tr key={person.id}>
              <td>{person.id}</td>
              <td>{person.name}</td>
              <td>
                <button href="#/" onClick={() => editPerson(person.id, person.name)}>Edit </button>
                <button href="#/" onClick={(e) => { e.preventDefault(); deletePerson(person.id) }}> Delete </button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </React.Fragment>
  );
};
export default PersonList;

PersonList.propTypes = {
  persons: PropTypes.array
}
