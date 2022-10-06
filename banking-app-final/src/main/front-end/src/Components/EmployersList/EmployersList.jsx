import Employer from "Components/Employer/Employer";
import React from "react";

const EmployersList = ({ employers }) => {
  return (
    <div className="items-container">
      <ul className="items-list">
        {employers.map((employer) => {
          return (
            <li className="item" key={employer.id}>
              <Employer employer={employer} />
            </li>
          );
        })}
      </ul>
    </div>
  );
};

export default EmployersList;
