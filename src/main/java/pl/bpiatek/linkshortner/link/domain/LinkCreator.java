package pl.bpiatek.linkshortner.link.domain;

import static java.util.Objects.requireNonNull;

import pl.bpiatek.linkshortner.link.dto.LinkCreateRequest;

/**
 * Created by Bartosz Piatek on 05/08/2019
 */
class LinkCreator {
  private static final String HTTP = "http";
  private static final String HTTPS = "https";

  private LinkShortener linkShortener;

  LinkCreator(LinkShortener linkShortener) {
    this.linkShortener = linkShortener;
  }

  Link from(LinkCreateRequest linkCreateRequest){
    requireNonNull(linkCreateRequest);
    String originalUrl = linkCreateRequest.getOriginalUrl();

    return Link.builder()
        .originalUrl(decorateOriginalUrl(originalUrl))
        .shortUrl(shorten(originalUrl))
        .build();
  }

  private String shorten(String originalLink) {
    return linkShortener.shortenLink(originalLink);
  }

  private String decorateOriginalUrl(String originalUrl) {
    if(!correctPrefix(originalUrl)) {
      return HTTP + "://" + originalUrl;
    }
    return originalUrl;
  }

  private boolean correctPrefix(String originalUrl) {
    return originalUrl.startsWith(HTTP)
        || originalUrl.startsWith(HTTPS);
  }
}
