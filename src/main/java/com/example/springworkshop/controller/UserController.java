package com.example.springworkshop.controller;

import com.example.springworkshop.controller.DTO.CreateUserReq;
import com.example.springworkshop.model.User;
import com.example.springworkshop.model.UserSession;
import com.example.springworkshop.service.UserService;
import com.example.springworkshop.service.UserSessionService;

import jakarta.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/user")
public class UserController {

    private final UserSessionService userSessionService;
    private final UserService userService;

    public UserController(UserService userService, UserSessionService userSessionService) {
        this.userService = userService;
        this.userSessionService = userSessionService;
    }

    @PostMapping("/register")
    public ResponseEntity<Void> createUserEntity(@RequestBody CreateUserReq createUserReq) {
        userService.createUser(createUserReq.name(), createUserReq.password());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUserEntity(@RequestBody CreateUserReq createUserReq) {
        User user = userService.loginUser(createUserReq.name(), createUserReq.password());

        UserSession userSession = userSessionService.createSession(user.getUserId());

        ResponseCookie cookie = ResponseCookie.from("auth", userSession.getUserSession())
                .httpOnly(true)
                .secure(false)
                .path("/")
                .build();

        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, cookie.toString())
                .body("Login successful");
    }
    @GetMapping("/logout")
    public ResponseEntity<?> logout(@CookieValue(value="auth") String userSession,
                                    HttpServletResponse response) {
        userSessionService.deleteSession(userSession);
        response.setHeader("Clear-Site-Data", "\"cookies\", \"cache\", \"storage\"");
        return ResponseEntity.noContent().build();
    }
}