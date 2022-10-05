import "./App.css";
import { Route, Routes } from "react-router-dom";

import React, { useEffect, useState } from "react";

import Header from "Components/Header/Header";

import { Management } from "Pages/Management";

import Customers from "./Pages/Customers";
import SockJS from "sockjs-client";
import Stomp from "stompjs";

function App() {
  let websocket = null;
  const [params, setparams] = useState();
  useEffect(() => {
    const socket = new SockJS("http://localhost:9000/ws");
    websocket = Stomp.over(socket);
    websocket.connect({}, function (frame) {
      console.log("Connected: " + frame);
      websocket.subscribe("/queue/user", function (greeting) {
        // showGreeting(JSON.parse(greeting.body).content);
        setparams(greeting.body);
      });
      websocket.subscribe("/topic/color", function (greeting) {
        // setNewColor(JSON.parse(greeting.body).content);
      });
    });
  }, []);

  return (
    <>
      <Header />

      <main>
        <p>{params}</p>
        <Routes>
          <Route path="/allcustomers/*" element={<Customers />} />

          <Route path="/management/*" element={<Management />} />
        </Routes>
      </main>
    </>
  );
}

export default App;
