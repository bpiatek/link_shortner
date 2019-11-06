package pl.bpiatek.linkshortner.user.domain;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Created by Bartosz Piatek on 04/11/2019
 */
@Service
public class UserPrincipalDetailsService implements UserDetailsService {

  private PersistenceUserRepository userRepository;

  UserPrincipalDetailsService(
      PersistenceUserRepository userRepository
  ) {
    this.userRepository = userRepository;
  }

  @Override
  public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
    User user = userRepository.findByUsername(s);
    return new UserPrincipal(user);
  }
}
