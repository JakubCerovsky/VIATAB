import { Link } from "react-router-dom";
import { MdNoteAdd } from "react-icons/md";

export default function Navbar() {
  return (
    <nav className="navbar-container">
      <Link to="/" className="navbar-container_link">
        Stories
      </Link>
      <Link to="/AddStory" className="navbar-container_link">
        <MdNoteAdd />
      </Link>
      <Link to="/Departments" className="navbar-container_link">
        Departments
      </Link>
    </nav>
  );
}
