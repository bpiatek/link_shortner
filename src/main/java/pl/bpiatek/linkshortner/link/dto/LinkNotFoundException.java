package pl.bpiatek.linkshortner.link.dto;

/**
 * Created by Bartosz Piatek on 05/08/2019
 */
public class LinkNotFoundException extends RuntimeException {

  public LinkNotFoundException(Long id) {
    super("No Link with id " + id + " found", null, false, false);
  }

  public LinkNotFoundException(String id) {
    super("No Shortened Link for: " + id + " found", null, false, false);
  }
}
