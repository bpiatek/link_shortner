package pl.bpiatek.linkshortner.link.api;

/**
 * Created by Bartosz Piatek on 05/08/2019
 */
public class LinkNotFoundException extends RuntimeException {

  public LinkNotFoundException() {
    super("Link not found");
  }

  public LinkNotFoundException(Long id) {
    super("No Link with id " +  + id + " found", null, false, false);
  }

  public LinkNotFoundException(String shortUrl, String urlBase) {
    super("No Shortened Link for: " + urlBase.replace("/api", "") + shortUrl + "' found", null, false, false);
  }
}
