import AddFundsForm from "Components/AddFundsForm/AddFundsForm";
import TransferFundsForm from "Components/TransferFundsForm/TransferFundsForm";
import WithdrawFundsForm from "Components/WithdrawFundsForm/WithdrawFundsForm";
import React from "react";

export const CashTransfers = () => {
  return (
    <div className="transfers-container">
      <TransferFundsForm />
      <AddFundsForm />
      <WithdrawFundsForm />
    </div>
  );
};
