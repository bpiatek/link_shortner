package pl.bpiatek.linkshortner.link.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Value;

/**
 * Created by Bartosz Piatek on 12/11/2019
 */
@Value
@ApiModel(description = "Details about Link send in response from CQRS request")
public class LinkQueryResponse {

  @ApiModelProperty(name = "Id", value = "Id of the link in database")
  private Long id;
  @ApiModelProperty(name = "Original URL", value = "URL value before shortening")
  private String originalUrl;
}
