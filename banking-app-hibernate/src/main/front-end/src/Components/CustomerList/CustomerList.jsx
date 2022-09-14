import Customer from "Components/Customer/Customer";
import React from "react";
import "./customerList.scss";
const CustomerList = ({ customers }) => {
  return (
    <ul className="customers-list">
      {customers.map((customer) => {
        return (
          <li className="customer" key={customer.id}>
            <Customer customer={customer} />
          </li>
        );
      })}
    </ul>
  );
};

export default CustomerList;
