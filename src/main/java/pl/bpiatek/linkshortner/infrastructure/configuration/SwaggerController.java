package pl.bpiatek.linkshortner.infrastructure.configuration;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Bartosz Piatek on 18/11/2019
 */
@Controller
@RequestMapping("/swagger")
class SwaggerController {

  public String greeting() {
    return "redirect:/swagger-ui.html";
  }
}
