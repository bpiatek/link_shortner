package pl.bpiatek.linkshortner.link.dto;

/**
 * Created by Bartosz Piatek on 16/08/2019
 */
public class InvalidLinkException extends RuntimeException{

  public InvalidLinkException(String message) {
    super(message);
  }
}
