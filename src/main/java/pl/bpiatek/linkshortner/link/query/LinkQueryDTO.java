package pl.bpiatek.linkshortner.link.query;

import lombok.Value;

/**
 * Created by Bartosz Piatek on 12/11/2019
 */
@Value
public class LinkQueryDTO {

  private Long id;
  private String originalUrl;
}
