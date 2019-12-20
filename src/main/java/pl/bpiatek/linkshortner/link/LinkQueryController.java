package pl.bpiatek.linkshortner.link;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static pl.bpiatek.linkshortner.infrastructure.configuration.SwaggerMessages.FORBIDDEN;
import static pl.bpiatek.linkshortner.infrastructure.configuration.SwaggerMessages.NOT_FOUND;
import static pl.bpiatek.linkshortner.infrastructure.configuration.SwaggerMessages.UNAUTHORIZED;

import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.bpiatek.linkshortner.link.api.LinkNotFoundException;
import pl.bpiatek.linkshortner.link.domain.LinkQueryRepository;
import pl.bpiatek.linkshortner.link.query.LinkQueryResponse;

/**
 * Created by Bartosz Piatek on 12/11/2019
 */
@Api(value = "Link rest CQRS controller")
@RequestMapping(value = "api/link/query", produces = APPLICATION_JSON)
@RestController
class LinkQueryController {

  private final LinkQueryRepository linkQueryRepository;

  LinkQueryController(LinkQueryRepository linkQueryRepository) {
    this.linkQueryRepository = linkQueryRepository;
  }

  @ApiOperation(value = "Retrieve original link based on a short link", response = LinkQueryResponse.class)
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Link was successfully retrieved"),
      @ApiResponse(code = 401, message = UNAUTHORIZED),
      @ApiResponse(code = 403, message = FORBIDDEN),
      @ApiResponse(code = 404, message = NOT_FOUND)
  })
  @GetMapping("{shortUrl}")
  ResponseEntity<LinkQueryResponse> findByShortUrl(@ApiParam(value = "Short link url", required = true)
                                                   @PathVariable String shortUrl) {
    LinkQueryResponse response = linkQueryRepository.findByShortUrl(shortUrl)
        .orElseThrow(LinkNotFoundException::new);

    return ResponseEntity.ok(response);
  }

}
