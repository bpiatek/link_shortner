package pl.bpiatek.linkshortner.link.domain;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import pl.bpiatek.linkshortner.link.api.LinkResponse;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by Bartosz Piatek on 06/12/2019
 */
@Slf4j
class InMemoryLinkRepository implements LinkRepository {

  private String linkBase = "";

  private ConcurrentHashMap<Long, Link> links = new ConcurrentHashMap<>();
  private static final AtomicLong ID = new AtomicLong(1);

  @Override
  public Link save(Link link) {
    long id = ID.getAndIncrement();
    LinkResponse dto = link.dto(linkBase);
    Link linkToBeSaved = new Link(
        id,
        dto.getOriginalUrl(),
        dto.getShortUrl(),
        dto.getCreated(),
        dto.getExpiryDate(),
        dto.getClicks(),
        dto.isEnabled()
    );

    links.put(id, linkToBeSaved);
    return linkToBeSaved;
  }

  @Override
  public Link findById(Long id) {
    return links.get(id);
  }

  @Override
  public Page<Link> findAll(Pageable pageable) {
    return new PageImpl<>(new ArrayList<>(links.values()), pageable, links.size());
  }

  @Override
  public Link findByShortUrl(String url) {
    Link returnLink = null;
    for (Link link : links.values()) {
      if(link.dto(linkBase).getShortUrl().contains(url)) {
        returnLink = link;
      }
    }

    return returnLink;
  }

  @Override
  public int setEnabledToFalse() {
    throw new RuntimeException("Not implemented");
  }
}
