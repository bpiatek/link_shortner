package pl.bpiatek.linkshortner.useragent;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.bpiatek.linkshortner.useragent.api.UserAgentResponse;
import pl.bpiatek.linkshortner.useragent.domain.UserAgentFacade;

/**
 * Created by Bartosz Piatek on 20/12/2019
 */
@RestController
@RequestMapping(value = "api/useragent", produces = APPLICATION_JSON_VALUE)
class UseragentController {

  private final UserAgentFacade userAgentFacade;

  UseragentController(UserAgentFacade userAgentFacade) {
    this.userAgentFacade = userAgentFacade;
  }

  @GetMapping("all")
  Page<UserAgentResponse> findAll(Pageable pageable) {
    return userAgentFacade.all(pageable);
  }
}
