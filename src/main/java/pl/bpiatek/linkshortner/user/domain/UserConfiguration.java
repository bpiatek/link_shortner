package pl.bpiatek.linkshortner.user.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Bartosz Piatek on 04/11/2019
 */
@Configuration
class UserConfiguration {

  @Bean
  UserFacade userFacade(PersistenceUserRepository persistenceUserRepository) {
    UserPrincipalDetailsService userPrincipalDetailsService = new UserPrincipalDetailsService(persistenceUserRepository);
    return new UserFacade(userPrincipalDetailsService, persistenceUserRepository);
  }
}
