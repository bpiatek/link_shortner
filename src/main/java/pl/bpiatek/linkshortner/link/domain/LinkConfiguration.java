package pl.bpiatek.linkshortner.link.domain;

import nl.basjes.parse.useragent.UserAgentAnalyzer;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Bartosz Piatek on 05/08/2019
 */
@Configuration
class LinkConfiguration {

  @Bean
  LinkFacade linkFacade(
      PersistenceLinkRepository repository,
      ApplicationEventPublisher applicationEventPublisher,
      UserAgentAnalyzer userAgentAnalyzer
  ) {
    LinkValidator linkValidator = new LinkValidator();
    UserAgentParser userAgentParser = new UserAgentParser(userAgentAnalyzer);
    LinkShortener linkShortener = new LinkShortener(linkValidator, repository);
    LinkCreator linkCreator = new LinkCreator(linkShortener);
    return new LinkFacade(repository, linkCreator, applicationEventPublisher, userAgentParser);
  }
}
