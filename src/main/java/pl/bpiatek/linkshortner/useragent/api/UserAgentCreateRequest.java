package pl.bpiatek.linkshortner.useragent.api;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * Created by Bartosz Piatek on 31/10/2019
 */
@ToString
@Getter
@AllArgsConstructor
public class UserAgentCreateRequest {

  private String deviceName;
  private String deviceBrand;
  private String operatingSystemName;
  private String operatingSystemClass;
  private String agentName;
  private String language;
  private LocalDateTime clickDate;
  private Long linkId;
}
