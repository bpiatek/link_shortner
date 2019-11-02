package pl.bpiatek.linkshortner.infrastructure.events;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import pl.bpiatek.linkshortner.useragent.api.UserAgentCreateRequest;
import pl.bpiatek.linkshortner.useragent.domain.UserAgentFacade;

/**
 * Created by Bartosz Piatek on 31/10/2019
 */
@Slf4j
@Component
class DomainEventListener {

  private UserAgentFacade userAgentFacade;

  public DomainEventListener(UserAgentFacade userAgentFacade) {
    this.userAgentFacade = userAgentFacade;
  }

  @EventListener
  public void receiveTestEvent(DomainEvent domainEvent) {

    if(domainEvent.getType().equals("USER_AGENT")) {
      userAgentFacade.save((UserAgentCreateRequest) domainEvent.getPayload());
    } else {
      log.info("I don't know what to do with: {} event", domainEvent.getType());
    }
  }
}
