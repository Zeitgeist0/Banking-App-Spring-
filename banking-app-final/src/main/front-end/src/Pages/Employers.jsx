import fetchAllCustomers from "API/customers/fetchAllCustomers";
import getEmployers from "API/employers/getEmployers";
import CustomerList from "Components/CustomerList/CustomerList";
import EmployersList from "Components/EmployersList/EmployersList";
import React, { useEffect, useState } from "react";
import { ThreeCircles } from "react-loader-spinner";

const Employers = () => {
  const [employers, setEmployers] = useState([]);
  const [isLoading, setisLoading] = useState(false);
  useEffect(() => {
    setisLoading(true);
    getEmployers().then((employers) => {
      setEmployers(employers);
      setisLoading(false);
    });
  }, []);

  return (
    <div className="items-container">
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
      <EmployersList employers={employers} />
    </div>
  );
};

export default Employers;
