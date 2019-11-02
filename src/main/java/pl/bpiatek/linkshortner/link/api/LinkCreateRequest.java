package pl.bpiatek.linkshortner.link.api;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotEmpty;

/**
 * Created by Bartosz Piatek on 29/10/2019
 */
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "Details about Link to be shortened send in as request from client")
public class LinkCreateRequest {
  @NotEmpty
  @ApiModelProperty(name = "Original URL", value = "URL of the link to be shortened", required = true)
  private String originalUrl;
}
