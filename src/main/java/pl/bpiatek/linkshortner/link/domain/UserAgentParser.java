package pl.bpiatek.linkshortner.link.domain;

import static nl.basjes.parse.useragent.UserAgent.*;
import static org.springframework.http.HttpHeaders.USER_AGENT;

import nl.basjes.parse.useragent.UserAgent;
import nl.basjes.parse.useragent.UserAgentAnalyzer;
import pl.bpiatek.linkshortner.useragent.api.UserAgentCreateRequest;

import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Bartosz Piatek on 30/10/2019
 */
class UserAgentParser {

  private UserAgentAnalyzer userAgentAnalyzer;

  UserAgentParser(UserAgentAnalyzer userAgentAnalyzer) {
    this.userAgentAnalyzer = userAgentAnalyzer;
  }

  UserAgentCreateRequest getDetails(HttpServletRequest request, Long linkId) {
    UserAgent userAgent = userAgentAnalyzer.parse(request.getHeader(USER_AGENT));

    String operatingSystemClass = userAgent.getValue(OPERATING_SYSTEM_CLASS);
    String operatingSystemName = userAgent.getValue(OPERATING_SYSTEM_NAME);
    String deviceName = userAgent.getValue(DEVICE_NAME);
    String deviceBrand = userAgent.getValue(DEVICE_BRAND);
    String agentName = userAgent.getValue(AGENT_NAME);
    String language = userAgent.getValue(AGENT_LANGUAGE);

    return new UserAgentCreateRequest(
        deviceName,
        deviceBrand,
        operatingSystemName,
        operatingSystemClass,
        agentName,
        language,
        LocalDateTime.now(),
        linkId
    );
  }
}
