package pl.bpiatek.linkshortner.link.domain;

import static java.util.Objects.requireNonNull;

import pl.bpiatek.linkshortner.link.dto.LinkDto;

/**
 * Created by Bartosz Piatek on 05/08/2019
 */
class LinkCreator {

  private LinkShortener linkShortener;

  LinkCreator(LinkShortener linkShortener) {
    this.linkShortener = linkShortener;
  }


  String shorten(String originalLink) {
    return linkShortener.shortenLink(originalLink);
  }


  Link from(LinkDto linkDto){
    requireNonNull(linkDto);
    String originalUrl = linkDto.getOriginalUrl();
    return Link.builder()
        .originalUrl(originalUrl)
        .shortUrl(shorten(originalUrl))
        .build();
  }
}
