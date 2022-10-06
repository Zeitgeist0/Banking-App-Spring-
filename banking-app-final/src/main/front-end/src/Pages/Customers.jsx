import fetchAllCustomers from "API/customers/fetchAllCustomers";
import CustomerList from "Components/CustomerList/CustomerList";
import React, { useEffect, useState } from "react";
import { ThreeCircles } from "react-loader-spinner";

const Customers = () => {
  const [customers, setCustomers] = useState([]);
  const [isLoading, setisLoading] = useState(false);
  useEffect(() => {
    setisLoading(true);
    fetchAllCustomers().then((customers) => {
      setCustomers(customers);
      setisLoading(false);
    });
  }, []);

  return (
    <div className="customers-container">
      <div className="loading-div">
        <ThreeCircles
          height="300"
          width="300"
          color="#4fa94d"
          wrapperStyle={{}}
          wrapperClass=""
          visible={isLoading}
          ariaLabel="three-circles-rotating"
          outerCircleColor=""
          innerCircleColor=""
          middleCircleColor=""
        />
      </div>
      <CustomerList customers={customers} />
    </div>
  );
};

export default Customers;
