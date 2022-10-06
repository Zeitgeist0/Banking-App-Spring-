import React from "react";
import { Link } from "react-router-dom";
import "./header.scss";
const Header = () => {
  return (
    <>
      <header>
        <h1 className="header">BankingApp</h1>
        <nav>
          <ul className="header-list">
            <li>
              <Link className="header-item" to="/customers">
                Customers
              </Link>
            </li>
            <li>
              <Link className="header-item" to="/employers">
                Employers
              </Link>
            </li>
            <li>
              <Link className="header-item" to="/management">
                Management
              </Link>
            </li>
            <li>
              <Link className="header-item" to="/transfers">
                Cash transfers
              </Link>
            </li>
          </ul>
        </nav>
      </header>
    </>
  );
};

export default Header;
