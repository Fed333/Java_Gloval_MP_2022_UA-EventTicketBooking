package org.fed333.ticket.booking.app.facade;

import org.fed333.ticket.booking.app.model.Event;
import org.fed333.ticket.booking.app.model.Ticket;
import org.fed333.ticket.booking.app.model.User;
import org.fed333.ticket.booking.app.model.impl.EventImpl;
import org.fed333.ticket.booking.app.model.impl.TicketImpl;
import org.fed333.ticket.booking.app.model.impl.UserImpl;
import org.fed333.ticket.booking.app.util.comparator.EventEqualityComparator;
import org.fed333.ticket.booking.app.util.comparator.TicketEqualityComparator;
import org.fed333.ticket.booking.app.util.comparator.UserEqualityComparator;
import org.fed333.ticket.booking.app.utils.TestingDataUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import java.util.*;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;
import static org.assertj.core.api.Assertions.assertThat;
import static org.fed333.ticket.booking.app.utils.DateUtils.parseDate;
import static org.fed333.ticket.booking.app.utils.DateUtils.parseDateTime;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/resources/services.xml")
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class})
public class BookingFacadeITest {

    @Autowired
    private BookingFacade facade;

    private Map<Long, Event> testEvents;

    private Map<Long, User> testUsers;

    private Map<Long, Ticket> testTickets;

    private EventEqualityComparator eventComparator;

    private UserEqualityComparator userComparator;

    private TicketEqualityComparator ticketComparator;

    @Before
    public void setUp() {
        testEvents = Stream.of(
                new Object[][]{
                        {1L, new EventImpl(1L, "EPAM Townhall", parseDateTime("2022-09-05 15:30:00"))},
                        {2L, new EventImpl(2L, "VNTU openday", parseDateTime("2022-09-05 11:20:00"))},
                        {3L, new EventImpl(3L, "Kalush charity music concert", parseDateTime("2022-09-06 18:45:00"))},
                        {4L, new EventImpl(4L, "EPAM Townhall", parseDateTime("2022-10-05 15:30:00"))},
                        {5L, new EventImpl(5L, "EPAM Townhall", parseDateTime("2022-11-05 15:30:00"))},
                        {6L, new EventImpl(6L, "Event1 to be deleted", parseDateTime("2022-12-05 15:30:00"))},
                }
        ).collect(toMap(o -> (Long) o[0], o -> (Event) o[1]));

        testUsers = Stream.of(
                new Object[][]{
                        {1L, new UserImpl(1L, "Roman", "kovalchuk.roman03@gmail.com")},
                        {2L, new UserImpl(2L, "Pips", "mpostryk@gmail.com")},
                        {3L, new UserImpl(3L, "Ivan", "builbik@gmail.com")},
                        {4L, new UserImpl(4L, "Kyrylo", "kyrylo@gmail.com")},
                        {5L, new UserImpl(5L, "Ivan", "vanno8782@gmail.com")},
                        {6L, new UserImpl(6L, "Serhiy", "meizum@gmail.com")},
                        {7L, new UserImpl(7L, "Andrii", "riko@gmail.com")},
                        {8L, new UserImpl(8L, "Iryna", "ira@gmail.com")},
                        {9L, new UserImpl(9L, "Tetyana", "taniusha@gmail.com")},
                        {10L, new UserImpl(10L, "Viktoria", "viktoria@gmail.com")},
                        {11L, new UserImpl(11L, "Ivan", "tocker342@gmail.com")},
                        {12L, new UserImpl(12L, "Mykhailo", "mykhailo@gmail.com")},
                        {13L, new UserImpl(13L, "Hlib", "glego@gmail.com")},
                        {14L, new UserImpl(14L, "Pavlo", "pavlo_makushak@gmail.com")},
                        {15L, new UserImpl(15L, "Updated", "updated@gmail.com")},
                        {16L, new UserImpl(16L, "Deleted", "deleted@gmail.com")}
                }
        ).collect(toMap(o -> (Long) o[0], o -> (User) o[1]));

        testTickets = Stream.of(
                new Object[][]{
                        {1L, new TicketImpl(1L, 1L, 1L, Ticket.Category.PREMIUM, 12, false)},
                        {2L, new TicketImpl(2L, 1L, 3L, Ticket.Category.STANDARD, 13, false)},
                        {3L, new TicketImpl(3L, 1L, 5L, Ticket.Category.BAR, 2, true)},
                        {4L, new TicketImpl(4L, 2L, 13L, Ticket.Category.STANDARD, 10, true)},
                        {5L, new TicketImpl(4L, 2L, 11L, Ticket.Category.STANDARD, 1, false)},
                        {6L, new TicketImpl(4L, 2L, 11L, Ticket.Category.STANDARD, 1, false)},
                        {7L, new TicketImpl( 7L,  3L,  4L,  Ticket.Category.STANDARD,  132,  false)},
                        {8L, new TicketImpl( 8L,  3L,  3L,  Ticket.Category.STANDARD,  133,  false)},
                        {9L, new TicketImpl( 9L,  3L,  1L,  Ticket.Category.STANDARD,  134,  false)},
                        {10L, new TicketImpl( 10L,  3L,  2L,  Ticket.Category.STANDARD,  135,  false)},
                        {11L, new TicketImpl( 11L,  3L,  7L,  Ticket.Category.STANDARD,  136,  false)},
                        {12L, new TicketImpl( 12L,  3L,  6L,  Ticket.Category.STANDARD,  137,  false)},
                        {13L, new TicketImpl( 13L,  3L,  8L,  Ticket.Category.PREMIUM,  10,  false)},
                        {14L, new TicketImpl( 14L,  3L,  9L,  Ticket.Category.PREMIUM,  11,  false)},
                        {15L, new TicketImpl( 15L,  3L,  10L,  Ticket.Category.PREMIUM,  12,  false)},
                        {16L, new TicketImpl( 16L,  3L,  13L,  Ticket.Category.STANDARD,  138,  false)},
                        {17L, new TicketImpl( 17L,  3L,  5L,  Ticket.Category.STANDARD,  139,  false)},
                        {18L, new TicketImpl( 18L,  3L,  12L,  Ticket.Category.STANDARD,  140,  false)},
                        {19L, new TicketImpl( 19L,  3L,  14L,  Ticket.Category.STANDARD,  141,  false)},
                        {20L, new TicketImpl( 20L,  3L,  15L,  Ticket.Category.STANDARD,  142,  false)},
                }
        ).collect(toMap(o -> (Long) o[0], o -> (Ticket) o[1]));

        eventComparator = new EventEqualityComparator();
        userComparator = new UserEqualityComparator();
        ticketComparator = new TicketEqualityComparator();
    }

    @Test
    public void realLifeScenario() {
        Event testEvent = TestingDataUtils.createTestEvent(null);
        User testUser = TestingDataUtils.createTestUserWithName(null, "Test");

        Event createdEvent = facade.createEvent(testEvent);
        assertThat(createdEvent.getId()).isNotNull();
        assertThat(facade.getEventById(createdEvent.getId())).isEqualTo(createdEvent);

        User createdUser = facade.createUser(testUser);
        assertThat(createdUser.getId()).isNotNull();
        assertThat(facade.getUserById(createdUser.getId())).isEqualTo(createdUser);

        Ticket createdTicket = facade.bookTicket(createdUser.getId(), createdEvent.getId(), 1, Ticket.Category.PREMIUM);
        assertThat(createdTicket.getId()).isNotNull();

        List<Ticket> bookedTicketsByUser = facade.getBookedTickets(createdUser, 1, 1);
        assertThat(bookedTicketsByUser).isNotEmpty();
        assertThat(bookedTicketsByUser.get(0)).isEqualTo(createdTicket);

        List<Ticket> bookedTicketsByEvent = facade.getBookedTickets(createdEvent, 1, 1);
        assertThat(bookedTicketsByEvent).isNotEmpty();
        assertThat(bookedTicketsByEvent.get(0)).isEqualTo(createdTicket);

        boolean cancelled = facade.cancelTicket(createdTicket.getId());
        assertThat(cancelled).isTrue();

        Ticket actualTicket = facade.getBookedTickets(createdUser, 20, 1).stream().filter(t->t.getId().equals(createdTicket.getId())).findFirst().orElseThrow(NoSuchElementException::new);
        assertThat(actualTicket.isCancelled()).isTrue();
    }

    @Test
    public void getEventById_shouldReturnEvent() {
        Event testEvent = testEvents.get(1L);
        assertThat(facade.getEventById(testEvent.getId())).usingComparator(eventComparator).isEqualTo(testEvent);
    }

    @Test
    public void getEventsByTitle_shouldReturnAll() {
        String testTitle = "EPAM Townhall";
        List<Event> expected = testEvents.values().stream().filter(event -> event.getTitle().equals(testTitle)).collect(toList());

        assertThat(facade.getEventsByTitle(testTitle, 3, 1)).usingElementComparator(eventComparator).isEqualTo(expected);
    }

    @Test
    public void getEventsByTitle_shouldReturnPage1() {
        String testTitle = "EPAM Townhall";
        List<Event> expected = testEvents.values().stream().filter(event -> event.getTitle().equals(testTitle)).limit(2).collect(toList());

        assertThat(facade.getEventsByTitle(testTitle, 2, 1)).usingElementComparator(eventComparator).isEqualTo(expected);
    }

    @Test
    public void getEventsByTitle_shouldReturnPage2() {
        String testTitle = "EPAM Townhall";
        List<Event> expected = testEvents.values().stream().filter(event -> event.getTitle().equals(testTitle)).skip(2).limit(2).collect(toList());

        assertThat(facade.getEventsByTitle(testTitle, 2, 2)).usingElementComparator(eventComparator).isEqualTo(expected);
    }

    @Test
    public void getEventsForDay_shouldReturnAll() {
        Date testDay = parseDate("05-09-2022");
        List<Event> expected = Arrays.asList(testEvents.get(1L), testEvents.get(2L));

        List<Event> actual = facade.getEventsForDay(testDay, 2, 1);

        assertThat(actual).usingElementComparator(eventComparator).isEqualTo(expected);
    }

    @Test
    public void getEventsForDay_shouldReturnPage1() {
        Date testDay = parseDate("05-09-2022");
        List<Event> expected = Collections.singletonList(testEvents.get(1L));

        List<Event> actual = facade.getEventsForDay(testDay, 1, 1);

        assertThat(actual).usingElementComparator(eventComparator).isEqualTo(expected);
    }

    @Test
    public void getEventsForDay_shouldReturnPage2() {
        Date testDay = parseDate("05-09-2022");
        List<Event> expected = Collections.singletonList(testEvents.get(2L));

        List<Event> actual = facade.getEventsForDay(testDay, 1, 2);

        assertThat(actual).usingElementComparator(eventComparator).isEqualTo(expected);
    }

    @Test
    public void createEvent_shouldGenerateIdForEvent() {
        Event testEvent = TestingDataUtils.createTestEvent(null);

        Event actualEvent = facade.createEvent(testEvent);

        assertThat(actualEvent.getId()).isNotNull();
    }

    @Test
    public void createEvent_shouldGetCreatedEvent() {
        Event testEvent = TestingDataUtils.createTestEvent(null);

        Event expectedEvent = facade.createEvent(testEvent);
        Event actualEvent = facade.getEventById(expectedEvent.getId());

        assertThat(actualEvent).usingComparator(eventComparator).isEqualTo(expectedEvent);
    }

    @Test
    public void updateEvent_shouldChangeData() {
        Event testEvent = testEvents.get(3L);
        testEvent.setTitle("Kalush charity music \"STEFANIA\" concert.");
        testEvent.setDate(parseDate("2022-09-06 17:45:00"));

        facade.updateEvent(testEvent);
        Event actualEvent = facade.getEventById(testEvent.getId());

        assertThat(actualEvent).usingComparator(eventComparator).isEqualTo(testEvent);
    }

    @Test
    public void deleteEvent_shouldRemoveEvent() {
        Event testEvent = testEvents.get(6L);

        boolean deleted = facade.deleteEvent(testEvent.getId());

        assertThat(deleted).isTrue();
        assertThat(facade.getEventById(testEvent.getId())).isNull();
    }

    @Test
    public void getUserById_shouldReturn() {
        User testUser = testUsers.get(1L);
        assertThat(facade.getUserById(testUser.getId())).usingComparator(userComparator).isEqualTo(testUser);
    }

    @Test
    public void getUserByEmail_shouldReturn() {
        String testEmail = "mpostryk@gmail.com";
        User expectedUser = testUsers.get(2L);

        User actualUser = facade.getUserByEmail(testEmail);

        assertThat(actualUser).usingComparator(userComparator).isEqualTo(expectedUser);
    }

    @Test
    public void getUsersByName_shouldReturn() {
        String testName = "Ivan";
        List<User> expectedUsers = Arrays.asList(testUsers.get(3L), testUsers.get(5L), testUsers.get(11L));

        List<User> actualUsers = facade.getUsersByName(testName, 3, 1);

        assertThat(actualUsers).usingElementComparator(userComparator).isEqualTo(expectedUsers);
    }

    @Test
    public void getUsersByName_shouldReturnPage1() {
        String testName = "Ivan";
        List<User> expectedUsers = Arrays.asList(testUsers.get(3L), testUsers.get(5L));

        List<User> actualUsers = facade.getUsersByName(testName, 2, 1);

        assertThat(actualUsers).usingElementComparator(userComparator).isEqualTo(expectedUsers);
    }

    @Test
    public void getUsersByName_shouldReturnPage2() {
        String testName = "Ivan";
        List<User> expectedUsers = Collections.singletonList(testUsers.get(11L));

        List<User> actualUsers = facade.getUsersByName(testName, 2, 2);

        assertThat(actualUsers).usingElementComparator(userComparator).isEqualTo(expectedUsers);
    }

    @Test
    public void createUser_shouldGenerateIdForEvent() {
        User testUser = TestingDataUtils.createTestUser(null);

        User actualUser = facade.createUser(testUser);

        assertThat(actualUser.getId()).isNotNull();
    }

    @Test
    public void createUser_shouldGetCreatedEvent() {
        User testUser = TestingDataUtils.createTestUser(null);

        User expectedUser = facade.createUser(testUser);
        User actualUser = facade.getUserById(expectedUser.getId());

        assertThat(actualUser).usingComparator(userComparator).isEqualTo(expectedUser);
    }

    @Test
    public void updateUser() {
    }

    @Test
    public void deleteUser() {
    }

    @Test
    public void updateUser_shouldChangeData() {
        User testUser = testUsers.get(15L);
        testUser.setName("Changed");
        testUser.setEmail("changed@mail.com");

        facade.updateUser(testUser);
        User actualUser = facade.getUserById(testUser.getId());

        assertThat(actualUser).usingComparator(userComparator).isEqualTo(testUser);
    }

    @Test
    public void deleteUser_shouldRemoveEvent() {
        User testUser = testUsers.get(16L);

        boolean deleted = facade.deleteUser(testUser.getId());

        assertThat(deleted).isTrue();
        assertThat(facade.getUserById(testUser.getId())).isNull();
    }

    @Test
    public void bookTicket_shouldCreateTicketWithId() {
        User testUser = testUsers.get(16L);
        Event testEvent = testEvents.get(4L);
        int place = 3;
        Ticket.Category category = Ticket.Category.STANDARD;
        Ticket expectedTicket = TicketImpl.builder()
                .userId(testUser.getId())
                .eventId(testEvent.getId())
                .place(place)
                .category(category).build();

        Ticket actualTicket = facade.bookTicket(testUser.getId(), testEvent.getId(), place, category);

        assertThat(actualTicket.getId()).isNotNull();
        expectedTicket.setId(actualTicket.getId());
        assertThat(actualTicket).usingComparator(ticketComparator).isEqualTo(expectedTicket);
    }

    @Test
    public void getBookedTickets_shouldReturnByUser() {
        User testUser = testUsers.get(1L);
        List<Ticket> expectedTickets = Arrays.asList(testTickets.get(1L), testTickets.get(9L));

        List<Ticket> actualTickets = facade.getBookedTickets(testUser, 2, 1);

        assertThat(actualTickets).usingElementComparator(ticketComparator).isEqualTo(expectedTickets);
    }

    @Test
    public void getBookedTickets_shouldReturnByUserPage1() {
        User testUser = testUsers.get(1L);
        List<Ticket> expectedTickets = Collections.singletonList(testTickets.get(1L));

        List<Ticket> actualTickets = facade.getBookedTickets(testUser, 1, 1);

        assertThat(actualTickets).usingElementComparator(ticketComparator).isEqualTo(expectedTickets);
    }

    @Test
    public void getBookedTickets_shouldReturnByUserPage2() {
        User testUser = testUsers.get(1L);
        List<Ticket> expectedTickets = Collections.singletonList(testTickets.get(9L));

        List<Ticket> actualTickets = facade.getBookedTickets(testUser, 1, 2);

        assertThat(actualTickets).usingElementComparator(ticketComparator).isEqualTo(expectedTickets);
    }

    @Test
    public void getBookedTickets_shouldReturnByEvent() {
        Event testEvent = testEvents.get(3L);
        List<Ticket> expectedTickets = testTickets.values().stream().filter(t->t.getEventId().equals(3L)).collect(toList());
        List<Ticket> actualTickets = facade.getBookedTickets(testEvent, 14, 1);

        assertThat(actualTickets).usingElementComparator(ticketComparator).isEqualTo(expectedTickets);
    }

    @Test
    public void getBookedTickets_shouldReturnByEventPage1() {
        Event testEvent = testEvents.get(3L);
        List<Ticket> expectedTickets = testTickets.values().stream().filter(t->t.getEventId().equals(3L)).limit(7).collect(toList());
        List<Ticket> actualTickets = facade.getBookedTickets(testEvent, 7, 1);

        assertThat(actualTickets).usingElementComparator(ticketComparator).isEqualTo(expectedTickets);
    }

    @Test
    public void getBookedTickets_shouldReturnByEventPage2() {
        Event testEvent = testEvents.get(3L);
        List<Ticket> expectedTickets = testTickets.values().stream().filter(t->t.getEventId().equals(3L)).skip(7).collect(toList());
        List<Ticket> actualTickets = facade.getBookedTickets(testEvent, 7, 2);

        assertThat(actualTickets).usingElementComparator(ticketComparator).isEqualTo(expectedTickets);
    }

    @Test
    public void cancelTicket_shouldCancel() {

        Ticket testTicket = testTickets.get(2L);
        User testUser = testUsers.get(testTicket.getUserId());

        boolean cancelled = facade.cancelTicket(testTicket.getId());
        Ticket actualTicket = facade.getBookedTickets(testUser, 20, 1).stream().filter(t->t.getId().equals(testTicket.getId())).findFirst().orElseThrow(NoSuchElementException::new);

        assertThat(cancelled).isTrue();
        assertThat(actualTicket.isCancelled()).isTrue();
    }

}