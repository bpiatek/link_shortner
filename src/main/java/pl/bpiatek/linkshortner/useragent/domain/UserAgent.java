package pl.bpiatek.linkshortner.useragent.domain;

import lombok.*;

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
  private LocalDateTime clickDate;
  @NotNull
  private Long linkId;
}
