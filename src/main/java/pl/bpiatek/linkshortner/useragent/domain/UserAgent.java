package pl.bpiatek.linkshortner.useragent.domain;

import lombok.*;
import pl.bpiatek.linkshortner.useragent.api.UserAgentResponse;

import java.time.LocalDateTime;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by Bartosz Piatek on 30/10/2019
 */
@ToString
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
class UserAgent {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String deviceName;
  private String deviceBrand;
  private String operatingSystemName;
  private String operatingSystemClass;
  private String agentName;
  private String language;
  private LocalDateTime clickDate;
  @NotNull
  private Long linkId;

  UserAgentResponse dto() {
    return UserAgentResponse.builder()
        .id(this.id)
        .agentName(this.agentName)
        .clickDate(this.clickDate)
        .deviceBrand(this.deviceBrand)
        .deviceName(this.deviceName)
        .language(this.language)
        .operatingSystemClass(this.operatingSystemClass)
        .operatingSystemName(this.operatingSystemName)
        .linkId(this.linkId)
        .build();
  }
}
