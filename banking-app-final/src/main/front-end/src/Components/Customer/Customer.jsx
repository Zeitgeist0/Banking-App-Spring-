import AccountTable from "Components/AccountTable/AccountTable";
import React, { useState } from "react";

const Customer = ({ customer }) => {
  // const [employers, setemployers] = useState();

  // useEffect(() => {

  //   customer.employers.foreach((employer) =>

  //   )

  // }, [customer])

  return (
    <div>
      <p>
        Customer ID : {customer.id}. {customer.name} , {customer.age} years old.
      </p>

      <p>Email : {customer.email}</p>
      <p>Phone number : {customer.phoneNumber}</p>
      <p>Employers : {customer.employers.join(", ")}</p>
      <AccountTable accounts={customer.accounts} />
    </div>
  );
};

export default Customer;
