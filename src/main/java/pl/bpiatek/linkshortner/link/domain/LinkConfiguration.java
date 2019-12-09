package pl.bpiatek.linkshortner.link.domain;

import nl.basjes.parse.useragent.UserAgentAnalyzer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.bpiatek.linkshortner.infrastructure.configuration.TestValues;

import java.util.List;

/**
 * Created by Bartosz Piatek on 05/08/2019
 */
@Configuration
class LinkConfiguration {

  LinkFacade linkFacade(
      ApplicationEventPublisher applicationEventPublisher,
      UserAgentAnalyzer userAgentAnalyzer
  ) {
    TestValues testValues = new TestValues();
    return linkFacade(
        testValues.getLinkBase(),
        testValues.getStringList(),
        new InMemoryLinkRepository(),
        applicationEventPublisher,
        userAgentAnalyzer
    );
  }

  @Bean
  LinkFacade linkFacade(
      @Value("${link.base}") String linkBase,
      @Value(value = "#{'${list.to.trim}'.split(',')}") List<String> stringList,
      LinkRepository repository,
      ApplicationEventPublisher applicationEventPublisher,
      UserAgentAnalyzer userAgentAnalyzer
  ) {
    LinkValidator linkValidator = new LinkValidator();
    UserAgentParser userAgentParser = new UserAgentParser(userAgentAnalyzer);
    LinkShortener linkShortener = new LinkShortener(linkValidator, repository);
    LinkCreator linkCreator = new LinkCreator(linkShortener);
    return new LinkFacade(
        linkBase,
        stringList,
        repository,
        linkCreator,
        applicationEventPublisher,
        userAgentParser,
        linkValidator
    );
  }
}
