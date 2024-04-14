import React from "react";
import "./App.css";
import { Navbar } from "./components";
import { Outlet } from "react-router-dom";

declare global {
  type Department = {
    id: number;
    name: string;
  };

  type Story = {
    id: number;
    department: Department;
    title: string;
    description: string;
  };
}

export default function App() {
  return (
    <div className="App">
      <Navbar />
      <Outlet />
    </div>
  );
}
