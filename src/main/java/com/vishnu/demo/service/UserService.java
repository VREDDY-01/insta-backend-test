package com.vishnu.demo.service;




import com.vishnu.demo.model.AuthenticationToken;
import com.vishnu.demo.model.Post;
import com.vishnu.demo.model.User;
import com.vishnu.demo.repository.IUserRepo;
import com.vishnu.demo.service.emailUtility.EmailHandler;
import com.vishnu.demo.service.hashingUtility.PasswordEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.util.List;

@Service
public class UserService {

    @Autowired
    IUserRepo userRepo;

    @Autowired
    AuthenticationService authenticationService;

    @Autowired
    PostService postService;

    @Autowired
    CommentService commentService;

    @Autowired
    LikeService likeService;

    @Autowired
    FollowService followService;


    public String userSignUp(User newUser) {

        //check if already exist -> Not allowed : try logging in

        String newEmail = newUser.getUserEmail();

        User existingUser = userRepo.findFirstByUserEmail(newEmail);

        if(existingUser != null)
        {
            return "email already in use";
        }

        // passwords are encrypted before we store it in the table

        String signUpPassword = newUser.getUserPassword();

        try {
            String encryptedPassword = PasswordEncryptor.encrypt(signUpPassword);

            newUser.setUserPassword(encryptedPassword);


            // patient table - save patient
            userRepo.save(newUser);
            return "Insta user registered";

        } catch (NoSuchAlgorithmException e) {

            return "Internal Server issues while saving password, try again later!!!";
        }

    }


    public String userSignIn(String email, String password) {

        //check if the email is there in your tables
        //sign in only possible if this person ever signed up


        User existingUser = userRepo.findFirstByUserEmail(email);

        if(existingUser == null)
        {
            return "Not a valid email, Please sign up first !!!";
        }

        //password should be matched

        try {
            String encryptedPassword = PasswordEncryptor.encrypt(password);

            if(existingUser.getUserPassword().equals(encryptedPassword))
            {
                // return a token for this sign in
                AuthenticationToken token  = new AuthenticationToken(existingUser);
                authenticationService.createToken(token);
                return token.getTokenValue();

            }
            else {
                //password was wrong!!!
                return "Invalid Credentials!!!";
            }

        } catch (NoSuchAlgorithmException e) {

            return "Internal Server issues while saving password, try again later!!!";
        }
    }


    public String createInstaPost(String email, String tokenValue, Post instaPost) {

        if(authenticationService.authenticate(email,tokenValue)) {

            User existingUser = userRepo.findFirstByUserEmail(email);
            instaPost.setPostOwner(existingUser);

            postService.createInstaPost(instaPost);
            return instaPost.getPostType() + " posted!!";

        }
        else {
            return "Un Authenticated access!!!";
        }
    }

    public List<Post> getInstaPosts(String email, String tokenValue) {
        if(authenticationService.authenticate(email,tokenValue)) {

            User existingUser = userRepo.findFirstByUserEmail(email);
            return postService.getPosts();

        }
        else {
            return null;
        }
    }

    public String updateUser(String email, String tokenValue, User user) throws NoSuchAlgorithmException {
        if(authenticationService.authenticate(email,tokenValue)) {

            User existingUser = userRepo.findFirstByUserEmail(email);
            existingUser.setUserBio(user.getUserBio());
            existingUser.setUserHandle(user.getUserHandle());
            existingUser.setUserName(user.getUserName());
            existingUser.setGender(user.getGender());
            String encryptedPassword = PasswordEncryptor.encrypt(user.getUserPassword());

            existingUser.setUserPassword(encryptedPassword);

            userRepo.save(existingUser);
            return "updated user";

        }
        else {
            return "No access";
        }
    }
}
