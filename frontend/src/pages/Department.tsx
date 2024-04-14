import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";

export default function Department() {
  const [department, setDepartment] = useState<Department | undefined>();

  const { departmentId } = useParams();

  useEffect(() => {
    if (departmentId) {
      const apiEndpoint = `http://localhost:8080/viatab/departments/v1/${parseInt(
        departmentId
      )}`;

      fetch(apiEndpoint)
        .then((response) => response.json())
        .then((data) => {
          setDepartment(data);
        })
        .catch((error) => {
          console.error("There was a problem fetching the data:", error);
        });
    }
  }, [departmentId]);
  return (
    <div>
      {department ? <h1>{department.name}</h1> : <p>Loading department...</p>}
    </div>
  );
}
