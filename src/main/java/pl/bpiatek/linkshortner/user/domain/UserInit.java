package pl.bpiatek.linkshortner.user.domain;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Created by Bartosz Piatek on 04/11/2019
 */
@Component
class UserInit {

  private PersistenceUserRepository userRepository;
  private PasswordEncoder passwordEncoder;

  UserInit(
      PersistenceUserRepository userRepository,
      PasswordEncoder passwordEncoder
  ) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
  }

  @PostConstruct
  void init() {
    userRepository.save(new User("admin", passwordEncoder.encode("pass"), "ADMIN", ""));
    userRepository.save(new User("user", passwordEncoder.encode("pass"), "USER", ""));
  }
}
