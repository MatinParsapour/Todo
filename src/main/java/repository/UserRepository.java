package repository;

import base.repository.BaseEntityRepository;
import domain.User;

public interface UserRepository extends BaseEntityRepository<User,Long> {

    boolean checkUser(String username, String password);
}
