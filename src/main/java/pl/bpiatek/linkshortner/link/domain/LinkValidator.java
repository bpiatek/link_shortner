package pl.bpiatek.linkshortner.link.domain;

import org.apache.commons.validator.routines.UrlValidator;
import org.springframework.stereotype.Service;

/**
 * Created by Bartosz Piatek on 16/08/2019
 */
@Service
class LinkValidator {

    boolean isValid(String link) {
      String[] schemes = {"www", "http", "https"};
      UrlValidator urlValidator = new UrlValidator(schemes);
      return urlValidator.isValid(link);
  }
}
