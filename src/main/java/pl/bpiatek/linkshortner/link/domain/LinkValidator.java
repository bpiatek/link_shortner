package pl.bpiatek.linkshortner.link.domain;

import org.apache.commons.validator.routines.UrlValidator;

/**
 * Created by Bartosz Piatek on 16/08/2019
 */
class LinkValidator {

  boolean isValid(String link) {
    UrlValidator urlValidator = new UrlValidator() {
      @Override
      public boolean isValid(String value) {
        return super.isValid(value) || super.isValid("http://" + value);
      }
    };

    return urlValidator.isValid(link);
  }
}
