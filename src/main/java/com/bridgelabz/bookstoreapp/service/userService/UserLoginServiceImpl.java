package com.bridgelabz.bookstoreapp.service.userService;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.Verification;
import com.bridgelabz.bookstoreapp.entity.User;
import com.bridgelabz.bookstoreapp.entity.UserLogin;
import com.bridgelabz.bookstoreapp.exception.BookStoreException;
import com.bridgelabz.bookstoreapp.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;
import java.util.Random;

@Service
@Slf4j
public class UserLoginServiceImpl implements IUserLoginService{

    public static String JWT_TOKEN ;
    public static Object PRINCIPLE ;
    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public String checkLoginUserAccountByEmailAndPasswordAndRole(UserLogin userLogin) {
        String email = userLogin.getEmail();
        String password = userLogin.getPassword();
        String userLoginRole = userLogin.getRole();
        // Authenticate TOKEN
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(email,password);
        log.info("email : {}",email);
        log.info("password : {}",password);
        log.info("AuthenticationToken : {}",authenticationToken);
        User encodePasswordByEmail = userRepository.getEncodePasswordByEmail(email);
        boolean matches = passwordEncoder.matches(password,encodePasswordByEmail.getPassword());
        if (matches==true){
            log.info("Authentication status for email ID : {}: Verified ",email);
            PRINCIPLE = authenticationToken.getPrincipal(); // subject in token
            log.info("principal --> {}",PRINCIPLE);
            String jwtToken = createJWTToken(PRINCIPLE);// JWT TOKEN
            log.info("JWT Token is : {}",jwtToken);
            JWT_TOKEN = jwtToken;
            return  jwtToken;// JWT TOKEN
        }
        log.info("Authentication status for email ID : {}: Not Verified ",email);
        log.info("Password is mismatch, Enter the valid password");
        throw new BookStoreException("Password is mismatch, Enter the valid password");
    }

    String createJWTToken(Object principal){
        Algorithm algorithm = Algorithm.HMAC256("secret_token".getBytes());
        String generated_token = JWT.create().withIssuedAt(new Date(System.currentTimeMillis()))
                                .withIssuer("http://localhost:8080/address-book/Login")
                                .withSubject(principal.toString())
                                .withExpiresAt(new Date(System.currentTimeMillis() + 10 * 60 * 1000)).sign(algorithm);
        return generated_token;
    }

    // Decoding the token
    public static String findSubByDecodeToken(String jwtToken){
        // to be corrected
        String SubByDecodeToken = JWT.require(Algorithm.HMAC256("secret_token".getBytes()))
                                .build()
                                .verify(jwtToken)
                                .getSubject();
        return SubByDecodeToken;
    }


    public static String verifyToken(String token){
        String JWT_TOKEN = UserLoginServiceImpl.JWT_TOKEN;
        String actualSubject = UserLoginServiceImpl.findSubByDecodeToken(JWT_TOKEN);
        String receivedSubject = UserLoginServiceImpl.findSubByDecodeToken(token);
        if(actualSubject.equals(receivedSubject)){
            return actualSubject;
        }
        throw new BookStoreException("Enter the valid token !!");
    }

    static Random random = new Random();
    static long OTP = random.nextInt(999999); // Generating OTP

    @Override
    public Long sendOTPToEmail(String email) {
        String subject="OTP from Boot Store";
        String message = "your OTP is "+OTP;
        String toEmail = email;
        String fromEmail= "ashwath.naidu@bridgelabz.com";
        sendEmail(subject, message,fromEmail,toEmail);
        return OTP;
    }

    @Override
    public Long verifyOTPFromEmail(long InputOtp) {
        if(InputOtp==OTP){
            log.info("OTP successfully verified");
            return InputOtp;
        }
        log.error("OTP not verified, Enter valid OTP (One Time Password)");
        throw new BookStoreException("OTP not verified, Enter valid OTP (One Time Password)");
    }

    @Override
    public User changeExistingPasswordInDatabases(UserLogin userLogin) {

        User emailIdByEmail = userRepository.getEmailIdByEmail(userLogin.getEmail());
        if(emailIdByEmail.getEmail().equals(userLogin.getEmail())){
            log.info("Existing encode password in database -> {}",emailIdByEmail.getPassword());
            String encode = passwordEncoder.encode(userLogin.getPassword());
            emailIdByEmail.setPassword(encode); // setting new encode password
            log.info("New encode password set in database -> {}",emailIdByEmail.getPassword());
            log.info("{}",emailIdByEmail);
            userRepository.save(emailIdByEmail);
            return emailIdByEmail;
        }
        // boolean upgradeEncoding = passwordEncoder.upgradeEncoding(existingUser.getPassword());
        log.error("Enter the valid email ID");
        throw new BookStoreException("Enter the valid email ID");
    }

    private void sendEmail(String subject, String message, String fromEmail, String toEmail) {
        Properties properties = System.getProperties(); // system properties
        System.out.println(properties);
        // set the host properties
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587"); // 465
        properties.put("mail.smtp.auth", true);
        properties.put("mail.smtp.starttls.enable", "true");
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, "Chanti_0704");
            }
        });
        session.setDebug(true);
        MimeMessage mimeMessage = new MimeMessage(session);
        try {
            mimeMessage.setFrom(new InternetAddress(fromEmail));// need to change
            mimeMessage.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail)); // to email
            mimeMessage.setSubject(subject); // setting subject
            mimeMessage.setText(message); // setting message
            Transport.send(mimeMessage);
            log.info("Email sent successFully");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

}
