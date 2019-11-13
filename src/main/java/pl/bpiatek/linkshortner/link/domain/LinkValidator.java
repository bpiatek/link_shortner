package pl.bpiatek.linkshortner.link.domain;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.validator.routines.UrlValidator;

import java.util.List;

/**
 * Created by Bartosz Piatek on 16/08/2019
 */
class LinkValidator {

  private static final String[] REPLACE = {"", "", "", ""};

  boolean isValid(String link) {
    UrlValidator urlValidator = new UrlValidator() {
      @Override
      public boolean isValid(String value) {
        return super.isValid(value) || super.isValid("http://" + value);
      }
    };

    return urlValidator.isValid(link);
  }

  String trimShortLink(String shortUrl, List<String> stringList) {
    return StringUtils.replaceEach(shortUrl, stringList.toArray(String[]::new), REPLACE);
  }
}
