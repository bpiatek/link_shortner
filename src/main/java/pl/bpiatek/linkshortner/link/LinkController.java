package pl.bpiatek.linkshortner.link;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.bpiatek.linkshortner.link.domain.LinkFacade;
import pl.bpiatek.linkshortner.link.dto.LinkCreateRequest;
import pl.bpiatek.linkshortner.link.dto.LinkResponse;

import java.net.URI;

import javax.validation.Valid;

/**
 * Created by Bartosz Piatek on 05/08/2019
 */
@RestController
class LinkController {

  private LinkFacade linkFacade;

  public LinkController(LinkFacade linkFacade) {
    this.linkFacade = linkFacade;
  }

  @PostMapping("link")
  LinkResponse addLink(@Valid @RequestBody LinkCreateRequest linkCreateRequest) {
    return linkFacade.add(linkCreateRequest);
  }

  @GetMapping("links")
  Page<LinkResponse> findAll(Pageable pageable) {
    return linkFacade.findAll(pageable);
  }

  @GetMapping("/link/{id}")
  LinkResponse findById(@PathVariable Long id) {
    return linkFacade.show(id);
  }

  @GetMapping("short/{shortLink}")
  ResponseEntity<Void> redirect(@PathVariable String shortLink) {
    LinkResponse linkResponse = linkFacade.findByShortLink(shortLink);


    return ResponseEntity.status(HttpStatus.FOUND)
        .location(URI.create(linkResponse.getOriginalUrl()))
        .build();
  }
}

