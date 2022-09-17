import React from "react";
import NewCustomerForm from "Components/NewCustomerForm/NewCustomerForm";
import "./management.scss";
import NewAccountForm from "Components/NewAccountForm/NewAccountForm";
import DeleteAccountForm from "Components/DeleteAccountForm/DeleteAccountForm";
export const Management = () => {
  return (
    <div className="management-container">
      <NewCustomerForm />
      <NewAccountForm />
      <DeleteAccountForm />
    </div>
  );
};
