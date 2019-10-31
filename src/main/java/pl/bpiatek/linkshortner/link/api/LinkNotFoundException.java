package pl.bpiatek.linkshortner.link.api;

/**
 * Created by Bartosz Piatek on 05/08/2019
 */
public class LinkNotFoundException extends RuntimeException {

  public LinkNotFoundException(Long id) {
    super("No Link with id " +  + id + " found", null, false, false);
  }

  public LinkNotFoundException(String shortUrl) {
    super("No Shortened Link for: 'http://localhost:8080/" + shortUrl + "' found", null, false, false);
  }
}
