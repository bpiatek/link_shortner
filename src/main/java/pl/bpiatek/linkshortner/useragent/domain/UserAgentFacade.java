package pl.bpiatek.linkshortner.useragent.domain;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pl.bpiatek.linkshortner.useragent.api.UserAgentCreateRequest;
import pl.bpiatek.linkshortner.useragent.api.UserAgentResponse;

/**
 * Created by Bartosz Piatek on 31/10/2019
 */
@Slf4j
public class UserAgentFacade {

  private UserAgentRepository userAgentRepository;
  private UserAgentCreator userAgentCreator;

  public UserAgentFacade(
      UserAgentRepository userAgentRepository,
      UserAgentCreator userAgentCreator
  ) {
    this.userAgentRepository = userAgentRepository;
    this.userAgentCreator = userAgentCreator;
  }

  public void save(UserAgentCreateRequest userAgentCreateRequest) {
    UserAgent userAgent = userAgentCreator.from(userAgentCreateRequest);
    UserAgent save = userAgentRepository.save(userAgent);
    log.info("User agent {} saved for link: {}", save, userAgentCreateRequest.getLinkId());
  }

  public Page<UserAgentResponse> all(Pageable pageable) {
    return userAgentRepository.findAll(pageable).map(UserAgent::dto);
  }
}
