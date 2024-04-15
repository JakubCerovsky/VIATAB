import { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import { FaRegTrashCan } from "react-icons/fa6";

export default function Story() {
  const [story, setStory] = useState<Story | undefined>();

  const { storyId } = useParams();
  const navigate = useNavigate();

  async function handleDelete() {
    console.log(storyId);
    if (storyId) {
      const apiEndpoint = `http://localhost:8080/viatab/stories/v1/${parseInt(
        storyId
      )}`;
      console.log(apiEndpoint);

      try {
        const response = await fetch(apiEndpoint, {
          method: "DELETE",
        });
        console.log(response);

        if (!response.ok) {
          throw new Error("Failed to delete story.");
        }
        navigate("/");
      } catch (error) {
        console.error("Error deleting story:", error);
      }
    }
  }

  useEffect(() => {
    if (storyId) {
      const apiEndpoint = `http://localhost:8080/viatab/stories/v1/${parseInt(
        storyId
      )}`;

      fetch(apiEndpoint)
        .then((response) => response.json())
        .then((data) => {
          setStory(data);
        })
        .catch((error) => {
          console.error("There was a problem fetching the data:", error);
        });
    }
  }, [storyId]);

  if (!story) return <p>Loading story...</p>;

  return (
    <div className="outlet-container">
      <div className="story-container">
        <div>
          <h1>{story.title}</h1>{" "}
          <button type="submit" id="delete-story-btn" onClick={handleDelete}>
            <FaRegTrashCan />
          </button>
        </div>
        <h3>{story.department.name}</h3>
        <p>{story.description}</p>
      </div>
    </div>
  );
}
