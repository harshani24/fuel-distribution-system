import React from "react";
import {Link} from "react-router-dom";

const Nav = (prop) => {
 
    return (
        <div>
             <nav className="navbar fixed-top navbar-dark bg-dark navbar-expand-lg">
                <Link className="navbar-brand" to="/">Fuel Station App</Link>

                <div className="navbar-collapse">
                    <div className="navbar-nav mr-auto">
                        <Link className="nav-item nav-link active" to="/">All Orders</Link>
                        <Link className="nav-item nav-link" to="/add-order">New Order</Link>
                        <Link className="nav-item nav-link" to="/view-order">View Order</Link>
                        <Link className="nav-item nav-link" to="/order">Order</Link>
                    </div>
                </div>
            </nav>
        </div>
    );

}

export default Nav;
