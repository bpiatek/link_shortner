package pl.bpiatek.linkshortner.useragent.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;

/**
 * Created by Bartosz Piatek on 30/10/2019
 */
interface UserAgentRepository extends Repository<UserAgent, Long> {

  UserAgent save(UserAgent userAgent);
  UserAgent findById(Long id);
  Page<UserAgent> findAll(Pageable pageable);
}
