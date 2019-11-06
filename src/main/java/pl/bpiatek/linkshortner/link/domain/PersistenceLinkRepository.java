package pl.bpiatek.linkshortner.link.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import pl.bpiatek.linkshortner.link.api.LinkNotFoundException;

/**
 * Created by Bartosz Piatek on 05/08/2019
 */
interface PersistenceLinkRepository extends Repository<Link, Long> {

  Link save(Link link);
  Link findById(Long id);
  Page<Link> findAll(Pageable pageable);

  default Link findOneOrThrow(Long id) {
    Link link = findById(id);
    if(link == null) {
      throw new LinkNotFoundException(id);
    }
    return link;
  }

  Link findByShortUrl(String url);

  default Link findByShortUrlOrThrow(String url) {
    Link byShortUrl = findByShortUrl(url);
    if(byShortUrl == null) {
      throw new LinkNotFoundException(url);
    }

    return byShortUrl;
  }

  @Modifying
  @Query("UPDATE Link l SET l.enabled = false WHERE l.expiryDate < CURRENT_DATE")
  int setEnabledToFalse();
}