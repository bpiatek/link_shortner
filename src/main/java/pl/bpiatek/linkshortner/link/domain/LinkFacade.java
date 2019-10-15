package pl.bpiatek.linkshortner.link.domain;

import static java.util.Objects.requireNonNull;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import pl.bpiatek.linkshortner.link.dto.LinkDto;

import java.time.LocalDateTime;

/**
 * Created by Bartosz Piatek on 05/08/2019
 */
@Transactional
public class LinkFacade {

  private LinkRepository linkRepository;
  private LinkCreator linkCreator;

  public LinkFacade(LinkRepository linkRepository, LinkCreator linkCreator) {
    this.linkRepository = linkRepository;
    this.linkCreator = linkCreator;
  }

  public LinkDto show(Long id) {
    requireNonNull(id);
    Link link = linkRepository.findOneOrThrow(id);

    return link.dto();
  }

  public LinkDto add(LinkDto linkDto) {
    requireNonNull(linkDto);
    Link link = linkCreator.from(linkDto);
    link = linkRepository.save(link);

    return link.dto();
  }

  public Page<LinkDto> findAll(Pageable pageable) {
    return linkRepository
        .findAll(pageable)
        .map(Link::dto);
  }

  public LinkDto findByShortLink(String shortLink) {
    requireNonNull(shortLink);
    Link shortUrl = linkRepository.findByShortUrlOrThrow(shortLink);
    updateCount(shortUrl);

    return shortUrl.dto();
  }

  public void removeExpiredLinks() {
    linkRepository.removeAllByExpiryDateBefore(LocalDateTime.now());
  }

  private void updateCount(Link link) {
    link.updateClicks();
    linkRepository.save(link);
  }
}
