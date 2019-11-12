package pl.bpiatek.linkshortner.user.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pl.bpiatek.linkshortner.user.api.UserResponse;

/**
 * Created by Bartosz Piatek on 04/11/2019
 */
public class UserFacade {

  private final UserPrincipalDetailsService userPrincipalDetailsService;
  private final PersistenceUserRepository userRepository;

  public UserFacade(
      UserPrincipalDetailsService userPrincipalDetailsService,
      PersistenceUserRepository userRepository
  ) {
    this.userPrincipalDetailsService = userPrincipalDetailsService;
    this.userRepository = userRepository;
  }

  public UserPrincipalDetailsService getUserPrincipalDetailsService() {
    return userPrincipalDetailsService;
  }

  public Page<UserResponse> getAllUsers(Pageable pageable) {
    return userRepository.findAll(pageable)
        .map(User::dto);
  }
}
