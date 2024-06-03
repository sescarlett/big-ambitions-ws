package com.sscarlett.big_ambitions_companion.service;

import com.sscarlett.big_ambitions_companion.model.Users;

public interface UserService {

    /**
     * adds a new user to the app
     * @param users user info
     * @return user id
     */
    Integer postNewUser(Users users);

    /**
     * checks if valid user and if so logs in
     * @param users user info
     * @return user id
     */
    Integer loginUser(Users users);

    /**
     * selects a user and their info
     * @param userId user id
     * @return user info
     */
    Users getUserInfo(Integer userId);

    /**
     * update user's name and email
     * @param users user info
     */
    void patchUser(Users users);

    /**
     * allows user to reset their password
     * @param users user info
     */
    void resetPassword(Users users);
}
