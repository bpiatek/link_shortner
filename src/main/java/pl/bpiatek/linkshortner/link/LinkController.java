package pl.bpiatek.linkshortner.link;

import static org.springframework.http.HttpStatus.FOUND;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static pl.bpiatek.linkshortner.infrastructure.configuration.SwaggerMessages.FORBIDDEN;
import static pl.bpiatek.linkshortner.infrastructure.configuration.SwaggerMessages.NOT_FOUND;
import static pl.bpiatek.linkshortner.infrastructure.configuration.SwaggerMessages.UNAUTHORIZED;

import io.swagger.annotations.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pl.bpiatek.linkshortner.link.api.LinkRedirectRequest;
import pl.bpiatek.linkshortner.link.api.LinkCreateRequest;
import pl.bpiatek.linkshortner.link.api.LinkResponse;
import pl.bpiatek.linkshortner.link.domain.LinkFacade;

import java.net.URI;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * Created by Bartosz Piatek on 05/08/2019
 */
@Api(value = "Link rest controller")
@RequestMapping(value = "api/link", produces = APPLICATION_JSON_VALUE)
@RestController
class LinkController {

  private LinkFacade linkFacade;

  public LinkController(LinkFacade linkFacade) {
    this.linkFacade = linkFacade;
  }

  @ApiOperation(value = "Create shortened link based on passed object", response = LinkResponse.class)
  @ApiResponses(value = {
      @ApiResponse(code = 201, message = "Link is successfully created"),
      @ApiResponse(code = 401, message = UNAUTHORIZED),
      @ApiResponse(code = 403, message = FORBIDDEN)
  })
  @PostMapping
  ResponseEntity<LinkResponse> addLink(@ApiParam(value = "Request object to create shortened link", required = true)
                                       @Valid @RequestBody LinkCreateRequest linkCreateRequest) {
    LinkResponse linkResponse = linkFacade.add(linkCreateRequest);

    return new ResponseEntity<>(linkResponse, HttpStatus.CREATED);
  }

  @ApiOperation(value = "Retrieve all links from database. Result is paged.", response = LinkResponse.class, responseContainer = "Page")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Links are successfully retrieved"),
      @ApiResponse(code = 401, message = UNAUTHORIZED),
      @ApiResponse(code = 403, message = FORBIDDEN),
      @ApiResponse(code = 404, message = NOT_FOUND)
  })
  @GetMapping("all")
  Page<LinkResponse> findAll(Pageable pageable) {
    return linkFacade.findAll(pageable);
  }

  @ApiOperation(value = "Retrieve a specific link from database based on Id", response = LinkResponse.class)
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Links are successfully retrieved"),
      @ApiResponse(code = 401, message = UNAUTHORIZED),
      @ApiResponse(code = 403, message = FORBIDDEN),
      @ApiResponse(code = 404, message = NOT_FOUND)
  })
  @GetMapping("{id}")
  ResponseEntity<LinkResponse> findById(@ApiParam(value = "Id of the specific link", required = true)
                                        @PathVariable Long id) {
    LinkResponse linkResponse = linkFacade.show(id);

    return ResponseEntity.ok(linkResponse);
  }

  @ApiOperation(value = "Redirect shortened link to it's original URL", response = LinkResponse.class)
  @ApiResponses(value = {
      @ApiResponse(code = 302, message = "Link is successfully redirected"),
      @ApiResponse(code = 401, message = UNAUTHORIZED),
      @ApiResponse(code = 403, message = FORBIDDEN),
      @ApiResponse(code = 404, message = NOT_FOUND)
  })
  @PostMapping("redirect")
  ResponseEntity<Void> redirect(@Validated @RequestBody LinkRedirectRequest linkRedirectRequest, HttpServletRequest request) {
    LinkResponse linkResponse = linkFacade.findByShortLinkAndRedirect(linkRedirectRequest, request);

    return ResponseEntity.status(FOUND)
        .location(URI.create(linkResponse.getOriginalUrl()))
        .build();
  }
}
