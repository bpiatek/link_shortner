package pl.bpiatek.linkshortner.link.domain;

import org.springframework.stereotype.Service;
import pl.bpiatek.linkshortner.link.dto.InvalidLinkException;

import java.util.UUID;

/**
 * Created by Bartosz Piatek on 16/08/2019
 */
@Service
class LinkShortener {

  private LinkValidator linkValidator;
  private LinkRepository linkRepository;

  LinkShortener(LinkValidator linkValidator, LinkRepository linkRepository) {
    this.linkValidator = linkValidator;
    this.linkRepository = linkRepository;
  }

  String shortenLink(String originalLink) {
    if(linkValidator.isValid(originalLink)) {
      return createRandomLink();
    }

    throw new InvalidLinkException("Given link: '" + originalLink + "' is NOT valid");
  }

  private String createRandomLink() {
    String shortUrl;

    do {
      shortUrl = UUID.randomUUID().toString().substring(25);
    } while (shortenUrlAlreadyExists(shortUrl));

    return shortUrl;
  }

  private boolean shortenUrlAlreadyExists(String shortUrl) {
    return linkRepository.findByShortUrl(shortUrl) != null;
  }
}
