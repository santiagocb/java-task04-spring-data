import com.ticketland.config.ApplicationConfig;
import com.ticketland.config.DatabaseSetup;
import com.ticketland.entities.Event;
import com.ticketland.entities.Ticket;
import com.ticketland.entities.User;
import com.ticketland.entities.UserAccount;
import com.ticketland.programs.BookingFacade;
import com.ticketland.repos.UserAccountRepository;
import com.ticketland.services.EventService;
import com.ticketland.services.TicketService;
import com.ticketland.services.UserAccountService;
import com.ticketland.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.time.LocalDate;
import java.time.Month;

public class Application {

    public static final Logger logger = LoggerFactory.getLogger(BookingFacade.class);

    public static void main(String[] args) {

        ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);

        DatabaseSetup databaseSetup = context.getBean(DatabaseSetup.class);
        databaseSetup.createTables();

        UserService userService = context.getBean(UserService.class);
        EventService eventService = context.getBean(EventService.class);
        UserAccountService userAccountService = context.getBean(UserAccountService.class);
        UserAccountRepository userAccountRepository = context.getBean(UserAccountRepository.class);

        // Create user 1
        var user = new User("01", "Monkey D Luffy", "monkey@luffy.de");
        userService.register(user);

        // Create event
        Event event1 = eventService.create(new Event("01", "ComiCon", "Plaza Mayor", LocalDate.of(2024, Month.DECEMBER, 12), 100));

        // Create user 2
        userService.register(new User("02", "Zoro", "zoro@luffy.de"));
        // Create user 3
        userService.register(new User("03", "Ussop", "ussop@luffy.de"));


        // Create account
        UserAccount ua = userAccountService.createAccount("01");

        // Refill account
        userAccountService.refillBalance("01", 100);


        System.out.println("All users: ");
        userService.findAll().forEach(u -> System.out.println(u.getId() + " " + u.getName() + " " + u.getEmail()));

        System.out.println("All events: ");
        eventService.findAll().forEach(e -> System.out.println(e.getId() + " " + e.getName() + " " + e.getTicketPrice()));

        System.out.println("All user accounts: ");
        userAccountRepository.findAll().forEach(u -> System.out.println(u.getId() + " " + u.getBalance() + " " + u.getUser()));

        System.out.println("Creating ticket");

        TicketService ticketService = context.getBean(TicketService.class);
        ticketService.generate(new Ticket(ua, event1));

        System.out.println("All tickets accounts: ");
        ticketService.findAll().forEach(u -> System.out.println(u.getId() + " " + u.getUser() + " " + u.getEvent()));

        databaseSetup.dropTables();
    }
}
