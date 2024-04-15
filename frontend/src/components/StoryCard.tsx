import { Link } from "react-router-dom";

export default function StoryCard({
  id,
  title,
  description,
  department,
}: Story) {
  return (
    <Link to={`stories/${id}`} className="story_card-container">
      <h1>{title}</h1>
      <p>{department.name}</p>
    </Link>
  );
}
