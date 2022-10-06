import React, { useState } from "react";
import { Formik, Form, Field, ErrorMessage } from "formik";
import * as Yup from "yup";

import withdrawFunds from "API/accounts/withdrawFunds";

const FormSchema = Yup.object({
  number: Yup.string().required("The account number is required"),
  funds: Yup.number()
    .positive()
    .required("Please enter the desired funds amount"),
});

const DeleteAccountForm = () => {
  const handleFormSubmit = (values, { resetForm }) => {
    const { number, funds } = values;

    withdrawFunds(number, funds);
    resetForm();
  };
  return (
    <>
      <h2 className="checkout-header">Withdraw funds from an account</h2>
      <Formik
        initialValues={{
          number: "",
          funds: "",
        }}
        validationSchema={FormSchema}
        onSubmit={handleFormSubmit}
      >
        {({ isSubmitting }) => (
          <Form className="checkout-form">
            <div className="form-container">
              <div className="form-list-item">
                <label htmlFor="name">Account number</label>
                <Field
                  className="form-field"
                  name="number"
                  type="accountNumber"
                  placeholder="Account number"
                />
                <ErrorMessage
                  component="div"
                  className="error-message"
                  name="number"
                />
              </div>
              <div className="form-list-item">
                <label htmlFor="name">Funds that will be withdrawn</label>
                <Field
                  className="form-field"
                  name="funds"
                  type="funds"
                  placeholder="Funds"
                />
                <ErrorMessage
                  component="div"
                  className="error-message"
                  name="funds"
                />
              </div>
              <button className="form-button" type="submit">
                Withdraw funds
              </button>
            </div>
          </Form>
        )}
      </Formik>
    </>
  );
};

export default DeleteAccountForm;
