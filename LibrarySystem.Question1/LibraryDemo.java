

public class LibraryDemo {
    public static void main(String[] args) {
        // Instantiate the library context
        Library centralLibrary = new Library();

        // 1. Create and seed 3 Books (using overloaded constructors)
        Book book1 = new Book("978-0134685991", "Effective Java", "Joshua Bloch");
        Book book2 = new Book("978-0132350884", "Clean Code", "Robert C. Martin");
        Book book3 = new Book("978-0596009205", "Head First Design Patterns"); // Uses default fallback inside constructor 1

        centralLibrary.addBook(book1);
        centralLibrary.addBook(book2);
        centralLibrary.addBook(book3);

        // 2. Create and register 2 members
        Member member1 = new Member("M001", "Alice Nsubuga");
        Member member2 = new Member("M002", "Bob Barigye");

        centralLibrary.registerMember(member1);
        centralLibrary.registerMember(member2);

        // 3. Print state BEFORE sequence of operations
        System.out.println("--- STATE BEFORE OPERATIONS ---");
        centralLibrary.printLibraryState();

        // 4. Perform lending/returning sequences
        System.out.println("--- EXECUTING TRANSACTIONS ---");
        // Transaction A: Successful execution
        centralLibrary.lendBook("978-0134685991", "M001"); 
        
        // Transaction B: Successful execution (Member 1 borrowing a second book)
        centralLibrary.lendBook("978-0132350884", "M001");

        // Transaction C: REJECTED execution (Attempting to borrow an unavailable book)
        System.out.println("\n[Attempting Illegal Operation]");
        centralLibrary.lendBook("978-0134685991", "M002"); 

        // Transaction D: Return Book Sequence
        System.out.println("\n[Processing Return Sequence]");
        centralLibrary.returnBook("978-0134685991");

        // Transaction E: Post-Return Lend Action (Now eligible to borrow)
        System.out.println("\n[Retrying original illegal operation]");
        centralLibrary.lendBook("978-0134685991", "M002");

        // 5. Print state AFTER operations
        System.out.println("\n--- STATE AFTER OPERATIONS ---");
        centralLibrary.printLibraryState();
    }
}

