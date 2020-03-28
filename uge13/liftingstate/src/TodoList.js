import React from 'react';
import PropTypes from 'prop-types';

const TodoList = (props) => {
  const {todos, editTodo, deleteTodo} = props
  return (
    <React.Fragment>
      <h2>All Todos</h2>
      <ul>
        {todos.map(todo => (
          <li key={todo.id}>{todo.todoText}
          <a href="#/" onClick= {(e)=>{e.preventDefault();deleteTodo(todo.id)}}> (delete </a> 
          <a href="#/" onClick= {()=>editTodo(todo.id, todo.todoText)}>, edit) </a>
        </li>
        ))}
      </ul>
    </React.Fragment>
  );
};
export default TodoList;

TodoList.propTypes = {
  todos: PropTypes.array
}
