import fetchAllCustomers from "API/customers/fetchAllCustomers";
import getEmployers from "API/employers/getEmployers";
import CustomerList from "Components/CustomerList/CustomerList";
import EmployersList from "Components/EmployersList/EmployersList";
import React, { useEffect, useState } from "react";

const Employers = () => {
  const [employers, setEmployers] = useState([]);

  useEffect(() => {
    getEmployers().then((employers) => {
      setEmployers(employers);
    });
  }, []);

  return (
    <div className="items-container">
      <EmployersList employers={employers} />
    </div>
  );
};

export default Employers;
