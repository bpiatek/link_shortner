package pl.bpiatek.linkshortner.link.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

/**
 * Created by Bartosz Piatek on 30/10/2019
 */
public class LinkValidatorTest {


  @Test
  public void should_accept_link() {
    LinkValidator linkValidator = new LinkValidator();
    // given
    String link = "www.wp.pl";

    // when
    boolean isValid = linkValidator.isValid(link);

    // then
    assertThat(isValid).isTrue();
  }

}
