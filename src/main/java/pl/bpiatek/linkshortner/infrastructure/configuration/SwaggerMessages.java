package pl.bpiatek.linkshortner.infrastructure.configuration;

import static lombok.AccessLevel.PRIVATE;

import lombok.NoArgsConstructor;

/**
 * Created by Bartosz Piatek on 01/11/2019
 */
@NoArgsConstructor(access = PRIVATE)
public class SwaggerMessages {
  public static final String UNAUTHORIZED = "You are not authorized to view the resource";
  public static final String FORBIDDEN = "Accessing the resource you were trying to reach is forbidden";
  public static final String NOT_FOUND = "The resource you were trying to reach is not found";
}
