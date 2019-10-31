package pl.bpiatek.linkshortner.link.domain;

import static java.util.Objects.requireNonNull;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import pl.bpiatek.linkshortner.link.api.*;
import pl.bpiatek.linkshortner.useragent.api.UserAgentCreateEvent;
import pl.bpiatek.linkshortner.useragent.api.UserAgentCreateRequest;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Bartosz Piatek on 05/08/2019
 */
@Transactional
public class LinkFacade {

  @Value("${link.base}")
  private String linkBase;
  private PersistenceLinkRepository persistenceLinkRepository;
  private LinkCreator linkCreator;
  private ApplicationEventPublisher applicationEventPublisher;
  private UserAgentParser userAgentParser;

  public LinkFacade(
      PersistenceLinkRepository persistenceLinkRepository,
      LinkCreator linkCreator,
      ApplicationEventPublisher applicationEventPublisher,
      UserAgentParser userAgentParser
  ) {
    this.persistenceLinkRepository = persistenceLinkRepository;
    this.linkCreator = linkCreator;
    this.applicationEventPublisher = applicationEventPublisher;
    this.userAgentParser = userAgentParser;
  }

  public LinkResponse show(Long id) {
    requireNonNull(id);
    Link link = persistenceLinkRepository.findOneOrThrow(id);

    return link.dto(linkBase);
  }

  public LinkResponse add(LinkCreateRequest linkCreateRequest) {
    requireNonNull(linkCreateRequest);
    Link link = linkCreator.from(linkCreateRequest);
    link = persistenceLinkRepository.save(link);

    return link.dto(linkBase);
  }

  public Page<LinkResponse> findAll(Pageable pageable) {
    return persistenceLinkRepository
        .findAll(pageable)
        .map(l -> l.dto(linkBase));
  }

  public LinkResponse findByShortLink(String shortLink, HttpServletRequest request) {
    requireNonNull(shortLink);
    requireNonNull(request);
    Link link = persistenceLinkRepository.findByShortUrlOrThrow(shortLink);
    Link updatedLink = updateCount(link);

    return updatedLink.dto(linkBase);
  }

  public int markExpired() {
    return persistenceLinkRepository.setEnabledToFalse();
  }

  public void publicUserAgentEvent(HttpServletRequest request, Long linkId) {
    UserAgentCreateRequest details = userAgentParser.getDetails(request, linkId);
    applicationEventPublisher.publishEvent(new UserAgentCreateEvent(details));
  }

  private Link updateCount(Link link) {
    link.updateClicks();
    return persistenceLinkRepository.save(link);
  }
}
