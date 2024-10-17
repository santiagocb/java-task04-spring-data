import com.ticketland.config.ApplicationConfig;
import com.ticketland.config.DatabaseSetup;
import com.ticketland.exceptions.InsufficientFundsException;
import com.ticketland.programs.BookingFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Application {

    public static final Logger logger = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {

        ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);

        BookingFacade bookingFacade = context.getBean(BookingFacade.class);

        // Create accounts of existing users
        bookingFacade.createAccount("01");
        bookingFacade.createAccount("03");
        bookingFacade.createAccount("04");

        // Refill account existing accounts
        bookingFacade.refillAccount("01", 1000);
        bookingFacade.refillAccount("03", 1000);
        bookingFacade.refillAccount("04", 100);

        // Show all events
        bookingFacade.showAllEvents();

        // Book a ticket successfully
        bookingFacade.bookTicket("01", "001");
        bookingFacade.bookTicket("01", "002");
        bookingFacade.bookTicket("03", "001");
        bookingFacade.bookTicket("03", "002");
        bookingFacade.bookTicket("01", "001");
        bookingFacade.bookTicket("01", "004");

        // Show all user accounts
        bookingFacade.showAllUserAccounts();

        // Show all tickets
        bookingFacade.showAllTickets();

        // Book a ticket with no enough money
        try {
            bookingFacade.bookTicket("04", "004");
        } catch (InsufficientFundsException e) {
           logger.info("User account with ID 04 does not have enough money for event 004");
        }

        // Show tickets of user 01
        bookingFacade.showTicketsByUserAccountId("01");

        DatabaseSetup databaseSetup = context.getBean(DatabaseSetup.class);
        databaseSetup.dropTables();
    }
}
