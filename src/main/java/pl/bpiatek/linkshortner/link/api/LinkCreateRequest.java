package pl.bpiatek.linkshortner.link.api;

import lombok.*;

import javax.validation.constraints.NotEmpty;

/**
 * Created by Bartosz Piatek on 29/10/2019
 */
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class LinkCreateRequest {
  @NotEmpty
  private String originalUrl;
}
