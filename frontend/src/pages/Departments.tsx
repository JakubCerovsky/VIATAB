import { useEffect, useState } from "react";

export default function Departments() {
  const [departments, setDepartments] = useState<Department[] | undefined>();

  useEffect(() => {
    const apiEndpoint = `http://localhost:8080/viatab/departments/v1`;

    fetch(apiEndpoint)
      .then((response) => response.json())
      .then((data) => {
        setDepartments(data);
      })
      .catch((error) => {
        console.error("There was a problem fetching the data:", error);
      });
  }, []);

  return (
    <div className="pokemons-container">
      <div className="pokemons-container_pokecards">
        {departments ? (
          departments.map((department, index) => (
            <p key={index}>{department.name}</p>
          ))
        ) : (
          <p>Loading departments...</p>
        )}
      </div>
    </div>
  );
}
