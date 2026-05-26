# 📚 Library Management System

A console-based **Library Management System** built in Java, demonstrating core **Object-Oriented Programming (OOP)** concepts.

---

## 🧩 OOP Concepts Used

| Concept | How it's Applied |
|---|---|
| **Class & Object** | `Book`, `Student`, `Librarian`, `Library` classes with objects |
| **Encapsulation** | All fields are `private`, accessed via getters/setters |
| **Inheritance** | `Student` and `Librarian` both extend the `Person` parent class |
| **Polymorphism** | `displayDetails()` is overridden in both `Student` and `Librarian` |
| **Abstraction** | `LibraryOperations` interface with `issueBook()` and `returnBook()` methods |
| **Constructor** | Parameterized constructors in all classes |
| **Exception Handling** | Custom exceptions for all error scenarios |

---

## 📁 Project Structure

```
LibraryManagementSystem/
└── src/
    ├── Main.java                          # Entry point (menu-driven)
    ├── library/
    │   ├── Person.java                    # Abstract parent class
    │   ├── LibraryOperations.java         # Interface
    │   ├── Book.java                      # Book entity
    │   ├── Student.java                   # Student (extends Person)
    │   ├── Librarian.java                 # Librarian (extends Person)
    │   └── Library.java                   # Core logic (implements LibraryOperations)
    └── exceptions/
        ├── BookNotFoundException.java
        ├── BookAlreadyIssuedException.java
        ├── BorrowLimitExceededException.java
        └── StudentNotFoundException.java
```

---

## ⚙️ How to Run

### Prerequisites
- Java JDK 17 or higher

### Compile
```bash
cd LibraryManagementSystem/src
javac -d . library/*.java exceptions/*.java Main.java
```

### Run
```bash
java Main
```

---

## 📋 Features

- ✅ Add books to the library
- ✅ View all books with availability status
- ✅ Register students
- ✅ Issue books to students (max 3 books per student)
- ✅ Return books
- ✅ View student details with borrowed books list
- ✅ Search books by author name
- ✅ Custom exception handling for all error cases

---

## 📌 Business Rules

- A student can borrow a **maximum of 3 books** at a time
- A book that is already issued **cannot be issued again** until returned
- A student can only return a book **they have borrowed**

---

## 🚫 Custom Exceptions

| Exception | When Thrown |
|---|---|
| `BookNotFoundException` | Book ID doesn't exist |
| `BookAlreadyIssuedException` | Book is already issued to someone |
| `BorrowLimitExceededException` | Student has already borrowed 3 books |
| `StudentNotFoundException` | Student ID doesn't exist |

---

## 👤 Author

ADD BOOK
<img width="1471" height="650" alt="Screenshot 2026-05-26 153550" src="https://github.com/user-attachments/assets/8ffa2fe4-8ea4-4850-9adc-3142df204ef7" />
---

ADD STUDENT
<img width="1471" height="650" alt="Screenshot 2026-05-26 153550" src="https://github.com/user-attachments/assets/c9a8a2ee-02e0-4907-82dc-3cc0a7425444" />
---

ISSUE BOOK
<img width="716" height="323" alt="Screenshot 2026-05-26 154350" src="https://github.com/user-attachments/assets/4af0f200-c535-418c-a0d4-481fdd7c920e" />
---

RETURN BOOK
<img width="661" height="313" alt="Screenshot 2026-05-26 154422" src="https://github.com/user-attachments/assets/0358b089-4197-4c6d-8ca4-96f744932d3a" />
---

VIEW STUDENTS DETAILS
<img width="810" height="474" alt="Screenshot 2026-05-26 154446" src="https://github.com/user-attachments/assets/146dbf00-8cb3-4500-afef-d0bf0884d0b0" />
---




