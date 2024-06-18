interface Department {
  id: number;
  name: string;
}

interface Story {
  id: number;
  department: Department;
  title: string;
  description: string;
}
