package org.fed333.ticket.booking.app.model.impl;

import lombok.Data;
import org.fed333.ticket.booking.app.model.Event;

import java.util.Date;

@Data
public class EventImpl implements Event {

    private Long id;

    private String title;

    private Date date;

}
