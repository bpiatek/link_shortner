package pl.bpiatek.linkshortner.user.domain;

import org.springframework.data.repository.Repository;

import java.util.List;

/**
 * Created by Bartosz Piatek on 04/11/2019
 */
@org.springframework.stereotype.Repository
interface PersistenceUserRepository extends Repository<User, Long> {

  User findByUsername(String username);
  List<User> findAll();
  User save(User user);
}
