package pl.bpiatek.linkshortner.infrastructure.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import pl.bpiatek.linkshortner.link.domain.LinkFacade;

/**
 * Created by Bartosz Piatek on 22/08/2019
 */
@Slf4j
@Component
class ScheduledTasks {

  private final LinkFacade linkFacade;

  ScheduledTasks(LinkFacade linkFacade) {
    this.linkFacade = linkFacade;
  }


  @Scheduled(cron = "${cron.remove-expired-links}")
  void remove() {
    log.info("Disabling expired links...");
    int count = linkFacade.markExpired();
    log.info("{} links disabled", count);
  }
}
