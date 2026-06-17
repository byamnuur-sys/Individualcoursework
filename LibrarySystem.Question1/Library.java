import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class Library {
    private List<Book> books;
    private List<Member> members;

    public Library() {
        this.books = new ArrayList<>();
        this.members = new ArrayList<>();
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public void registerMember(Member member) {
        members.add(member);
    }

    /**
     * core requirement (c): Business Rules for Lending Books
     */
    public boolean lendBook(String isbn, String memberId) {
        Book targetBook = findBookByIsbn(isbn);
        Member targetMember = findMemberById(memberId);

        if (targetBook == null) {
            System.out.println(">>> Operation Failed: Book with ISBN " + isbn + " not found.");
            return false;
        }
        if (targetMember == null) {
            System.out.println(">>> Operation Failed: Member with ID " + memberId + " not found.");
            return false;
        }

        // Rule Enforcer: At most one active loan per book
        if (!targetBook.isAvailable()) {
            System.out.println(">>> REJECTED: '" + targetBook.getTitle() + "' is already currently out on loan.");
            return false;
        }

        // Complete the process
        Loan processingLoan = new Loan(targetMember, targetBook, LocalDate.now(), 14);
        targetBook.setAvailable(false);
        targetMember.addLoan(processingLoan);
        System.out.println(">>> SUCCESS: '" + targetBook.getTitle() + "' successfully lent to " + targetMember.getName());
        return true;
    }

    /**
     * core requirement (c): Business Rules for Returning Books
     */
    public boolean returnBook(String isbn) {
        Book targetBook = findBookByIsbn(isbn);

        if (targetBook == null) {
            System.out.println(">>> Operation Failed: Book with ISBN " + isbn + " not found.");
            return false;
        }

        if (targetBook.isAvailable()) {
            System.out.println(">>> Notice: '" + targetBook.getTitle() + "' is already back inside the library collection inventory.");
            return false;
        }

        // Trace and clean active loan structures
        for (Member member : members) {
            for (Loan loan : member.getCurrentLoans()) {
                if (loan.getBook().getIsbn().equals(isbn)) {
                    member.removeLoan(loan);
                    targetBook.setAvailable(true);
                    System.out.println(">>> SUCCESS: '" + targetBook.getTitle() + "' returned successfully by " + member.getName());
                    return true;
                }
            }
        }
        return false;
    }

    public List<Book> searchBookByTitle(String title) {
        List<Book> matchingBooks = new ArrayList<>();
        for (Book b : books) {
            if (b.getTitle().equalsIgnoreCase(title)) {
                matchingBooks.add(b);
            }
        }
        return matchingBooks;
    }

    // Helper methods to resolve entities internally
    private Book findBookByIsbn(String isbn) {
        for (Book b : books) {
            if (b.getIsbn().equals(isbn)) return b;
        }
        return null;
    }

    private Member findMemberById(String id) {
        for (Member m : members) {
            if (m.getMemberId().equals(id)) return m;
        }
        return null;
    }

    public void printLibraryState() {
        System.out.println("\n========= CURRENT LIBRARY STATE =========");
        System.out.println("BOOKS IN INVENTORY:");
        for (Book b : books) System.out.println("  " + b);
        System.out.println("REGISTERED MEMBERS:");
        for (Member m : members) {
            System.out.println("  " + m);
            for (Loan l : m.getCurrentLoans()) {
                System.out.println("    -> " + l);
            }
        }
        System.out.println("=========================================\n");
    }
}
