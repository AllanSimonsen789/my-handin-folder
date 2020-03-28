import React, { useState, useEffect } from 'react';
import './App.css';

function Counter(props) {
  const [count, setCount] = useState(parseInt(localStorage.getItem("count"),10));

  useEffect(()=>{

    localStorage.setItem("count", count);
  
  });

  return (
    <div>
      <p>You clicked {count} times</p>
      <button onClick={() => setCount(count + props.stepValue)}>
      Increase</button>
      <button onClick ={() => setCount(count - props.stepValue)}>
      decrease</button>
    </div>
  );
}

function App() {
  return (
    <div className="App">
      <Counter stepValue = {5} />
    </div>
  );
}

export default App;
