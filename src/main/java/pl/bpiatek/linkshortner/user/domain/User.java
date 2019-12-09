package pl.bpiatek.linkshortner.user.domain;

import static org.apache.commons.lang3.StringUtils.isEmpty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.bpiatek.linkshortner.user.api.UserResponse;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by Bartosz Piatek on 04/11/2019
 */
@Entity
@Getter
@NoArgsConstructor
class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotNull
  @Column(nullable = false, unique = true)
  private String username;

  @NotNull
  @Column(nullable = false)
  private String password;

  private int active;

  private String roles = "";

  private String permissions = "";

  User(String username, String password, String roles, String permissions) {
    this.username = username;
    this.password = password;
    this.active = 1;
    this.roles = roles;
    this.permissions = permissions;
  }

  List<String> roles() {
    if(isEmpty(this.roles)) {
      return new ArrayList<>();
    }

    return Arrays.asList(this.roles.split(","));
  }

  List<String> permissions() {
    if(isEmpty(this.permissions)) {
      return new ArrayList<>();
    }

    return Arrays.asList(this.permissions.split(","));
  }

  UserResponse dto() {
    return UserResponse.builder()
        .id(this.id)
        .username(this.username)
        .active(this.active)
        .build();
  }
}
