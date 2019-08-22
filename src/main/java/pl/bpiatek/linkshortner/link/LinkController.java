package pl.bpiatek.linkshortner.link;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.bpiatek.linkshortner.link.domain.LinkFacade;
import pl.bpiatek.linkshortner.link.dto.LinkDto;

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
  LinkDto addLink(@Valid @RequestBody LinkDto linkDto) {
    return linkFacade.add(linkDto);
  }

  @GetMapping("links")
  Page<LinkDto> findAll(Pageable pageable) {
    return linkFacade.findAll(pageable);
  }

  @GetMapping("/show/{id}")
  LinkDto findById(@PathVariable Long id) {
    return linkFacade.show(id);
  }

  @GetMapping("/{shortLink}")
  ResponseEntity<Void> redirect(@PathVariable String shortLink) {
    String originalUrl = linkFacade.findByShortLink(shortLink).getOriginalUrl();

    return ResponseEntity.status(HttpStatus.FOUND)
        .location(URI.create(originalUrl))
        .build();
  }
}

