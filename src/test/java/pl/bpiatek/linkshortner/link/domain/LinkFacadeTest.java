package pl.bpiatek.linkshortner.link.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import nl.basjes.parse.useragent.UserAgentAnalyzer;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationEventPublisher;
import pl.bpiatek.linkshortner.link.api.LinkCreateRequest;
import pl.bpiatek.linkshortner.link.api.LinkNotFoundException;
import pl.bpiatek.linkshortner.link.api.LinkResponse;

/**
 * Created by Bartosz Piatek on 18/11/2019
 */
@SpringBootTest
public class LinkFacadeTest {


  @Autowired
  ApplicationEventPublisher applicationEventPublisher;

  @Autowired
  UserAgentAnalyzer userAgentAnalyzer;

  LinkFacade linkFacade = new LinkConfiguration().linkFacade(applicationEventPublisher, userAgentAnalyzer);

  @Test
  public void should_show_link_existing_in_database() {
    // given
    linkFacade.add(new LinkCreateRequest("www.test.pl"));

    // when
    LinkResponse show = linkFacade.show(1L);

    // then
    assertThat(show.getOriginalUrl()).contains("www.test.pl");
  }

  @Test
  public void should_throw_exception_when_link_is_not_found() {
    // given
    // when
    // then
    assertThatExceptionOfType(LinkNotFoundException.class)
        .isThrownBy(() -> {
          linkFacade.show(1L);
        });
  }

  @Test
  public void should_find_link_by_short_link() {
    // given
    String shortUrl = linkFacade.add(new LinkCreateRequest("www.test.pl")).getShortUrl();

    // when
    LinkResponse linkResponse = linkFacade.findByShortLink(shortUrl);

    // then
    assertThat(linkResponse.getShortUrl()).isEqualTo(shortUrl);
  }
}
