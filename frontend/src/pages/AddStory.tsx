import React, { useState } from "react";

export default function AddStoryForm() {
  // const [story, setStory] = useState<Story>();

  // const handleSubmit = async (event: React.FormEvent<HTMLFormElement>) => {
  //   event.preventDefault();

  //   try {
  //     const response = await fetch("http://localhost:8080/stories/v1", {
  //       method: "POST",
  //       headers: {
  //         "Content-Type": "application/json",
  //       },
  //       body: JSON.stringify(newStory),
  //     });

  //     if (!response.ok) {
  //       throw new Error("Failed to add story");
  //     }
  //   } catch (error) {
  //     console.error("Error adding story:", error);
  //   }
  // };

  return null;
  // <form onSubmit={handleSubmit}>
  //   <div>
  //     <label htmlFor="title">Title:</label>
  //     <input
  //       type="text"
  //       id="title"
  //       value={story?.title}
  //       onChange={(e) => setStory(story?.title= e.target.value)}
  //     />
  //   </div>
  //   <div>
  //     <label htmlFor="department">Department:</label>
  //     <input
  //       type="text"
  //       id="department"
  //       value={departmentName}
  //       onChange={(e) => setDepartmentName(e.target.value)}
  //     />
  //   </div>
  //   <button type="submit">Add Story</button>
  // </form>
}
