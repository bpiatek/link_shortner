package pl.bpiatek.linkshortner.infrastructure.configuration;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import springfox.documentation.annotations.ApiIgnore;

/**
 * Created by Bartosz Piatek on 18/11/2019
 */
@ApiIgnore
@Controller
@RequestMapping("/swagger")
class SwaggerController {

  @GetMapping
  public String greeting() {
    return "redirect:/swagger-ui.html";
  }
}
