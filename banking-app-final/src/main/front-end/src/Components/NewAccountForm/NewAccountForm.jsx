import React, { useState } from "react";
import { Formik, Form, Field, ErrorMessage } from "formik";
import * as Yup from "yup";

import { useDispatch, useSelector } from "react-redux";
import createNewAccount from "API/accounts/createNewAccount";

const FormSchema = Yup.object({
  currency: Yup.string().required("Currency is required"),
  balance: Yup.number()
    .typeError("Please enter a balance")
    .required("Balance is required")
    .positive()
    .integer(),
  customerId: Yup.number()
    .typeError("Please enter a customer ID")
    .required("Customer ID is required")
    .positive()
    .integer(),
});

const NewAccountForm = () => {
  const handleFormSubmit = (values, { resetForm }) => {
    const { currency, balance, customerId } = values;
    createNewAccount(currency, customerId, balance);
    resetForm();
  };
  return (
    <>
      <h2 className="account-header">Create a new account</h2>
      <Formik
        initialValues={{
          currency: "",
          balance: "",
          customerId: "",
        }}
        validationSchema={FormSchema}
        onSubmit={handleFormSubmit}
      >
        {({ isSubmitting }) => (
          <Form className="checkout-form">
            <div className="form-container">
              <div className="form-list-item">
                <ul className="currency-list">
                  <p>Number correspondency for each currency</p>
                  <li>0 = USD</li>
                  <li>1 = EUR</li>
                  <li>2 = UAH</li>
                  <li>3 = CHF</li>
                  <li>4 = GBP</li>
                </ul>
                <label htmlFor="name">Enter a currency number</label>
                <Field
                  className="form-field"
                  name="currency"
                  type="currency"
                  placeholder="Currency"
                />
                <ErrorMessage
                  component="div"
                  className="error-message"
                  name="currency"
                />
              </div>
              <div className="form-list-item">
                <label htmlFor="name">Balance</label>
                <Field
                  name="balance"
                  type="balance"
                  placeholder="Balance"
                  className="form-field"
                />
                <ErrorMessage
                  className="error-message"
                  component="div"
                  name="balance"
                />
              </div>
              <div className="form-list-item">
                <label htmlFor="name">Customer ID</label>
                <Field
                  name="customerId"
                  type="customerId"
                  placeholder="Customer ID"
                  className="form-field"
                />
                <ErrorMessage
                  className="error-message"
                  component="div"
                  name="customerId"
                />
              </div>

              <button className="form-button" type="submit">
                Create
              </button>
            </div>
          </Form>
        )}
      </Formik>
    </>
  );
};

export default NewAccountForm;
