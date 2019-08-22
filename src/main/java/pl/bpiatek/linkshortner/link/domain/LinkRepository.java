package pl.bpiatek.linkshortner.link.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;
import pl.bpiatek.linkshortner.link.dto.LinkNotFoundException;

/**
 * Created by Bartosz Piatek on 05/08/2019
 */
interface LinkRepository extends Repository<Link, Long> {
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
}
