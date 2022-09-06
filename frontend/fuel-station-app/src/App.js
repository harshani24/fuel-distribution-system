import React  from "react";
import {BrowserRouter as Router, Route} from "react-router-dom";
import "bootstrap/dist/css/bootstrap.min.css";

import Nav from './components/nav.component.js';


function App() {
  return (
    <Router>
      <div className="container">
        <Nav/>
        <br/>
      </div>
    </Router>
     
  );
}

export default App;
