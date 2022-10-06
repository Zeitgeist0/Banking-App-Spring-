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
                <label htmlFor="name">Example of an account number:</label>
                <p>e4d55552-fea4-4544-b40d-0106a47383ce</p>
                <Field
                  className="form-field"
                  name="accountNumber"
                  type="accountNumber"
                  placeholder="Enter the account number that will be deleted"
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
