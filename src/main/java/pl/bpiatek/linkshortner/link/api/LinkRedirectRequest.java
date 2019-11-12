package pl.bpiatek.linkshortner.link.api;

import lombok.*;

import javax.validation.constraints.NotEmpty;

/**
 * Created by Bartosz Piatek on 12/11/2019
 */
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class LinkRedirectRequest {

  @NotEmpty
  private String shortenedLink;
}
