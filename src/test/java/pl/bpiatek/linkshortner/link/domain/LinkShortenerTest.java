package pl.bpiatek.linkshortner.link.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by Bartosz Piatek on 18/11/2019
 */
@RunWith(MockitoJUnitRunner.class)
public class LinkShortenerTest {

  @Mock
  private LinkRepository linkRepository;

  private LinkShortener linkShortener;

  @Captor
  private ArgumentCaptor<String> captor;

  @Before
  public void setUp() {
    LinkValidator linkValidator = new LinkValidator();
    linkShortener = new LinkShortener(linkValidator, this.linkRepository);
  }

  @Test
  public void should_shorten_link_if_it_does_not_exist_in_database() {
    // given
    given(linkRepository.findByShortUrl(any())).willReturn(null);

    // when
    String shortenedLink = linkShortener.shortenLink("www.test.pl");

    // then
    verify(linkRepository).findByShortUrl(captor.capture());
    assertThat(shortenedLink).isEqualTo(captor.getValue());
  }
}

