package pl.bpiatek.linkshortner.link.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotEmpty;

/**
 * Created by Bartosz Piatek on 29/10/2019
 */
@Builder
@Getter
@AllArgsConstructor
public class LinkCreateRequest {
  @NotEmpty
  private String originalUrl;
}
