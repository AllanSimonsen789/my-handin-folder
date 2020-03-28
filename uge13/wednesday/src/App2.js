import React, { useState, useEffect } from 'react';
import './App.css';

function App() {
  return (
    <div className="App">
      <Clock />
    </div>
  );
}
function Clock() {
    const [time, setTime] = useState(new Date().toLocaleTimeString());
  
    useEffect(() => {
        const interval = setInterval(() => {
            setTime(new Date().toLocaleTimeString());
        }, 1000);
        return () => clearInterval(interval);
    }, []);
  
    return <h1>Time is: {time}</h1>;
  }
export default App;
