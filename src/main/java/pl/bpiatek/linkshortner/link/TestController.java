package pl.bpiatek.linkshortner.link;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Bartosz Piatek on 04/11/2019
 */
@RestController
@RequestMapping("api/test")
class TestController {

  @GetMapping("1")
  ResponseEntity<String> test1() {
    return ResponseEntity.ok("TESTUJEMY ROLE I AUTHORITIES");
  }

  @GetMapping("2")
  ResponseEntity<String> test2() {
    return ResponseEntity.ok("TESTUJEMY ROLE I AUTHORITIES");
  }
}
