import com.ticketland.config.ApplicationConfig;
import com.ticketland.programs.BookingFacade;
import com.ticketland.repos.UserAccountRepository;
import com.ticketland.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Application {

    public static final Logger logger = LoggerFactory.getLogger(BookingFacade.class);

    public static void main(String[] args) {

        System.out.println("HOla mundo");

        ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);

        UserService userService = context.getBean(UserService.class);
        UserAccountRepository userAccountRepository = context.getBean(UserAccountRepository.class);


        userService.showUsersJPA();
        userAccountRepository.findAll().forEach(u -> System.out.println(u.toString()));
    }
}
