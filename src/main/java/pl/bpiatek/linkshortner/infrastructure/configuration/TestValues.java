package pl.bpiatek.linkshortner.infrastructure.configuration;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Bartosz Piatek on 09/12/2019
 */
@Getter
public class TestValues {

  private String linkBase;
  private List<String> stringList;

  public TestValues() {
    this.linkBase = "http://localhost:8080/api/link/redirect/";
    this.stringList = Arrays.asList(("localhost:8080/api/link/redirect/,"
                                     + "www.localhost:8080/api/link/redirect/,"
                                     + "http://www.localhost:8080/api/link/redirect/,"
                                     + "http://localhost:8080/api/link/redirect/").split(","));
  }
}
