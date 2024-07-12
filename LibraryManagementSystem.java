import java.util.ArrayList;
import java.util.Scanner;

// Book class to represent a book
class Book {
    private String title;
    private String author;
    private boolean available;

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
        this.available = true;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isAvailable() {
        return available;
    }

    public void borrowBook() {
        if (available) {
            available = false;
            System.out.println("You have borrowed the book: " + title);
        } else {
            System.out.println("Sorry, the book is currently not available.");
        }
    }

    public void returnBook() {
        if (!available) {
            available = true;
            System.out.println("Thank you for returning the book: " + title);
        } else {
            System.out.println("This book is already available in the library.");
        }
    }

    public void display() {
        System.out.println("Title: " + title);
        System.out.println("Author: " + author);
        System.out.println("Available: " + (available ? "Yes" : "No"));
    }
}

// Member class to represent a library member
class Member {
    private String name;
    private int memberId;

    public Member(String name, int memberId) {
        this.name = name;
        this.memberId = memberId;
    }

    public String getName() {
        return name;
    }

    public int getMemberId() {
        return memberId;
    }

    public void display() {
        System.out.println("Member Name: " + name);
        System.out.println("Member ID: " + memberId);
    }
}

// Library class to manage books and members
class Library {
    private ArrayList<Book> books;
    private ArrayList<Member> members;

    public Library() {
        books = new ArrayList<>();
        members = new ArrayList<>();
    }

    public void addBook(Book book) {
        books.add(book);
        System.out.println("Book added to the library: " + book.getTitle());
    }

    public void addMember(Member member) {
        members.add(member);
        System.out.println("Member added to the library: " + member.getName());
    }

    public void displayBooks() {
        System.out.println("\n--- Available Books in Library ---");
        for (Book book : books) {
            book.display();
            System.out.println();
        }
    }

    public void displayMembers() {
        System.out.println("\n--- Library Members ---");
        for (Member member : members) {
            member.display();
            System.out.println();
        }
    }

    public void borrowBook(int memberId, String bookTitle) {
        Member member = findMember(memberId);
        if (member != null) {
            Book book = findBook(bookTitle);
            if (book != null) {
                book.borrowBook();
            } else {
                System.out.println("Book not found in the library.");
            }
        } else {
            System.out.println("Member not found in the library records.");
        }
    }

    public void returnBook(int memberId, String bookTitle) {
        Member member = findMember(memberId);
        if (member != null) {
            Book book = findBook(bookTitle);
            if (book != null) {
                book.returnBook();
            } else {
                System.out.println("Book not found in the library.");
            }
        } else {
            System.out.println("Member not found in the library records.");
        }
    }

    private Member findMember(int memberId) {
        for (Member member : members) {
            if (member.getMemberId() == memberId) {
                return member;
            }
        }
        return null;
    }

    private Book findBook(String bookTitle) {
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(bookTitle)) {
                return book;
            }
        }
        return null;
    }
}

// LibraryManagementSystem class to interact with the library
public class LibraryManagementSystem {
    public static void main(String[] args) {
        Library library = new Library();

        // Adding books to the library
        library.addBook(new Book("Java Programming", "John Doe"));
        library.addBook(new Book("Python Basics", "Jane Smith"));
        library.addBook(new Book("Data Structures in C", "Alan Turing"));

        // Adding members to the library
        library.addMember(new Member("Santi Karmakar", 1001));
        library.addMember(new Member("mahasin Khan", 1002));

        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            // Display menu
            System.out.println("\n--- Library Management System ---");
            System.out.println("1. Display All Books");
            System.out.println("2. Display All Members");
            System.out.println("3. Borrow a Book");
            System.out.println("4. Return a Book");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (choice) {
                case 1:
                    library.displayBooks();
                    break;
                case 2:
                    library.displayMembers();
                    break;
                case 3:
                    System.out.print("Enter Member ID: ");
                    int memberId = scanner.nextInt();
                    scanner.nextLine(); // Consume newline character
                    System.out.print("Enter Book Title to Borrow: ");
                    String borrowBookTitle = scanner.nextLine();
                    library.borrowBook(memberId, borrowBookTitle);
                    break;
                case 4:
                    System.out.print("Enter Member ID: ");
                    int returnMemberId = scanner.nextInt();
                    scanner.nextLine(); // Consume newline character
                    System.out.print("Enter Book Title to Return: ");
                    String returnBookTitle = scanner.nextLine();
                    library.returnBook(returnMemberId, returnBookTitle);
                    break;
                case 5:
                    System.out.println("Exiting program. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number from 1 to 5.");
            }

        } while (choice != 5);

        scanner.close();
    }
}
