import React, { useState } from "react";
import { Formik, Form, Field, ErrorMessage } from "formik";
import * as Yup from "yup";
import addFunds from "API/accounts/addFunds";
import transferFunds from "API/accounts/transferFunds";

const FormSchema = Yup.object({
  originAccountNumber: Yup.string().required(
    "The origin account number is required"
  ),
  destinationAccountNumber: Yup.string().required(
    "The destination account number is required"
  ),
  funds: Yup.number()
    .positive()
    .required("Please enter the desired funds amount"),
});

const DeleteAccountForm = () => {
  const handleFormSubmit = (values, { resetForm }) => {
    const { originAccountNumber, destinationAccountNumber, funds } = values;

    transferFunds(originAccountNumber, destinationAccountNumber, funds);
    resetForm();
  };
  return (
    <>
      <h2 className="checkout-header">Transfer funds between accounts</h2>
      <Formik
        initialValues={{
          originAccountNumber: "",
          destinationAccountNumber: "",
          funds: "",
        }}
        validationSchema={FormSchema}
        onSubmit={handleFormSubmit}
      >
        {({ isSubmitting }) => (
          <Form className="checkout-form">
            <div className="form-container">
              <div className="form-list-item">
                <label htmlFor="name">Account that will send the money</label>
                <Field
                  className="form-field"
                  name="originAccountNumber"
                  type="originAccountNumber"
                  placeholder="Please enter the account number here"
                />
                <ErrorMessage
                  component="div"
                  className="error-message"
                  name="originAccountNumber"
                />
              </div>
              <div className="form-list-item">
                <label htmlFor="name">
                  Account that will receive the money
                </label>
                <Field
                  className="form-field"
                  name="destinationAccountNumber"
                  type="destinationAccountNumber"
                  placeholder="Please enter the account number here"
                />
                <ErrorMessage
                  component="div"
                  className="error-message"
                  name="destinationAccountNumber"
                />
              </div>
              <div className="form-list-item">
                <label htmlFor="name">Funds that will be transferred</label>
                <Field
                  className="form-field"
                  name="funds"
                  type="funds"
                  placeholder="Funds to be transferred"
                />
                <ErrorMessage
                  component="div"
                  className="error-message"
                  name="funds"
                />
              </div>
              <button className="form-button" type="submit">
                Transfer funds
              </button>
            </div>
          </Form>
        )}
      </Formik>
    </>
  );
};

export default DeleteAccountForm;
