import React, { useState } from "react";
import { Formik, Form, Field, ErrorMessage } from "formik";
import * as Yup from "yup";
import "./newCustomerForm.scss";
import { useDispatch, useSelector } from "react-redux";
import createNewCustomer from "API/customers/createNewCustomer";

const FormSchema = Yup.object({
  name: Yup.string().required("Name is required"),
  password: Yup.string().required("Password is required"),
  email: Yup.string().required("Email is required"),
  age: Yup.number()
    .typeError("Please enter a number")
    .required("Age is required")
    .positive()
    .min(18)
    .integer(),
  phoneNumber: Yup.string().required("Phone number is required"),
});

const NewCustomerForm = () => {
  const handleFormSubmit = (values, { resetForm }) => {
    const { name, password, email, age, phoneNumber } = values;

    createNewCustomer(name, password, email, age, phoneNumber);
    resetForm();
  };

  return (
    <>
      <h2 className="checkout-header">Create a new customer</h2>
      <Formik
        initialValues={{
          name: "",
          password: "",
          email: "",
          age: "",
          phoneNumber: "",
        }}
        validationSchema={FormSchema}
        onSubmit={handleFormSubmit}
      >
        {({ isSubmitting }) => (
          <Form className="checkout-form">
            <div className="form-container">
              <div className="form-list-item">
                <label htmlFor="name">Name</label>
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
                <label htmlFor="name">Password</label>
                <Field
                  className="form-field"
                  name="password"
                  type="password"
                  placeholder="Password"
                />
                <ErrorMessage
                  component="div"
                  className="error-message"
                  name="password"
                />
              </div>
              <div className="form-list-item">
                <label htmlFor="name">Email</label>
                <Field
                  name="email"
                  type="email"
                  placeholder="Email"
                  className="form-field"
                />
                <ErrorMessage
                  className="error-message"
                  component="div"
                  name="email"
                />
              </div>
              <div className="form-list-item">
                <label htmlFor="name">Age</label>
                <Field
                  name="age"
                  type="age"
                  placeholder="Age"
                  className="form-field"
                />
                <ErrorMessage
                  className="error-message"
                  component="div"
                  name="age"
                />
              </div>
              <div className="form-list-item">
                <label htmlFor="name">Phone number</label>
                <Field
                  className="form-field"
                  name="phoneNumber"
                  type="phoneNumber"
                  placeholder="Phone number"
                />
                <ErrorMessage
                  component="div"
                  className="error-message"
                  name="phoneNumber"
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

export default NewCustomerForm;
