package js.study.study.restfulapi.controller;

import jakarta.validation.Valid;
import js.study.study.restfulapi.bean.Post;
import js.study.study.restfulapi.bean.ResponseData;
import js.study.study.restfulapi.bean.User;
import js.study.study.restfulapi.exception.UserNotFoundException;
import js.study.study.restfulapi.repository.PostRepository;
import js.study.study.restfulapi.repository.UserRepository;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/jpa")
public class UserJPAController {

    private UserRepository userRepository;
    private PostRepository postRepository;

    public UserJPAController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

//    @GetMapping("/users")
//    public List<User> retrieveAllUsers() {
//        return userRepository.findAll();
//    }
    @GetMapping("/users")
     public ResponseEntity retrieveAllUsers() {
        List<User> users = userRepository.findAll();

        ResponseData response = ResponseData.builder()
                .count(users == null || users.isEmpty() ? 0 : users.size())
                .users(users)
                .build();

        EntityModel entityModel = EntityModel.of(response);
        WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retrieveAllUsers());
        entityModel.add(linkTo.withSelfRel());

        return ResponseEntity.ok(entityModel);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity retrieveUser(@PathVariable int id) {
        Optional<User> user = userRepository.findById(id);

        if (!user.isPresent()) {
            throw new UserNotFoundException("id-" + id);
        }

        EntityModel entityModel = EntityModel.of(user.get());
        WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retrieveAllUsers());
        entityModel.add(linkTo.withRel("all-users"));

        return ResponseEntity.ok(entityModel);
    }

    @PostMapping("/users")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
        User savedUser = userRepository.save(user);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable int id) {
        userRepository.deleteById(id);
    }

    @GetMapping("/users/{id}/posts")
    public List<Post> retrieveAllPostsByUser(@PathVariable int id) {
        Optional<User> user = userRepository.findById(id);

        if (!user.isPresent()) {
            throw new UserNotFoundException("id-" + id);
        }

        return user.get().getPosts();
    }

    @PostMapping("/users/{id}/posts")
    public ResponseEntity createPost(@PathVariable int id, @Valid @RequestBody Post post) {
        Optional<User> userOptional = userRepository.findById(id);

        if (!userOptional.isPresent()) {
            throw new UserNotFoundException("id-" + id);
        }

        User user = userOptional.get();
        post.setUser(user);

        postRepository.save(post);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(post.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }
}
