import React from 'react';
import App2 from './App2';
import App3 from './App3';

import './style1.css';

import {
  BrowserRouter as Router,
  Switch,
  Route,
  NavLink
} from "react-router-dom";

// This site has 3 pages, all of which are rendered
// dynamically in the browser (not server rendered).
//
// Although the page does not ever refresh, notice how
// React Router keeps the URL up to date as you navigate
// through the site. This preserves the browser history,
// making sure things like the back button and bookmarks
// work properly.

export default function BasicExample() {
  return (
    <Router>
      <div>
        <Header />
        <hr />

        {/*
          A <Switch> looks through all its children <Route>
          elements and renders the first one whose path
          matches the current URL. Use a <Switch> any time
          you have multiple routes, but you want only one
          of them to render at a time
        */}
        <div className="content">
          <Switch>
            <Route exact path="/">
              <Home />
            </Route>
            <Route path="/Excersise2">
              <Excersise2 />
            </Route>
            <Route path="/Excersise3">
              <Excersise3 />
            </Route>
          </Switch>
        </div>
      </div>
    </Router>
  );
}

// You can think of these components as "pages"
// in your app.

function Header() {
  return (
    <ul className="header">
      <li>
        <NavLink exact to="/">Home</NavLink>
      </li>
      <li>
        <NavLink exact to="/Excersise2">Excersise2</NavLink>
      </li>
      <li>
        <NavLink exact to="/Excersise3">Excersise3</NavLink>
      </li>
    </ul>
  );
}

function Home() {
  return (
    <div>
      <h2>Home</h2>
      <p>Homepage click on a project to left</p>
    </div>
  );
}

function Excersise2() {
  return (
    <div>
      <h2>Excersise2</h2>
      <App2/>
    </div>
  );
}

function Excersise3() {
  return (
    <div>
      <h2>Excersise3</h2>
      <App3/>
    </div>
  );
}
