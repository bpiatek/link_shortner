package pl.bpiatek.linkshortner.infrastructure.security;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

/**
 * Created by Bartosz Piatek on 07/11/2019
 */
@RestController
@RequestMapping
@ApiIgnore
class LoginController {

  @PostMapping("logout")
  ResponseEntity<Void> logout() {
    return ResponseEntity.ok().build();
  }

  /**
   * This is left just for swagger as all authentication is handled by spring security.
   * @param authenticationData
   * @return
   */
  @PostMapping("login")
  ResponseEntity<Void> login(@RequestBody AuthenticationData authenticationData) {
    return ResponseEntity.ok().build();
  }
}
