import "./App.css";
import { Route, Routes } from "react-router-dom";
import { useEffect, useState } from "react";
import React from "react";
import fetchAllCustomers from "API/customers/fetchAllCustomers";
import Header from "Components/Header/Header";
import CustomerList from "Components/CustomerList/CustomerList";

import { Management } from "Pages/Management";

function App() {
  const [customers, setCustomers] = useState([]);

  useEffect(() => {
    fetchAllCustomers().then((customers) => {
      setCustomers(customers);
    });
  }, []);

  return (
    <>
      <Header />

      <main>
        <Routes>
          <Route
            path="/customers/*"
            element={<CustomerList customers={customers} />}
          />

          <Route path="/management/*" element={<Management />} />
        </Routes>
      </main>
    </>
  );
}

export default App;
