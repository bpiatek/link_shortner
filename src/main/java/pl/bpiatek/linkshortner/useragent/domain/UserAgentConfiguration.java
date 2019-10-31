package pl.bpiatek.linkshortner.useragent.domain;

import static nl.basjes.parse.useragent.UserAgent.*;
import static nl.basjes.parse.useragent.UserAgent.AGENT_NAME;

import nl.basjes.parse.useragent.UserAgentAnalyzer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Bartosz Piatek on 31/10/2019
 */
@Configuration
class UserAgentConfiguration {

  @Bean
  UserAgentAnalyzer userAgentSingleton() {
    return UserAgentAnalyzer
        .newBuilder()
        .withField(OPERATING_SYSTEM_CLASS)
        .withField(DEVICE_NAME)
        .withField(DEVICE_BRAND)
        .withField(OPERATING_SYSTEM_NAME)
        .withField(AGENT_NAME)
        .hideMatcherLoadStats()
        .withCache(50)
        .build();
  }

  @Bean
  UserAgentFacade userAgentFacade(UserAgentRepository userAgentRepository) {
    UserAgentCreator userAgentCreator = new UserAgentCreator();
    return new UserAgentFacade(userAgentRepository, userAgentCreator);
  }
}
