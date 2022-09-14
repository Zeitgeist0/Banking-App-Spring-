import Customer from "Components/Customer/Customer";
import React from "react";
import "./customerList.scss";
const CustomerList = ({ customers }) => {
  return (
    <div className="customers-container">
      <ul className="customers-list">
        {customers.map((customer) => {
          return (
            <li className="customer" key={customer.id}>
              <Customer customer={customer} />
            </li>
          );
        })}
      </ul>
    </div>
  );
};

export default CustomerList;
