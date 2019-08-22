package pl.bpiatek.linkshortner.link.dto;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.time.LocalDateTime;

import javax.validation.constraints.NotEmpty;

/**
 * Created by Bartosz Piatek on 05/08/2019
 */
@Builder
@Getter
@EqualsAndHashCode
public class LinkDto {
  private Long id;
  @NotEmpty
  private String originalUrl;
  private String shortUrl;
  private LocalDateTime created;
  private LocalDateTime expiryDate;
  private Integer clicks;
  private boolean enabled;
}
