package com.sscarlett.big_ambitions_companion.service;

import com.sscarlett.big_ambitions_companion.dao.UserDao;
import com.sscarlett.big_ambitions_companion.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    /**
     * adds a new user to the app
     *
     * @param users user info
     * @return user id
     */
    @Override
    public Integer postNewUser(Users users) {
        Integer newId = userDao.selectMaxId();
        users.setUserId(newId);
        userDao.insertNewUser(users);
        return newId;
    }

    /**
     * checks if valid user and if so logs in
     *
     * @param users user info
     * @return user id
     */
    @Override
    public Integer loginUser(Users users) {
        Boolean isPasswordValid = userDao.verifyPassword(users);
        if (!Boolean.TRUE.equals(isPasswordValid)) {
            throw new IllegalArgumentException("Invalid password");
        }

        return userDao.selectUserIdByLogin(users);
    }

    /**
     * selects a user and their info
     *
     * @param userId user id
     * @return user info
     */
    @Override
    public Users getUserInfo(Integer userId) {
        return userDao.selectUserById(userId);
    }

    /**
     * update user's name and email
     *
     * @param users user info
     */
    @Override
    public void patchUser(Users users) {
        userDao.updateUser(users);
    }

    /**
     * allows user to reset their password
     *
     * @param users user info
     */
    @Override
    public void resetPassword(Users users) {
        userDao.updatePassword(users);
    }
}
