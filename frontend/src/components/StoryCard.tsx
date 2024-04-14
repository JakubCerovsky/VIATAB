import { Link } from "react-router-dom";

export default function StoryCard({
  id,
  title,
  description,
  department,
}: Story) {
  return <Link to={`/${id}`}>{title}</Link>;
}
