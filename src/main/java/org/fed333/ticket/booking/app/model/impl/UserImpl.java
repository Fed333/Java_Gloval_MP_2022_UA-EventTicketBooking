package org.fed333.ticket.booking.app.model.impl;

import lombok.Data;
import org.fed333.ticket.booking.app.model.User;

/**
 * Entity User implementation.
 * @author Roman_Kovalchuk
 * */
@Data
public class UserImpl implements User {

    private Long id;

    private String name;

    private String email;

}
