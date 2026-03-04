package kinoteka.controller;

import kinoteka.model.User;
import kinoteka.model.UserDto;
import kinoteka.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody UserDto dto) {
        return ResponseEntity.ok(service.register(dto));
    }

    @GetMapping("/me")
    public ResponseEntity<User> me(Authentication auth) {
        return service.findByUsername(auth.getName())
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<User>> all() {
        return ResponseEntity.ok(service.findAll());
    }
}
