import React  from "react";
import {BrowserRouter as Router, Route, Routes} from "react-router-dom";
import "bootstrap/dist/css/bootstrap.min.css";

import Nav from './components/nav.component';
import OrderList from './components/order-list.component';
import AddOrder from './components/add-order.component';
import ViewOrder from './components/view-order.component';


function App() {
  return (
    <Router>
      <div className="container">
        <Nav/> 
        <br/><br/><br/> 

        <Routes>
            <Route path="/" element={<OrderList />} />
            <Route path="/add-order" element={<AddOrder />} />
        </Routes> 
      </div>
    </Router>
     
  );
}

export default App;
