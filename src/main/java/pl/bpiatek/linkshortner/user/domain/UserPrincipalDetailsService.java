package pl.bpiatek.linkshortner.user.domain;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Created by Bartosz Piatek on 04/11/2019
 */
@Service
class UserPrincipalDetailsService implements UserDetailsService {

  private PersistenceUserRepository userRepository;

  UserPrincipalDetailsService(PersistenceUserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

    return new UserPrincipal(userRepository.findByUsername(s).orElseThrow(
        () -> new UsernameNotFoundException("No user found with username: " + s)
    ));
  }
}
