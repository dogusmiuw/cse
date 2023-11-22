class Student {
    private int studentID;
    private String name;
    private double GPA;

    public Student(int studentID, String name, double GPA) {
        this.studentID = studentID;
        this.name = name;
        this.GPA = GPA;
    }

    public int getStudentID() {
        return studentID;
    }

    public String getName() {
        return name;
    }

    public double getGPA() {
        return GPA;
    }

    public void setGPA(double GPA) {
        this.GPA = GPA;
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentID=" + studentID +
                ", name='" + name + '\'' +
                ", GPA=" + GPA +
                '}';
    }
}

class Node {
    private Student student;
    private Node prev;
    private Node next;

    public Node(Student student, Node prev, Node next) {
        this.student = student;
        this.prev = prev;
        this.next = next;
    }

    public Student getStudent() {
        return student;
    }

    public Node getPrev() {
        return prev;
    }

    public Node getNext() {
        return next;
    }

    public void setPrev(Node prev) {
        this.prev = prev;
    }

    public void setNext(Node next) {
        this.next = next;
    }
}

class StudentDatabase {
    private Node head;
    private Node tail;
    private int size;

    public StudentDatabase() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    public void addStudent(int studentID, String name, double GPA, int position) {
        if (position < 0 || position > size) {
            throw new IndexOutOfBoundsException("Invalid position for insertion");
        }

        Student newStudent = new Student(studentID, name, GPA);
        Node newNode = new Node(newStudent, null, null);

        if (size == 0) {
            head = tail = newNode;
        } else if (position == 0) {
            newNode.setNext(head);
            head.setPrev(newNode);
            head = newNode;
        } else if (position == size) {
            tail.setNext(newNode);
            newNode.setPrev(tail);
            tail = newNode;
        } else {
            Node current = head;
            for (int i = 0; i < position - 1; i++) {
                current = current.getNext();
            }
            newNode.setPrev(current);
            newNode.setNext(current.getNext());
            current.getNext().setPrev(newNode);
            current.setNext(newNode);
        }

        size++;
    }

    public void removeStudent(int position) {
        if (position < 0 || position >= size) {
            throw new IndexOutOfBoundsException("Invalid position for deletion");
        }

        if (size == 1) {
            head = tail = null;
        } else if (position == 0) {
            head.getNext().setPrev(null);
            head = head.getNext();
        } else if (position == size - 1) {
            tail.getPrev().setNext(null);
            tail = tail.getPrev();
        } else {
            Node current = head;
            for (int i = 0; i < position; i++) {
                current = current.getNext();
            }
            current.getPrev().setNext(current.getNext());
            current.getNext().setPrev(current.getPrev());
        }

        size--;
    }

    public void findStudent(int studentID) {
        Node current = head;
        while (current != null) {
            if (current.getStudent().getStudentID() == studentID) {
                System.out.println("Student found: " + current.getStudent());
                return;
            }
            current = current.getNext();
        }
        System.out.println("Student with ID " + studentID + " not found.");
    }

    public void updateGPA(int studentID, double newGPA) {
        Node current = head;
        while (current != null) {
            if (current.getStudent().getStudentID() == studentID) {
                current.getStudent().setGPA(newGPA);
                System.out.println("GPA updated for student with ID " + studentID);
                return;
            }
            current = current.getNext();
        }
        System.out.println("Student with ID " + studentID + " not found.");
    }

    public void displayDatabase() {
        System.out.println("Student Database:");
        Node current = head;
        while (current != null) {
            System.out.println(current.getStudent());
            current = current.getNext();
        }
    }

    public static void main(String[] args) {
        StudentDatabase studentDatabase = new StudentDatabase();
        studentDatabase.addStudent(1, "Alice", 3.5, 0);
        studentDatabase.addStudent(2, "Bob", 3.2, 1);
        studentDatabase.addStudent(3, "Charlie", 3.8, 1);

        System.out.println("Original Student Database:");
        studentDatabase.displayDatabase();

        studentDatabase.removeStudent(1);

        System.out.println("Student Database after Removing Student at Position 1:");
        studentDatabase.displayDatabase();

        studentDatabase.findStudent(2);
        studentDatabase.updateGPA(2, 3.4);

        System.out.println("Student Database after Updating GPA for Student with ID 2:");
        studentDatabase.displayDatabase();
    }
}
