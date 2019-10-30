package pl.bpiatek.linkshortner.link.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import pl.bpiatek.linkshortner.link.dto.LinkResponse;

import java.time.LocalDateTime;

import javax.persistence.*;

/**
 * Created by Bartosz Piatek on 05/08/2019
 */
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
class Link {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String originalUrl;
  private String shortUrl;
  @CreationTimestamp
  private LocalDateTime created;
  private LocalDateTime expiryDate;
  private Integer clicks;
  private boolean enabled = true;

  @PrePersist
  private void initialClicksValue() {
    this.clicks = 0;
    this.expiryDate = LocalDateTime.now().plusDays(7);
    enable();
  }

  void updateClicks() {
    this.clicks++;
  }

  private void enable() {
    this.enabled = true;
  }

  void disable() {
    this.enabled = false;
  }

  LinkResponse dto(String linkBase) {
    return LinkResponse.builder()
        .id(this.id)
        .originalUrl(this.originalUrl)
        .shortUrl(linkBase + this.shortUrl)
        .created(this.created)
        .expiryDate(this.expiryDate)
        .clicks(this.clicks)
        .enabled(this.enabled)
        .build();
  }
}
