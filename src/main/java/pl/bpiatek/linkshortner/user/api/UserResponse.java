package pl.bpiatek.linkshortner.user.api;

import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 * Created by Bartosz Piatek on 07/11/2019
 */
@Builder
@Getter
@EqualsAndHashCode
@ApiModel(description = "Details about User send in response from server")
public class UserResponse {

  private Long id;
  private String username;
  private int active;
}
