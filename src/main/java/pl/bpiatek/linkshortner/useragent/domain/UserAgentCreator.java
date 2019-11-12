package pl.bpiatek.linkshortner.useragent.domain;

import static java.util.Objects.requireNonNull;

import pl.bpiatek.linkshortner.useragent.api.UserAgentCreateRequest;

/**
 * Created by Bartosz Piatek on 31/10/2019
 */
class UserAgentCreator {

  UserAgent from(UserAgentCreateRequest userAgentCreateRequest) {
    requireNonNull(userAgentCreateRequest);

    return UserAgent.builder()
        .operatingSystemClass(userAgentCreateRequest.getOperatingSystemClass())
        .operatingSystemName(userAgentCreateRequest.getOperatingSystemName())
        .agentName(userAgentCreateRequest.getAgentName())
        .deviceBrand(userAgentCreateRequest.getDeviceBrand())
        .deviceName(userAgentCreateRequest.getDeviceName())
        .language(userAgentCreateRequest.getLanguage())
        .clickDate(userAgentCreateRequest.getClickDate())
        .linkId(userAgentCreateRequest.getLinkId())
        .build();
  }
}
