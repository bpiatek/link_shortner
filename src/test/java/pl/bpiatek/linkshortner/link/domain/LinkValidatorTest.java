package pl.bpiatek.linkshortner.link.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * Created by Bartosz Piatek on 30/10/2019
 */
@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
@RunWith(SpringRunner.class)
public class LinkValidatorTest {

  @Value(value = "#{'${list.to.trim}'.split(',')}")
  private List<String> stringList;
  private LinkValidator linkValidator;

  @Before
  public void setUp() {
    linkValidator = new LinkValidator();
  }

  @Test
  public void should_accept_link_starting_with_www() {
    // given
    String link = "www.test.pl";

    // when
    boolean isValid = linkValidator.isValid(link);

    // then
    assertThat(isValid).isTrue();
  }

  @Test
  public void should_accept_link_starting_with_http_and_www() {
    // given
    String link = "http://www.test.pl";

    // when
    boolean isValid = linkValidator.isValid(link);

    // then
    assertThat(isValid).isTrue();
  }

  @Test
  public void should_accept_link_starting_with_https_and_www() {
    // given
    String link = "https://www.test.pl";

    // when
    boolean isValid = linkValidator.isValid(link);

    // then
    assertThat(isValid).isTrue();
  }

  @Test
  public void should_accept_link_starting_with_http() {
    // given
    String link = "http://test.pl";

    // when
    boolean isValid = linkValidator.isValid(link);

    // then
    assertThat(isValid).isTrue();
  }

  @Test
  public void should_accept_link_starting_with_https() {
    // given
    String link = "https://test.pl";

    // when
    boolean isValid = linkValidator.isValid(link);

    // then
    assertThat(isValid).isTrue();
  }

  @Test
  public void should_trim_when_starts_with_localhost8080() {
    //given
    String shortLink = "localhost:8080/api/link/redirect/1234";

    //when
    String s = linkValidator.trimShortLink(shortLink, stringList);

    //then
    assertThat(s).isEqualTo("1234");
  }

  @Test
  public void should_trim_when_starts_with_http_localhost8080() {
    //given
    String shortLink = "http://localhost:8080/api/link/redirect/1234";

    //when
    String s = linkValidator.trimShortLink(shortLink, stringList);

    //then
    assertThat(s).isEqualTo("1234");
  }

  @Test
  public void should_trim_when_starts_with_http_www_localhost8080() {
    //given
    String shortLink = "http://www.localhost:8080/api/link/redirect/1234";

    //when
    String s = linkValidator.trimShortLink(shortLink, stringList);

    //then
    assertThat(s).isEqualTo("1234");
  }

  @Test
  public void should_trim_when_starts_with_www_localhost8080() {
    //given
    String shortLink = "www.localhost:8080/api/link/redirect/1234";

    //when
    String s = linkValidator.trimShortLink(shortLink, stringList);

    //then
    assertThat(s).isEqualTo("1234");
  }
}
