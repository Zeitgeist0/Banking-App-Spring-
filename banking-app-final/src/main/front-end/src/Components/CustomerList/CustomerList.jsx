import Customer from "Components/Customer/Customer";
import React from "react";
import "./customerList.scss";
const CustomerList = ({ customers }) => {
  return (
    <div className="items-container">
      <ul className="items-list">
        {customers.map((customer) => {
          return (
            <li className="item" key={customer.id}>
              <Customer customer={customer} />
            </li>
          );
        })}
      </ul>
    </div>
  );
};

export default CustomerList;
