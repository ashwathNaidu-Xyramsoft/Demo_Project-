package com.bridgelabz.bookstoreapp.service.userService;

import com.bridgelabz.bookstoreapp.entity.User;
import com.bridgelabz.bookstoreapp.entity.UserLogin;

public interface IUserLoginService {

    String checkLoginUserAccountByEmailAndPasswordAndRole(UserLogin userLogin);
    Long sendOTPToEmail(String email);
    Long verifyOTPFromEmail(long InputOtp);
    User changeExistingPasswordInDatabases(UserLogin userLogin);
}
