import React from 'react';
import {Navbar} from 'react-bootstrap';

function Nav(props) {
    return (
        <Navbar bg="dark" variant="dark">
            <Navbar.Brand href="#home">Moody Expense Tracker</Navbar.Brand>
            <Navbar.Toggle />
            <Navbar.Collapse className="justify-content-end">
                <Navbar.Text>
                Signed in as: <a href="#login">Moody Jun</a>
                </Navbar.Text>
            </Navbar.Collapse>
        </Navbar>
    );
}

export default Nav;