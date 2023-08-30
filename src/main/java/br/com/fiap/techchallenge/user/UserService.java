package br.com.fiap.techchallenge.user;

import br.com.fiap.techchallenge.exception.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserView findById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new NotFoundException("User id: %s not found.".formatted(id)));
        return new UserView(user);
    }

    public List<UserView> findAll() {
        Collection<User> users = userRepository.findAll();
        return users.stream().map(UserView::new).toList();
    }

    public UserView create(UserForm userForm) {
        User user = userRepository.save(userForm.toEntity());

        return new UserView(user);
    }

    public UserView update(UserForm userForm, Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new NotFoundException("User id: %s not found.".formatted(id)));
        user.update(userForm);

        return new UserView(user);
    }

    public void deleteById(Long id) {
        userRepository.findById(id).orElseThrow(() -> new NotFoundException("User id: %s not found.".formatted(id)));

        userRepository.deleteById(id);
    }
}
