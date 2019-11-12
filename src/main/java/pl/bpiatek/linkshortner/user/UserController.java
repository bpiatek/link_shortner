package pl.bpiatek.linkshortner.user;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.bpiatek.linkshortner.user.api.UserResponse;
import pl.bpiatek.linkshortner.user.domain.UserFacade;

/**
 * Created by Bartosz Piatek on 07/11/2019
 */
@RestController
@RequestMapping(value = "api", produces = APPLICATION_JSON_VALUE)
class UserController {

  private final UserFacade userFacade;

  public UserController(UserFacade userFacade) {
    this.userFacade = userFacade;
  }

  @GetMapping("users")
  Page<UserResponse> users(Pageable pageable) {
    return userFacade.getAllUsers(pageable);
  }
}
