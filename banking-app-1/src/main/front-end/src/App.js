import logo from './logo.svg';
import './App.css';
import React, { useEffect, useState } from "react";
import fetchAllCustomersAndAccounts from "API/customers/fetchAllCustomersAndAccounts"
function App() {
  const [customers, setCustomers] = useState([]);
  useEffect(() => {
    
fetchAllCustomersAndAccounts().then((customers) => {
  setCustomers(customers);
})
    
  }, [] )
  
  

  return (
    <div className="App">
  
      <ul className="customers-list">
        {customers.map((customer) => {
          return (
            <li  key={customer.id}>
              <h4>User: </h4>
              <p> {customer.name} , {customer.age} years old. Email : {customer.email}</p>
           

              <h4>Accounts: </h4>
              <table className="customers-table">
        <tr>
          <th>Number</th>
          <th>Currency</th>
          <th>Balance</th>
        
        </tr>
        {customer.accounts.map((val, key) => {
          return (
            <tr key={key}>
              <td>{val.number}</td>
              <td>{val.currency}</td>
              <td>{val.balance}</td>
            </tr>
          )
        })}
      </table>
            </li>
          );
        })}
      </ul>
    </div>
  );
}

export default App;
