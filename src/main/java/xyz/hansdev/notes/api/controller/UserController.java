package xyz.hansdev.notes.api.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import xyz.hansdev.notes.api.exception.ResourceNotFoundException;
import xyz.hansdev.notes.api.model.User;
import xyz.hansdev.notes.api.service.UserService;

import java.util.List;

@RestController
@RequestMapping(value = "/users/")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) { this.userService = userService; }

    @ResponseBody
    public List<User> findPaginated(@PageableDefault(direction = Sort.Direction.DESC, sort = "id") Pageable pageable) {

        Page<User> result = userService.findPaginated(pageable);
        if(pageable.getPageNumber() > result.getTotalPages()) {
            throw new ResourceNotFoundException();
        }

        return result.getContent();
    }

    @GetMapping(value = "{id}")
    public User findOne(@PathVariable("id") Integer id) {
        return userService.findOne(id);
    }

    @PostMapping
    public User create(@PathVariable("user") User user) {
        return userService.create(user);
    }

    @PutMapping(value = "{id}")
    public User update(@PathVariable("id") Integer id, User user) {
        return userService.update(user);
    }

    @DeleteMapping(value = "{id}")
    public void delete(@PathVariable("id") Integer id) {
        userService.delete(id);
    }
}
