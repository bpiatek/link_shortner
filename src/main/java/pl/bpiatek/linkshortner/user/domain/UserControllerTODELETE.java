package pl.bpiatek.linkshortner.user.domain;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Bartosz Piatek on 04/11/2019
 */
@RestController
@RequestMapping("api/user")
class UserControllerTODELETE {

  private final PersistenceUserRepository userRepository;

  public UserControllerTODELETE(PersistenceUserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @GetMapping("all")
  List<User> allUsers() {
    return userRepository.findAll();
  }
}
