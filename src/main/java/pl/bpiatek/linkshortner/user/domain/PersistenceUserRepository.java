package pl.bpiatek.linkshortner.user.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;

import java.util.Optional;

/**
 * Created by Bartosz Piatek on 04/11/2019
 */
interface PersistenceUserRepository extends Repository<User, Long> {

  Optional<User> findByUsername(String username);
  Page<User> findAll(Pageable pageable);
  User save(User user);
}
