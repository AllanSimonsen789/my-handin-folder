import React, { useState } from 'react';
import './App.css';

import {
  BrowserRouter as Router,
  Switch,
  Route,
  NavLink,
  Prompt,
  useParams,
  useRouteMatch,
  useHistory
} from "react-router-dom";

function App({ bookFacade }) {

  const [isLoggedIn, setIsLoggedIn] = useState(false);
  let history = useHistory();

  const setLoginStatus = status => {
    setIsLoggedIn(status);
    history.push("/");
  };

  return (
    <div>
      <Header
        loginMsg={isLoggedIn ? "Logout" : "Login"}
        isLoggedIn={isLoggedIn}
      />
      <Switch>
        <Route exact path="/">
          <Home />
        </Route>
        <Route path="/products">
          <Products bookFacade={bookFacade} />
        </Route>
        <Route path="/add-book">
          <AddBook bookFacade={bookFacade} />
        </Route>
        <Route path="/find-book">
          <FindBook bookFacade={bookFacade} />
        </Route>
        <Route path="/company">
          <Company />
        </Route>
        <Route path="/login-out">
          <Login 
          loginMsg={isLoggedIn ? "Logout" : "Login"}
          isLoggedIn={isLoggedIn}
          setLoginStatus = {setLoginStatus}
           />
        </Route>
        <Route path="*">
          <NoMatch />
        </Route>
      </Switch>
    </div>

  );
}

function Header({ isLoggedIn, loginMsg }) {
  return (
    <ul className="header">
      <li><NavLink exact activeClassName="active" to="/">Home</NavLink></li>
      <li><NavLink activeClassName="active" to="/products">Products</NavLink></li>
      {isLoggedIn &&
        <React.Fragment>
          <li><NavLink activeClassName="active" to="/add-book">Add Book</NavLink></li>
          <li><NavLink activeClassName="active" to="/find-book">Find Book</NavLink></li>
        </React.Fragment>
      }
      <li><NavLink activeClassName="active" to="/company">Company</NavLink></li>
      <li><NavLink activeClassName="active" to="/login-out">{loginMsg}</NavLink></li>

    </ul>

  );
}

function Login({ isLoggedIn, loginMsg, setLoginStatus }) {
  const handleButtonClick = () => {
    setLoginStatus(!isLoggedIn);
  };
  return (
    <div>
      <h2>{loginMsg}</h2>
      <br />
      <button onClick = {handleButtonClick}>{loginMsg}</button>
    </div>
  );
}

function Home() {
  return (
    <p>Home</p>
  );
}

function FindBook({ bookFacade }) {

  const [bookID, setBookId] = useState("");
  const [book, setBook] = useState(null);
  const [editing, setEditing] = useState(false);
  const [isBlocking, setIsBlocking] = useState(false);


  const findBook = () => {
    setBook(bookFacade.findBook(bookID))
    setBookId("");
  };

  const deleteBook = (id) => {
    bookFacade.deleteBook(id);
    setBookId("");
    setBook(null);
  };

  const handleChange = event => {
    const target = event.target;
    const value = target.value;
    const name = target.name;
    setIsBlocking(value.length > 0);
    setBook({ ...book, [name]: value });
  };

  const editBook = (event) => {
    bookFacade.editBook(book);
    setBookId("");
    setBook(null);
    setEditing(false);
    setIsBlocking(false);
    event.preventDefault();

  }

  return (
    <React.Fragment>
      <h2>Find Book</h2>
      <input value={bookID} placeholder="Search for ID" onChange={e => { setBookId(e.target.value) }} />
      <br />
      <button onClick={findBook}>Find Book</button>
      {book && (
        <div>

          <h2>ID: {book.id}</h2>
          <h2>title: {!editing && book.title}</h2>{editing && <input name="title" value={book.title} onChange={handleChange} placeholder="Add Title" />}
          <h2>info: {!editing && book.info}</h2>{editing && <input name="info" value={book.info} onChange={handleChange} placeholder="Add info" />}
          <div>
            {!editing && <div>
              <button onClick={() => deleteBook(book.id)}>Delete Book</button>
              <button onClick={(e) => { setEditing(true) }}>Edit Book</button>
            </div>
            }
            {editing && <button onClick={(e) => editBook(e)}>Edit Book</button>}
          </div>
        </div>
      )}
      {!book && (
        <h2>Search for at book</h2>
      )}
    </React.Fragment>
  );
}

function Products({ bookFacade }) {

  let { path, url } = useRouteMatch();

  return (
    <React.Fragment>
      <p>Products</p>
      <ul>
        {bookFacade.getBooks().map((book) => {
          return (
            <li key={book.id}>
              {book.title} <NavLink to={`${url}/${book.id}`}>Details</NavLink>
            </li>
          );
        })}
      </ul>


      <Switch>
        <Route exact path={path}>
          <h3>Please select a topic.</h3>
        </Route>
        <Route path={`${path}/:bookId`}>
          <Details bookFacade={bookFacade} />
        </Route>
      </Switch>
    </React.Fragment>
  );
}

function Company() {
  return (
    <p>Company</p>
  );
}


function AddBook({ bookFacade }) {

  const newBook = {
    id: "",
    title: "",
    info: "",
  };

  const [book, setBook] = useState(newBook);
  const [isBlocking, setIsBlocking] = useState(false);


  const handleChange = event => {
    const target = event.target;
    const value = target.value;
    const name = target.name;
    setIsBlocking(value.length > 0);
    setBook({ ...book, [name]: value });
  };

  function handleSubmit(event) {
    bookFacade.addBook(book);
    setBook({ ...newBook })
    setIsBlocking(false);
    event.preventDefault();

  };

  return (
    <div>
      <h1>Add Book</h1>
      <form onSubmit={handleSubmit}>
        <Prompt
          when={isBlocking}
          message={location =>
            `Are you sure you want to go to ${location.pathname}`
          }
        />
        <input
          name="title"
          value={book.title}
          onChange={handleChange}
          placeholder="Add Title"
        />
        <br />
        <input
          name="info"
          value={book.info}
          onChange={handleChange}
          placeholder="Add info"
        />
        <input type="submit" value="Submit" />
        <p>{JSON.stringify(book)}</p>
      </form>
    </div>
  );
}

function NoMatch() {
  return (
    <p>NoMatch</p>
  );
}

function Details({ bookFacade }) {
  let { bookId } = useParams();

  const book = bookFacade.findBook(bookId);
  return (
    <React.Fragment>
      <h2>ID: {book.id}</h2>
      <h2>title: {book.title}</h2>
      <h2>info: {book.info}</h2>
    </React.Fragment>
  );
}

export default App;
