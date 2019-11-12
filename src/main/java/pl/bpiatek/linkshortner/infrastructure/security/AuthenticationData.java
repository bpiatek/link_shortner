package pl.bpiatek.linkshortner.infrastructure.security;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by Bartosz Piatek on 07/11/2019
 */
@JsonIgnoreProperties(ignoreUnknown = true)
class AuthenticationData {
  @ApiModelProperty(position = 1)
  public String username;
  @ApiModelProperty(position = 2)
  public String password;
}
