package org.fed333.ticket.booking.app;

import lombok.extern.slf4j.Slf4j;
import org.fed333.ticket.booking.app.facade.BookingFacade;
import org.fed333.ticket.booking.app.facade.impl.BookingFacadeImpl;
import org.fed333.ticket.booking.app.model.User;
import org.fed333.ticket.booking.app.model.impl.UserImpl;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@Slf4j
public class SpringApplication {

    public static void main(String[] args) {
        //TODO Spring XML-based container raising.
        log.info("Start raising ApplicationContext...");
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:services.xml");
        log.info("ApplicationContext has been raised.");
        BookingFacade bookingFacade = context.getBean(BookingFacade.class);
        UserImpl user = UserImpl.builder()
                .name("Roman")
                .email("kovalchuk.roman03@gmail.com").build();
        bookingFacade.createUser(user);
        User userById = bookingFacade.getUserById(user.getId());
        log.info("userById.equals(user) = {}", userById.equals(user));
    }
}
