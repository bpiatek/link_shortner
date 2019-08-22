package pl.bpiatek.linkshortner.link.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import pl.bpiatek.linkshortner.link.dto.LinkDto;

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
  private static final String LINK_BASE = "http://localhost:8080/";

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String originalUrl;
  private String shortUrl;
  @CreationTimestamp
  private LocalDateTime created;
  private LocalDateTime expiryDate;
  private Integer clicks;
  private boolean enabled;

  @PrePersist
  private void initialClicksValue() {
    this.clicks = 0;
    this.enabled = true;
    this.expiryDate = LocalDateTime.now().plusDays(7);
  }

  void updateClicks() {
    this.clicks++;
  }

  void enable() {
    this.enabled = true;
  }

  void disable() {
    this.enabled = false;
  }

  LinkDto dto() {
    return LinkDto.builder()
        .id(this.id)
        .originalUrl(this.originalUrl)
        .shortUrl(LINK_BASE + this.shortUrl)
        .created(this.created)
        .expiryDate(this.expiryDate)
        .clicks(this.clicks)
        .enabled(this.enabled)
        .build();
  }
}
