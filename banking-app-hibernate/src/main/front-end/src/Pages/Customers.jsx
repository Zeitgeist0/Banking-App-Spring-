import fetchAllCustomers from "API/customers/fetchAllCustomers";
import CustomerList from "Components/CustomerList/CustomerList";
import React, { useEffect, useState } from "react";

const Customers = () => {
  const [customers, setCustomers] = useState([]);

  useEffect(() => {
    fetchAllCustomers().then((customers) => {
      setCustomers(customers);
    });
  }, []);

  return (
    <div className="customers-container">
      <CustomerList customers={customers} />
    </div>
  );
};

export default Customers;
