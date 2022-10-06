import CustomerTable from "Components/CustomerTable/CustomerTable";
import React, { useState } from "react";

const Employer = ({ employer }) => {
  return (
    <div>
      <p>
        Employer ID : {employer.id}. {employer.name}
      </p>
      <p>Located at the following address: {employer.address} .</p>
      <p>Customers working in this company : </p>
      <CustomerTable customers={employer.customers} />
    </div>
  );
};

export default Employer;
