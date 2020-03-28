import React, { useState, useEffect } from 'react';
import './App.css';

function App() {
  return (
    <div className="App">
      <ChuckNorris/>
    </div>
  );
}

function ChuckNorris(){
    const[jokeData, setJokeData] = useState([]);
    const[fetchData, setFetchData] = useState([]);
    const handleClick = () => {
        fetch("https://api.chucknorris.io/jokes/random")
        .then(function (response) {
            return response.json();
        })
                .then(function (data) {
                    setJokeData(data.value)
                });
    }

    useEffect(() => {
        const interval = setInterval(() => {
        fetch("https://icanhazdadjoke.com", {
            headers: new Headers({
              accept: "application/json"
            })
          })
        .then(function (response) {
            return response.json();
        })
                .then(function (data) {
                    setFetchData(data.joke)
                });
        }, 15000);
        return () => clearInterval(interval);
    }, []);

    return(
        <React.Fragment>
            <button onClick={handleClick}>Get ChuckNorris Joke</button>
         <h2>Chuck Joke: {jokeData}</h2>
    <h2>Fetched joke: {fetchData}</h2>
        </React.Fragment>
    );
}

export default App;
