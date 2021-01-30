package com.neu.prattle.exceptions;

/***
 * An representation of an error which is thrown where a request has been made
 * for creation of a user object that already exists in the system.
 * Refer {@link User#equals}
 * Refer {@link com.neu.prattle.service.UserService#addUser(User)}
 *
 * @author CS5500 Fall 2019 Teaching staff
 * @version dated 2019-10-06
 */
public class GroupAlreadyPresentException extends RuntimeException {
    /**
	 *
	 */
	private static final long serialVersionUID = -4845176561270017896L;

	public GroupAlreadyPresentException(String message)  {
        super(message);
    }
}
