import React, { useState } from "react";
import { Formik, Form, Field, ErrorMessage } from "formik";
import * as Yup from "yup";

import { useDispatch, useSelector } from "react-redux";
import createNewCustomer from "API/customers/createNewCustomer";
import saveEmployer from "API/employers/saveEmployer";

const FormSchema = Yup.object({
  name: Yup.string().required("Name is required"),
  address: Yup.string().required("Password is required"),
  customerIdsString: Yup.string(),
});

const NewCustomerForm = () => {
  const handleFormSubmit = (values, { resetForm }) => {
    const { name, address, customerIdsString } = values;
    const customerIds = customerIdsString.split(",");
    saveEmployer(name, address, customerIds);
    resetForm();
  };

  return (
    <>
      <h2 className="checkout-header">Create a new employer</h2>
      <Formik
        initialValues={{
          name: "",
          address: "",
          customerIdsString: "",
        }}
        validationSchema={FormSchema}
        onSubmit={handleFormSubmit}
      >
        {({ isSubmitting }) => (
          <Form className="checkout-form">
            <div className="form-container">
              <div className="form-list-item">
                <label htmlFor="name">Employer name</label>
                <Field
                  className="form-field"
                  name="name"
                  type="name"
                  placeholder="Name"
                />
                <ErrorMessage
                  component="div"
                  className="error-message"
                  name="name"
                />
              </div>
              <div className="form-list-item">
                <label htmlFor="name">Employer address</label>
                <Field
                  className="form-field"
                  name="address"
                  type="address"
                  placeholder="Address"
                />
                <ErrorMessage
                  component="div"
                  className="error-message"
                  name="password"
                />
              </div>
              <div className="form-list-item">
                <label htmlFor="name">
                  Customers that work for the employer:
                </label>
                <p>Please, enter the customer ID's separated by commas</p>

                <Field
                  name="customerIdsString"
                  type="customerIdsString"
                  placeholder="For example like this: 1,4,6,8"
                  className="form-field"
                />
                <ErrorMessage
                  className="error-message"
                  component="div"
                  name="customerIdsString"
                />
              </div>

              <button className="form-button" type="submit">
                Create employer
              </button>
            </div>
          </Form>
        )}
      </Formik>
    </>
  );
};

export default NewCustomerForm;
