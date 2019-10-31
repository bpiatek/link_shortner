package pl.bpiatek.linkshortner.useragent.api;

import pl.bpiatek.linkshortner.infrastructure.events.DomainEvent;

/**
 * Created by Bartosz Piatek on 31/10/2019
 */
public class UserAgentCreateEvent extends DomainEvent {

  private static final String EVENT_TYPE = "USER_AGENT";

  public UserAgentCreateEvent(UserAgentCreateRequest userAgentCreateRequest) {
    super(EVENT_TYPE, userAgentCreateRequest);
  }
}
