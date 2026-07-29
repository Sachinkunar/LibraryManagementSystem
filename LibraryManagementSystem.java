import java.util.ArrayList;
import java.util.Scanner;

class Book {
    private String isbn;
    private String title;
    private String author;
    private String genre;
    private boolean isAvailable;
    
    // Constructor
    public Book(String isbn, String title, String author, String genre) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.isAvailable = true; // Books are available by default
    }
    
    // Getters and setters
    public String getIsbn() { return isbn; }
    public void setIsbn(String isbn) { this.isbn = isbn; }
    
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    
    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }
    
    public String getGenre() { return genre; }
    public void setGenre(String genre) { this.genre = genre; }
    
    public boolean isAvailable() { return isAvailable; }
    public void setAvailable(boolean available) { isAvailable = available; }
    
    public void displayInfo() {
        System.out.println("ISBN: " + isbn);
        System.out.println("Title: " + title);
        System.out.println("Author: " + author);
        System.out.println("Genre: " + genre);
        System.out.println("Status: " + (isAvailable ? "Available" : "Borrowed"));
        System.out.println("-".repeat(40));
    }
}

class Member {
    private String memberId;
    private String name;
    private String contact;
    private ArrayList<Book> borrowedBooks;
    
    public Member(String memberId, String name, String contact) {
        this.memberId = memberId;
        this.name = name;
        this.contact = contact;
        this.borrowedBooks = new ArrayList<>();
    }
    
    // Getters and setters
    public String getMemberId() { return memberId; }
    public void setMemberId(String memberId) { this.memberId = memberId; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public String getContact() { return contact; }
    public void setContact(String contact) { this.contact = contact; }
    
    public ArrayList<Book> getBorrowedBooks() { return borrowedBooks; }
    
    public boolean borrowBook(Book book) {
        if(book.isAvailable()) {
            borrowedBooks.add(book);
            book.setAvailable(false);
            return true;
        }
        return false;
    }
    
    public boolean returnBook(Book book) {
        if(borrowedBooks.contains(book)) {
            borrowedBooks.remove(book);
            book.setAvailable(true);
            return true;
        }
        return false;
    }
    
    public void displayInfo() {
        System.out.println("Member ID: " + memberId);
        System.out.println("Name: " + name);
        System.out.println("Contact: " + contact);
        System.out.println("Books Borrowed: " + borrowedBooks.size());
        
        if(!borrowedBooks.isEmpty()) {
            System.out.println("Borrowed Books:");
            for(Book book : borrowedBooks) {
                System.out.println("  - " + book.getTitle());
            }
        }
        System.out.println("-".repeat(40));
    }
}

class Library {
    private ArrayList<Book> books;
    private ArrayList<Member> members;
    
    public Library() {
        books = new ArrayList<>();
        members = new ArrayList<>();
    }
    
    public void addBook(Book book) {
        books.add(book);
    }
    
    public void addMember(Member member) {
        members.add(member);
    }
    
    public Book findBookByIsbn(String isbn) {
        for(Book book : books) {
            if(book.getIsbn().equals(isbn)) {
                return book;
            }
        }
        return null;
    }
    
    public Member findMemberById(String memberId) {
        for(Member member : members) {
            if(member.getMemberId().equals(memberId)) {
                return member;
            }
        }
        return null;
    }
    
    public ArrayList<Book> searchBooks(String keyword) {
        ArrayList<Book> results = new ArrayList<>();
        keyword = keyword.toLowerCase();
        
        for(Book book : books) {
            if(book.getTitle().toLowerCase().contains(keyword) || 
               book.getAuthor().toLowerCase().contains(keyword) ||
               book.getGenre().toLowerCase().contains(keyword)) {
                results.add(book);
            }
        }
        return results;
    }
    
    public void displayAllBooks() {
        System.out.println("\n=== ALL BOOKS ===");
        if(books.isEmpty()) {
            System.out.println("No books in library!");
            return;
        }
        
        for(Book book : books) {
            book.displayInfo();
        }
    }
    
    public void displayAvailableBooks() {
        System.out.println("\n=== AVAILABLE BOOKS ===");
        boolean found = false;
        
        for(Book book : books) {
            if(book.isAvailable()) {
                book.displayInfo();
                found = true;
            }
        }
        
        if(!found) {
            System.out.println("No books available at the moment!");
        }
    }
}





public class LibraryManagementSystem {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Library library = new Library();

        // Sample Books
        library.addBook(new Book("978-3-16-148410-0",
                "Java Programming Guide",
                "John Smith",
                "Programming"));

        library.addBook(new Book("978-0-262-03384-8",
                "Introduction to Algorithms",
                "Thomas Cormen",
                "Computer Science"));

        library.addBook(new Book("978-1-56619-909-4",
                "Clean Code",
                "Robert Martin",
                "Programming"));

        // Sample Member
        library.addMember(new Member("M001",
                "Alice Johnson",
                "9876543210"));

        int choice;

        do {

            System.out.println("\n=== LIBRARY MANAGEMENT SYSTEM ===");
            System.out.println("1. Add New Book");
            System.out.println("2. Register New Member");
            System.out.println("3. Display All Books");
            System.out.println("4. Display Available Books");
            System.out.println("5. Search Books");
            System.out.println("6. Borrow Book");
            System.out.println("7. Return Book");
            System.out.println("8. Exit");
            System.out.print("Enter your choice: ");

            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:

                    System.out.println("\n=== ADD NEW BOOK ===");

                    System.out.print("Enter ISBN: ");
                    String isbn = sc.nextLine();

                    System.out.print("Enter Title: ");
                    String title = sc.nextLine();

                    System.out.print("Enter Author: ");
                    String author = sc.nextLine();

                    System.out.print("Enter Genre: ");
                    String genre = sc.nextLine();

                    library.addBook(new Book(isbn, title, author, genre));

                    System.out.println("✅ Book added successfully!");
                    break;

                case 2:

                    System.out.println("\n=== REGISTER MEMBER ===");

                    System.out.print("Enter Member ID: ");
                    String id = sc.nextLine();

                    System.out.print("Enter Name: ");
                    String name = sc.nextLine();

                    System.out.print("Enter Contact: ");
                    String contact = sc.nextLine();

                    library.addMember(new Member(id, name, contact));

                    System.out.println("✅ Member registered successfully!");
                    break;

                case 3:

                    library.displayAllBooks();
                    break;

                case 4:

                    library.displayAvailableBooks();
                    break;

                case 5:

                    System.out.println("\n=== SEARCH BOOKS ===");

                    System.out.print("Enter search keyword: ");
                    String keyword = sc.nextLine();

                    ArrayList<Book> results = library.searchBooks(keyword);

                    if (results.isEmpty()) {
                        System.out.println("No books found!");
                    } else {

                        System.out.println("\nSearch Results:");

                        for (Book b : results) {
                            b.displayInfo();
                        }
                    }

                    break;

                case 6:

                    System.out.println("\n=== BORROW BOOK ===");

                    System.out.print("Enter Member ID: ");
                    String memberId = sc.nextLine();

                    System.out.print("Enter Book ISBN: ");
                    String bookIsbn = sc.nextLine();

                    Member member = library.findMemberById(memberId);
                    Book book = library.findBookByIsbn(bookIsbn);

                    if (member == null) {

                        System.out.println("Member not found!");

                    } else if (book == null) {

                        System.out.println("Book not found!");

                    } else if (member.borrowBook(book)) {

                        System.out.println("✅ Book borrowed successfully!");
                        System.out.println("Member: " + member.getName());
                        System.out.println("Book: " + book.getTitle());

                    } else {

                        System.out.println("Book is already borrowed.");
                    }

                    break;

                case 7:

                    System.out.println("\n=== RETURN BOOK ===");

                    System.out.print("Enter Member ID: ");
                    String mId = sc.nextLine();

                    System.out.print("Enter Book ISBN: ");
                    String bIsbn = sc.nextLine();

                    Member m = library.findMemberById(mId);
                    Book bk = library.findBookByIsbn(bIsbn);

                    if (m == null) {

                        System.out.println("Member not found!");

                    } else if (bk == null) {

                        System.out.println("Book not found!");

                    } else if (m.returnBook(bk)) {

                        System.out.println("✅ Book returned successfully!");

                    } else {

                        System.out.println("This member has not borrowed this book.");
                    }

                    break;

                case 8:

                    System.out.println("Thank you for using Library Management System.");
                    break;

                default:

                    System.out.println("Invalid Choice!");
            }

        } while (choice != 8);

        sc.close();
    }
}
