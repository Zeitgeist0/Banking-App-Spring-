import "./App.css";
import { Route, Routes } from "react-router-dom";
import { useEffect, useState } from "react";
import React from "react";
import fetchAllCustomers from "API/customers/fetchAllCustomers";
import Header from "Components/Header/Header";
import CustomerList from "Components/CustomerList/CustomerList";

import { Management } from "Pages/Management";

import Customers from "./Pages/Customers";

function App() {
  return (
    <>
      <Header />

      <main>
        <Routes>
          <Route path="/customers/*" element={<Customers />} />

          <Route path="/management/*" element={<Management />} />
        </Routes>
      </main>
    </>
  );
}

export default App;
