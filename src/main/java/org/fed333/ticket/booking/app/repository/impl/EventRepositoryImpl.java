package org.fed333.ticket.booking.app.repository.impl;

import lombok.Setter;
import org.fed333.ticket.booking.app.repository.EventRepository;
import org.fed333.ticket.booking.app.model.Event;
import org.fed333.ticket.booking.app.repository.impl.component.LongIdGenerator;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class EventRepositoryImpl extends AbstractCrudDao<Event,Long> implements EventRepository {

    @Setter
    private LongIdGenerator idGenerator;

    @Override
    protected Long nextId() {
        return idGenerator.generateNextId();
    }

    @Override
    public List<Event> getAllByTitle(String title) {
        return getAll().stream().filter(e->e.getTitle().equals(title)).collect(Collectors.toList());
    }

    @Override
    public List<Event> getAllByDate(Date date) {
        return getAll().stream().filter(e->e.getDate().equals(date)).collect(Collectors.toList());
    }
}
