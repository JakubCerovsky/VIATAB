import React from "react";
import ReactDOM from "react-dom/client";
import { RouterProvider, createHashRouter } from "react-router-dom";
import "./index.css";
import App from "./App";
import { AddStory, Stories, Story, Department, Departments } from "./pages";

const router = createHashRouter([
  {
    path: "/",
    element: <App />,
    children: [
      {
        path: "/",
        element: <Stories />,
      },
      {
        path: "/AddStory",
        element: <AddStory />,
      },
      {
        path: "/:storyId",
        element: <Story />,
      },
    ],
  },
  {
    path: "/Deparments",
    element: <Departments />,
    children: [
      {
        path: "Departments/:departmentId",
        element: <Department />,
      },
    ],
  },
]);

const root = ReactDOM.createRoot(
  document.getElementById("root") as HTMLElement
);
root.render(
  <React.StrictMode>
    <RouterProvider router={router} />
  </React.StrictMode>
);
