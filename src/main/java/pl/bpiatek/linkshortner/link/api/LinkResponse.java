package pl.bpiatek.linkshortner.link.api;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.time.LocalDateTime;

/**
 * Created by Bartosz Piatek on 05/08/2019
 */
@Builder
@Getter
@EqualsAndHashCode
@ApiModel(description = "Details about Link send in response from server")
public class LinkResponse {

  @ApiModelProperty(name = "Id", value = "Id of the link in database")
  private Long id;
  @ApiModelProperty(name = "Original URL", value = "URL value before shortening")
  private String originalUrl;
  @ApiModelProperty(name = "Short URL", value = "Shortened URL")
  private String shortUrl;
  @ApiModelProperty(name = "Created date", value = "Date of link creation")
  private LocalDateTime created;
  @ApiModelProperty(name = "Expiry date", value = "Date of link expiration")
  private LocalDateTime expiryDate;
  @ApiModelProperty(name = "Clicks number", value = "Number of clicks on shortened link")
  private Integer clicks;
  @ApiModelProperty(name = "Enabled", value = "Value representing if link is enabled or disabled")
  private boolean enabled;
}
