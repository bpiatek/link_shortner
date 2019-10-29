package pl.bpiatek.linkshortner.link.domain;

import static java.util.Objects.requireNonNull;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import pl.bpiatek.linkshortner.link.dto.LinkCreateRequest;
import pl.bpiatek.linkshortner.link.dto.LinkResponse;

import java.time.LocalDateTime;

/**
 * Created by Bartosz Piatek on 05/08/2019
 */
@Transactional
public class LinkFacade {

  @Value("${link.base}")
  private String linkBase;
  private LinkRepository linkRepository;
  private LinkCreator linkCreator;

  public LinkFacade(LinkRepository linkRepository, LinkCreator linkCreator) {
    this.linkRepository = linkRepository;
    this.linkCreator = linkCreator;
  }

  public LinkResponse show(Long id) {
    requireNonNull(id);
    Link link = linkRepository.findOneOrThrow(id);

    return link.dto(linkBase);
  }

  public LinkResponse add(LinkCreateRequest linkCreateRequest) {
    requireNonNull(linkCreateRequest);
    Link link = linkCreator.from(linkCreateRequest);
    link = linkRepository.save(link);

    return link.dto(linkBase);
  }

  public Page<LinkResponse> findAll(Pageable pageable) {
    return linkRepository
        .findAll(pageable)
        .map(l -> l.dto(linkBase));
  }

  public LinkResponse findByShortLink(String shortLink) {
    requireNonNull(shortLink);
    Link shortUrl = linkRepository.findByShortUrlOrThrow(shortLink);
//    Link updateCount = updateCount(shortUrl);

    Link save = linkRepository.save(shortUrl);

    return save.dto(linkBase);
  }

  public void removeExpiredLinks() {
    linkRepository.removeAllByExpiryDateBefore(LocalDateTime.now());
  }

//  private Link updateCount(Link link) {
//    link.updateClicks();
//    return linkRepository.save(link);
//  }
}
