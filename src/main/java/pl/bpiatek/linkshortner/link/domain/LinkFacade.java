package pl.bpiatek.linkshortner.link.domain;

import static java.util.Objects.requireNonNull;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import pl.bpiatek.linkshortner.link.api.LinkCreateRequest;
import pl.bpiatek.linkshortner.link.api.LinkResponse;
import pl.bpiatek.linkshortner.useragent.api.UserAgentCreateEvent;
import pl.bpiatek.linkshortner.useragent.api.UserAgentCreateRequest;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Bartosz Piatek on 05/08/2019
 */
@Transactional
public class LinkFacade {

  @Value("${link.base}")
  private String linkBase;

  @Value(value = "#{'${list.to.trim}'.split(',')}")
  private List<String> stringList;

  private LinkRepository linkRepository;
  private LinkCreator linkCreator;
  private ApplicationEventPublisher applicationEventPublisher;
  private UserAgentParser userAgentParser;
  private LinkValidator linkValidator;

  public LinkFacade(
      LinkRepository linkRepository,
      LinkCreator linkCreator,
      ApplicationEventPublisher applicationEventPublisher,
      UserAgentParser userAgentParser,
      LinkValidator linkValidator
  ) {
    this.linkRepository = linkRepository;
    this.linkCreator = linkCreator;
    this.applicationEventPublisher = applicationEventPublisher;
    this.userAgentParser = userAgentParser;
    this.linkValidator = linkValidator;
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

  public LinkResponse findByShortLinkAndRedirect(String shortLink, HttpServletRequest request) {
    requireNonNull(shortLink);
    requireNonNull(request);

    Link link = linkRepository.findByShortUrlOrThrow(shortLink, linkBase);
    Link updatedLink = updateCount(link);

    publicUserAgentEvent(request, updatedLink.id());

    return updatedLink.dto(linkBase);
  }

  public LinkResponse findByShortLink(String shortLink) {
    requireNonNull(shortLink);

    String trimmedShortLink = linkValidator.trimShortLink(shortLink, stringList);

    return linkRepository.findByShortUrlOrThrow(trimmedShortLink, linkBase)
        .dto(linkBase);
  }

  public int markExpired() {
    return linkRepository.setEnabledToFalse();
  }

  private void publicUserAgentEvent(HttpServletRequest request, Long linkId) {
    UserAgentCreateRequest details = userAgentParser.getDetails(request, linkId);
    applicationEventPublisher.publishEvent(new UserAgentCreateEvent(details));
  }

  private Link updateCount(Link link) {
    link.updateClicks();
    return linkRepository.save(link);
  }
}
