package pl.bpiatek.linkshortner.link.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Bartosz Piatek on 05/08/2019
 */
@Configuration
class LinkConfiguration {

//  LinkFacade linkFacade() {
//    return linkFacade(new DatabaseRepository());
//  }

  @Bean
  LinkFacade linkFacade(LinkRepository repository, LinkShortener linkShortener) {
    LinkCreator linkCreator = new LinkCreator(linkShortener);
    return new LinkFacade(repository, linkCreator);
  }
}
