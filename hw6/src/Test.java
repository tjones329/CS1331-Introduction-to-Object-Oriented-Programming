import java.util.ArrayList;
import java.util.List;

/**
 * Tests for hw6
 * @author Tillson Galloway
 * @version 1.1.2
 */
public class Test {

    public static void main(String[] args) {
        try {
            test();
            Color.prettyPrint(Color.CYAN + "Tests passed!");
        } catch (AssertionFailure failure) {
            Color.prettyPrint(Color.RED + "Tests failed: " + Color.CYAN + failure.getMessage());
            Color.prettyPrint(Color.RED + "Stack trace:");
            for (StackTraceElement element : failure.getStackTrace()) {
                System.out.println(Color.strip(element.toString()));
            }
        }
    }

    private static void test() throws AssertionFailure {
        List<Transaction> pastTransactions = new ArrayList<>();

        // Transaction tests
        Transaction transactionOne = new Transaction(TransactionType.DEPOSIT, 10.0);
        assertEquals("getType() does not return the value specified during initialization", transactionOne.getType(), TransactionType.DEPOSIT);
        assertEquals("getAmount() does not return the value specified during initialization", transactionOne.getAmount(), 10.0);
        assertBoolean("getComment() returns an incorrect value when a comment is not present.", !transactionOne.getComment().isPresent());
        assertBoolean("hasComment() returns an incorrect value when a comment is not present.", !transactionOne.hasComment());

        String COMMENT_TEST_STRING_1 = "len is 8";
        String COMMENT_TEST_STRING_2 = "length is 12";
        String COMMENT_TEST_STRING_3 = "size5";

        Transaction transactionTwo = new Transaction(TransactionType.WITHDRAWAL, 10.0, COMMENT_TEST_STRING_1);
        assertEquals("getType() does not return the value specified during initialization", transactionTwo.getType(), TransactionType.WITHDRAWAL);
        assertEquals("getAmount() does not return the value specified during initialization", transactionTwo.getAmount(), 10.0);
        assertBoolean("getComment() returns an incorrect value when a comment is present.", transactionTwo.hasComment());
        assertEquals("hasComment() returns an incorrect value with a value is present.", transactionTwo.getComment().get(), COMMENT_TEST_STRING_1);

        pastTransactions.add(transactionOne);
        pastTransactions.add(transactionTwo);

        pastTransactions.add(new Transaction(TransactionType.DEPOSIT, 20.0, COMMENT_TEST_STRING_2));

        for (int i = 0; i < 11; i++) {
            pastTransactions.add(new Transaction(
                    i % 2 == 0 ? TransactionType.DEPOSIT : TransactionType.WITHDRAWAL,
                    i));
        }
        int LIST_SIZE = pastTransactions.size();

        // Account tests
        Account account = new Account(pastTransactions);
        assertNotNull("getTransaction(index) for some index in [0, numberOfTransactions) was null", account.getTransaction(0));
        assertNotNull("getTransaction(index) for some index in [0, numberOfTransactions) was null", account.getTransaction(1));
        assertNotNull("getTransaction(index) for some index in [0, numberOfTransactions) was null", account.getTransaction(11));
//        assertEquals("The size of getWithdrawals() does match the number of withdrawals specified during initialization.", account.getWithdrawals().size(), 6);
        assertEquals("The size of getDeposits() does match the number of deposits specified during initialization.", account.getDeposits().size(), 8);
        assertEquals("getTransactionsByAmount(amount) returns an incorrect number of transactions.", account.getTransactionsByAmount(10.0).size(), 3);
        assertEquals("getTransactionsByAmount(amount) returns an incorrect number of transactions.", account.getTransactionsByAmount(50.0).size(), 0);
        assertEquals("findTransactionsByPredicate(predicate) returns an incorrect value.", account.findTransactionsByPredicate((t) -> t.getAmount() == 10.0).size(), 3);
        assertEquals("getTransactionsWithComment() returns an incorrect number of 'commented' transactions.", account.getTransactionsWithComment().size(), 2);
        assertEquals("getTransactionsWithCommentLongerThan(length) returns an incorrect number of transactions with comments that's lengths are (length, infinity).", account.getTransactionsWithCommentLongerThan(5).size(), 2);
        assertEquals("getTransactionsWithCommentLongerThan(length) returns an incorrect number of transactions with comments that's lengths are (length, infinity).", account.getTransactionsWithCommentLongerThan(8).size(), 1);
        assertEquals("getTransactionsWithCommentLongerThan(length) returns an incorrect number of transactions with comments that's lengths are (length, infinity).", account.getTransactionsWithCommentLongerThan(12).size(), 0);
        assertEquals("getTransactionsWithCommentLongerThan(length) returns an incorrect number of transactions with comments that's lengths are (length, infinity).", account.getTransactionsWithCommentLongerThan(10).get(0).getComment().get(), COMMENT_TEST_STRING_2);

        assertEquals("getPastTransactions() does not return the transactions specified during initialization.", account.getPastTransactions().size(), LIST_SIZE);

        account.getPastTransactions().add(new Transaction(TransactionType.DEPOSIT, 15.0, COMMENT_TEST_STRING_3));
        assertEquals("getPastTransactions() does not return a correct number of transactions.", account.getPastTransactions().size(), LIST_SIZE + 1);
        assertEquals("getTransactionsWithComment() returns an incorrect number of 'commented' transactions.", account.getTransactionsWithComment().size(), 3);

        Transaction nullCommentTransaction = new Transaction(TransactionType.DEPOSIT, 15.0, null);
        assertBoolean("hasComment() returns incorrect value for Transaction with null comment.", !nullCommentTransaction.hasComment());
        account.getPastTransactions().add(nullCommentTransaction);
        assertEquals("getTransactionsWithComment() returns an incorrect number of 'commented' transactions.", account.getTransactionsWithComment().size(), 3);

    }

    // Testing methods

    private static void assertEquals(String check, Object obj1, Object obj2) throws AssertionFailure {
        if (!obj1.equals(obj2)) {
            throw new AssertionFailure(check);
        }
    }

    private static void assertBoolean(String check, boolean b) throws AssertionFailure {
        if (!b) {
            throw new AssertionFailure(check);
        }
    }

    private static void assertNotNull(String check, Object obj) throws AssertionFailure {
        if (obj == null) {
            throw new AssertionFailure(check);
        }
    }


    private static class AssertionFailure extends Throwable {
        public AssertionFailure(String message) {
            super(message);
        }
    }

    private enum Color {
        RED("\u001B[31m"),
        CLEAR("\u001B[0m"),
        CYAN("\u001B[36m");

        private final String code;

        Color(String code) {
            this.code = code;
        }

        public String toString() {
            return code;
        }

        public static void prettyPrint(String message) {
            System.out.println(message + Color.CLEAR);
        }

        public static String strip(String message) {
            return message.replaceAll("\u001B\\[[;\\d]*m", "");
        }
    }
}