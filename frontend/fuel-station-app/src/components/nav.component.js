import React from "react";

const Nav = () => {

    return (
        <div>
            <nav className="navbar navbar-expand-lg navbar-dark bg-dark">
                <Link class="navbar-brand" href="/">Fuel Station App</Link>

                <div className="navbar-collapse">
                    <div className="navbar-nav mr-auto">
                        <Link className="nav-item nav-link active" to="/">All Orders</Link>
                        <Link className="nav-item nav-link" to="/exercise">New Order</Link>
                    </div>
                </div>
            </nav>

        </div>
    );

}

export default Nav;