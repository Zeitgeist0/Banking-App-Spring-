import React, { useState } from "react";
import { Formik, Form, Field, ErrorMessage } from "formik";
import * as Yup from "yup";

import deleteAccount from "API/accounts/deleteAccount";

const FormSchema = Yup.object({
  accountNumber: Yup.string().required("The account number is required"),
});

const DeleteAccountForm = () => {
  const handleFormSubmit = (values, { resetForm }) => {
    const { accountNumber } = values;

    deleteAccount(accountNumber);
    resetForm();
  };
  return (
    <>
      <h2 className="checkout-header">Delete an account</h2>
      <Formik
        initialValues={{
          accountNumber: "",
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
                  name="accountNumber"
                  type="accountNumber"
                  placeholder="Account number"
                />
                <ErrorMessage
                  component="div"
                  className="error-message"
                  name="accountNumber"
                />
              </div>

              <button className="form-button" type="submit">
                Delete
              </button>
            </div>
          </Form>
        )}
      </Formik>
    </>
  );
};

export default DeleteAccountForm;
