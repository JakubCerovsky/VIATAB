import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";

export default function Story() {
  const [story, setStory] = useState<Story | undefined>();

  const { storyId } = useParams();

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

  return <div>{story ? <h1>{story.title}</h1> : <p>Loading story...</p>}</div>;
}
