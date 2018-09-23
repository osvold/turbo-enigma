package xyz.hansdev.notes.api.service.implementations;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import xyz.hansdev.notes.api.exception.ResourceNotFoundException;
import xyz.hansdev.notes.api.model.User;
import xyz.hansdev.notes.api.repository.UserRepository;
import xyz.hansdev.notes.api.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Page<User> findPaginated(Pageable pageable) {
        return userRepository.findPaginated(pageable);
    }

    @Override
    public User findOne(Integer id) {
        return userRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public User create(User user) {
        return userRepository.save(user);
    }

    @Override
    public User update(User user) {
        User original = userRepository.findById(user.getId()).orElseThrow(ResourceNotFoundException::new);
        original.setUsername(user.getUsername());
        return userRepository.save(original);
    }

    @Override
    public void delete(Integer id) {
        User user = userRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
        userRepository.delete(user);
    }
}
