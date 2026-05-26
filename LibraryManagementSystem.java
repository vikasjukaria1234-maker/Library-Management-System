import java.util.*;


class BookNotFoundException extends Exception {
    public BookNotFoundException(String msg) { super(msg); }
}

class BookAlreadyIssuedException extends Exception {
    public BookAlreadyIssuedException(String msg) { super(msg); }
}

class BorrowLimitExceededException extends Exception {
    public BorrowLimitExceededException(String msg) { super(msg); }
}

class StudentNotFoundException extends Exception {
    public StudentNotFoundException(String msg) { super(msg); }
}




interface LibraryOperations {
    void issueBook(int studentId, int bookId) throws Exception;
    void returnBook(int studentId, int bookId) throws Exception;
}


abstract class Person {
    private int id;       // Encapsulation
    private String name;

    public Person(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId()       { return id; }
    public String getName()  { return name; }

    public abstract void displayDetails(); // Polymorphism
}




class Book {
    private int bookId;           // Encapsulation
    private String bookName;
    private String authorName;
    private boolean isAvailable;

    public Book(int bookId, String bookName, String authorName) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.authorName = authorName;
        this.isAvailable = true;
    }

    public int getBookId()        { return bookId; }
    public String getBookName()   { return bookName; }
    public String getAuthorName() { return authorName; }
    public boolean isAvailable()  { return isAvailable; }
    public void setAvailable(boolean available) { this.isAvailable = available; }

    public void displayDetails() {
        System.out.println("  [" + bookId + "] " + bookName + " by " + authorName
                + " | " + (isAvailable ? "Available" : "Issued"));
    }
}




class Student extends Person {
    private String department;             // Encapsulation
    private List<Book> borrowedBooks;

    public Student(int id, String name, String department) {
        super(id, name);
        this.department = department;
        this.borrowedBooks = new ArrayList<>();
    }

    public String getDepartment()        { return department; }
    public List<Book> getBorrowedBooks() { return borrowedBooks; }

    public boolean hasReachedLimit() { return borrowedBooks.size() >= 3; }

    public void borrowBook(Book book)    { borrowedBooks.add(book); }

    public boolean returnBook(int bookId) {
        return borrowedBooks.removeIf(b -> b.getBookId() == bookId);
    }

    public boolean hasBorrowed(int bookId) {
        return borrowedBooks.stream().anyMatch(b -> b.getBookId() == bookId);
    }

    @Override
    public void displayDetails() {          // Polymorphism — overrides Person
        System.out.println("-----------------------------");
        System.out.println("Student ID  : " + getId());
        System.out.println("Name        : " + getName());
        System.out.println("Department  : " + department);
        System.out.println("Borrowed    : " + borrowedBooks.size() + "/3");
        if (borrowedBooks.isEmpty()) {
            System.out.println("  (no books borrowed)");
        } else {
            for (Book b : borrowedBooks)
                System.out.println("  - [" + b.getBookId() + "] " + b.getBookName());
        }
        System.out.println("-----------------------------");
    }
}




class Librarian extends Person {
    private String employeeCode;           // Encapsulation

    public Librarian(int id, String name, String employeeCode) {
        super(id, name);
        this.employeeCode = employeeCode;
    }

    @Override
    public void displayDetails() {          // Polymorphism — overrides Person
        System.out.println("-----------------------------");
        System.out.println("Librarian   : " + getName());
        System.out.println("Emp. Code   : " + employeeCode);
        System.out.println("-----------------------------");
    }
}




class Library implements LibraryOperations {
    private List<Book>    books    = new ArrayList<>();
    private List<Student> students = new ArrayList<>();

    public void addBook(Book book) {
        books.add(book);
        System.out.println("Book added: " + book.getBookName());
    }

    public void addStudent(Student student) {
        students.add(student);
        System.out.println("Student registered: " + student.getName());
    }

    public void viewAllBooks() {
        System.out.println("\n===== ALL BOOKS =====");
        if (books.isEmpty()) { System.out.println("No books found."); return; }
        for (Book b : books) b.displayDetails();
    }

    public void viewAllStudents() {
        System.out.println("\n===== ALL STUDENTS =====");
        if (students.isEmpty()) { System.out.println("No students found."); return; }
        for (Student s : students) s.displayDetails();
    }

    public Book findBook(int bookId) throws BookNotFoundException {
        for (Book b : books)
            if (b.getBookId() == bookId) return b;
        throw new BookNotFoundException("Book ID " + bookId + " not found.");
    }

    public Student findStudent(int studentId) throws StudentNotFoundException {
        for (Student s : students)
            if (s.getId() == studentId) return s;
        throw new StudentNotFoundException("Student ID " + studentId + " not found.");
    }

    @Override
    public void issueBook(int studentId, int bookId) throws Exception {
        Student student = findStudent(studentId);
        Book book = findBook(bookId);

        if (!book.isAvailable())
            throw new BookAlreadyIssuedException("\"" + book.getBookName() + "\" is already issued.");
        if (student.hasReachedLimit())
            throw new BorrowLimitExceededException(student.getName() + " has reached the 3-book limit.");

        book.setAvailable(false);
        student.borrowBook(book);
        System.out.println("Issued \"" + book.getBookName() + "\" to " + student.getName());
    }

    @Override
    public void returnBook(int studentId, int bookId) throws Exception {
        Student student = findStudent(studentId);
        Book book = findBook(bookId);

        if (!student.hasBorrowed(bookId))
            throw new Exception(student.getName() + " did not borrow \"" + book.getBookName() + "\".");

        student.returnBook(bookId);
        book.setAvailable(true);
        System.out.println("Returned \"" + book.getBookName() + "\" from " + student.getName());
    }
}





public class LibraryManagementSystem {

    static Scanner sc = new Scanner(System.in);
    static Library library = new Library();

    public static void main(String[] args) {
        // Preload sample data
        library.addBook(new Book(101, "Java: The Complete Reference", "Herbert Schildt"));
        library.addBook(new Book(102, "Clean Code", "Robert C. Martin"));
        library.addBook(new Book(103, "Introduction to Algorithms", "Thomas Cormen"));
        library.addStudent(new Student(1, "Arjun Sharma", "Computer Science"));
        library.addStudent(new Student(2, "Priya Singh", "Information Technology"));

        System.out.println("\nWelcome to the Library Management System");

        int choice;
        do {
            System.out.println("\n===== MENU =====");
            System.out.println("1. Add Book");
            System.out.println("2. View All Books");
            System.out.println("3. Add Student");
            System.out.println("4. Issue Book");
            System.out.println("5. Return Book");
            System.out.println("6. View Student Details");
            System.out.println("0. Exit");
            System.out.print("Choice: ");

            choice = readInt();

            switch (choice) {
                case 1 -> addBook();
                case 2 -> library.viewAllBooks();
                case 3 -> addStudent();
                case 4 -> issueBook();
                case 5 -> returnBook();
                case 6 -> viewStudent();
                case 0 -> System.out.println("Goodbye!");
                default -> System.out.println("Invalid choice.");
            }
        } while (choice != 0);
    }

    static void addBook() {
        System.out.print("Book ID     : "); int id = readInt();
        System.out.print("Book Name   : "); String name = sc.nextLine();
        System.out.print("Author      : "); String author = sc.nextLine();
        library.addBook(new Book(id, name, author));
    }

    static void addStudent() {
        System.out.print("Student ID  : "); int id = readInt();
        System.out.print("Name        : "); String name = sc.nextLine();
        System.out.print("Department  : "); String dept = sc.nextLine();
        library.addStudent(new Student(id, name, dept));
    }

    static void issueBook() {
        System.out.print("Student ID : "); int sid = readInt();
        System.out.print("Book ID    : "); int bid = readInt();
        try { library.issueBook(sid, bid); }
        catch (Exception e) { System.out.println("Error: " + e.getMessage()); }
    }

    static void returnBook() {
        System.out.print("Student ID : "); int sid = readInt();
        System.out.print("Book ID    : "); int bid = readInt();
        try { library.returnBook(sid, bid); }
        catch (Exception e) { System.out.println("Error: " + e.getMessage()); }
    }

    static void viewStudent() {
        System.out.print("Student ID : "); int sid = readInt();
        try { library.findStudent(sid).displayDetails(); }
        catch (Exception e) { System.out.println("Error: " + e.getMessage()); }
    }

    static int readInt() {
        while (true) {
            try {
                int val = Integer.parseInt(sc.nextLine().trim());
                return val;
            } catch (NumberFormatException e) {
                System.out.print("Enter a valid number: ");
            }
        }
    }
}
