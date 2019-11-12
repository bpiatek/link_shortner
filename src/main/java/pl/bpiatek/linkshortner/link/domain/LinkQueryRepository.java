package pl.bpiatek.linkshortner.link.domain;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import pl.bpiatek.linkshortner.link.query.LinkQueryDTO;

import java.util.Optional;

/**
 * Created by Bartosz Piatek on 12/11/2019
 */
public interface LinkQueryRepository extends Repository<Link, Long> {

  @Query("SELECT NEW pl.bpiatek.linkshortner.link.query.LinkQueryDTO(l.id, l.originalUrl)"
         + "FROM Link l WHERE l.shortUrl = ?1")
  Optional<LinkQueryDTO> findByShortUrl(String shortLink);
}
