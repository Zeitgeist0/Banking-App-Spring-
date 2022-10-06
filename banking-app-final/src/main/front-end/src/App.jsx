import "./App.css";
import { Route, Routes } from "react-router-dom";
import { ToastContainer, toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import React, { useEffect, useState } from "react";

import Header from "Components/Header/Header";

import { Management } from "Pages/Management";

import Customers from "./Pages/Customers";
import SockJS from "sockjs-client";
import Stomp from "stompjs";
import { CashTransfers } from "Pages/CashTransfers";
import Employers from "Pages/Employers";

function App() {
  let websocket = null;
  const [params, setparams] = useState();
  useEffect(() => {
    const socket = new SockJS("http://localhost:9000/ws");
    websocket = Stomp.over(socket);
    websocket.connect({}, function (frame) {
      console.log("Connected: " + frame);
      websocket.subscribe("/queue/user", function (message) {
        toast(message.body);
      });
    });
  }, []);

  return (
    <>
      <Header />
      <ToastContainer
        position="top-left"
        autoClose={5000}
        hideProgressBar={false}
        newestOnTop
        closeOnClick
        rtl={false}
        pauseOnFocusLoss
        draggable
        pauseOnHover
      />
      <main>
        <p>{params}</p>
        <Routes>
          <Route path="/customers/*" element={<Customers />} />
          <Route path="/employers/*" element={<Employers />} />
          <Route path="/management/*" element={<Management />} />
          <Route path="/transfers/*" element={<CashTransfers />} />
        </Routes>
      </main>
    </>
  );
}

export default App;
