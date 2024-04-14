import { useEffect, useState } from "react";
import { StoryCard } from "../components";

export default function Stories() {
  const [stories, setStories] = useState<Story[] | undefined>();

  useEffect(() => {
    const apiEndpoint = `http://localhost:8080/viatab/stories/v1`;

    fetch(apiEndpoint)
      .then((response) => response.json())
      .then((data) => {
        setStories(data);
      })
      .catch((error) => {
        console.error("There was a problem fetching the data:", error);
      });
  }, []);

  return (
    <div className="pokemons-container">
      <div className="pokemons-container_pokecards">
        {stories ? (
          stories.map((story, index) => (
            <StoryCard
              key={index}
              id={story.id}
              title={story.title}
              department={story.department}
              description={story.description}
            />
          ))
        ) : (
          <p>Loading stories...</p>
        )}
      </div>
    </div>
  );
}
