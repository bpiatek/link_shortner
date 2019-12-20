package pl.bpiatek.linkshortner.useragent.api;

import lombok.*;

import java.time.LocalDateTime;

/**
 * Created by Bartosz Piatek on 20/12/2019
 */
@ToString
@Builder
@Getter
public class UserAgentResponse {

  private Long id;
  private String deviceName;
  private String deviceBrand;
  private String operatingSystemName;
  private String operatingSystemClass;
  private String agentName;
  private String language;
  private LocalDateTime clickDate;
  private Long linkId;
}
