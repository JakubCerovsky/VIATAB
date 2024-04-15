import React, { useState, useEffect } from "react";
import { FaCirclePlus } from "react-icons/fa6";

type CreateStoryPrompt = {
  departmentId: number;
  title: string;
  description: string;
};

export default function AddStoryForm() {
  const [story, setStory] = useState<CreateStoryPrompt>({
    title: "",
    description: "",
    departmentId: 0,
  });
  const [departments, setDepartments] = useState<Department[] | undefined>();
  const [statusMsg, setStatusMsg] = useState<string>("");

  function handleInputChange(
    event: React.ChangeEvent<HTMLInputElement | HTMLSelectElement>
  ) {
    const { name, value } = event.target;
    console.log(name + " " + value);
    setStory({ ...story, [name]: value });
    console.log(story);
  }

  async function handleSubmit(event: React.FormEvent<HTMLFormElement>) {
    event.preventDefault();
    const apiEndpoint = `http://localhost:8080/viatab/stories/v1`;

    console.log(story);

    try {
      const response = await fetch(apiEndpoint, {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(story),
      });

      if (!response.ok) {
        throw new Error("Failed to add story.");
      }
      setStatusMsg("Story succesfully added.");
    } catch (error) {
      console.error("Error adding story:", error);
    }
  }

  useEffect(() => {
    const apiEndpoint = `http://localhost:8080/viatab/departments/v1`;
    console.log("re-render");
    fetch(apiEndpoint)
      .then((response) => response.json())
      .then((data) => {
        setDepartments(data);
      })
      .catch((error) => {
        setStatusMsg(error);
        console.error("There was a problem fetching the data:", error);
      });
  }, []);

  return (
    <div className="outlet-container">
      <form onSubmit={handleSubmit} className="new_story-container">
        <h1>Add story!</h1>
        <input
          type="text"
          id="title"
          value={story.title}
          name="title"
          placeholder="Title"
          onChange={handleInputChange}
        />
        <input
          type="text"
          id="description"
          name="description"
          value={story.description}
          placeholder="Description"
          onChange={handleInputChange}
        />
        {departments ? (
          <select
            id="department"
            name="departmentId"
            onChange={handleInputChange}
          >
            <option value="" disabled selected>
              Select department
            </option>
            {departments.map((department, index) => (
              <option key={index} value={department.id}>
                {department.name}
              </option>
            ))}
          </select>
        ) : (
          <p>Loading departments...</p>
        )}
        <button
          type="submit"
          id="add-story-btn"
          disabled={
            story.title === "" ||
            story.description === "" ||
            story.departmentId === 0
          }
        >
          <FaCirclePlus />
        </button>
        <span>{statusMsg}</span>
      </form>
    </div>
  );
}
