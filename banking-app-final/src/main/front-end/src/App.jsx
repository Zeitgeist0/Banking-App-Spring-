import "./App.css";
import { Route, Routes } from "react-router-dom";

import React from "react";

import Header from "Components/Header/Header";

import { Management } from "Pages/Management";

import Customers from "./Pages/Customers";

function App() {
  return (
    <>
      <Header />

      <main>
        <Routes>
          <Route path="/allcustomers/*" element={<Customers />} />

          <Route path="/management/*" element={<Management />} />
        </Routes>
      </main>
    </>
  );
}

export default App;
