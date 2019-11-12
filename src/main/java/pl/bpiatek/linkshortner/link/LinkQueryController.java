package pl.bpiatek.linkshortner.link;

import static pl.bpiatek.linkshortner.infrastructure.configuration.SwaggerMessages.FORBIDDEN;
import static pl.bpiatek.linkshortner.infrastructure.configuration.SwaggerMessages.NOT_FOUND;
import static pl.bpiatek.linkshortner.infrastructure.configuration.SwaggerMessages.UNAUTHORIZED;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.bpiatek.linkshortner.link.api.LinkNotFoundException;
import pl.bpiatek.linkshortner.link.api.LinkResponse;
import pl.bpiatek.linkshortner.link.domain.LinkQueryRepository;
import pl.bpiatek.linkshortner.link.query.LinkQueryDTO;

/**
 * Created by Bartosz Piatek on 12/11/2019
 */
@RequestMapping("api/link")
@RestController
class LinkQueryController {

  private final LinkQueryRepository linkQueryRepository;

  public LinkQueryController(LinkQueryRepository linkQueryRepository) {
    this.linkQueryRepository = linkQueryRepository;
  }

    @ApiOperation(value = "Get information about original link based on the shortened one", response = LinkResponse.class)
    @ApiResponses(value = {
      @ApiResponse(code = 302, message = "Link is successfully retrieved"),
      @ApiResponse(code = 401, message = UNAUTHORIZED),
      @ApiResponse(code = 403, message = FORBIDDEN),
      @ApiResponse(code = 404, message = NOT_FOUND)
  })
  @GetMapping("check/{shortLink}")
  ResponseEntity<LinkQueryDTO> check(@PathVariable String shortLink) {
    LinkQueryDTO linkQueryDTO = linkQueryRepository.findByShortUrl(shortLink).orElseThrow(LinkNotFoundException::new);

    return ResponseEntity.ok(linkQueryDTO);
  }
}
