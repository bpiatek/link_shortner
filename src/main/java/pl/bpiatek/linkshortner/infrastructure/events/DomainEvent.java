package pl.bpiatek.linkshortner.infrastructure.events;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.context.ApplicationEvent;

/**
 * Created by Bartosz Piatek on 31/10/2019
 */
@JsonIgnoreProperties({"payload"})
public class DomainEvent extends ApplicationEvent {

  private final String type;

  public DomainEvent(String type, Object payload) {
    super(payload);
    this.type = type;
  }

  String getType() {
    return type;
  }

  Object getPayload() {
    return source;
  }

}
