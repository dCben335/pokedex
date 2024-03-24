package fr.iut.bc.pkdxapi.repositories;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import fr.iut.bc.pkdxapi.models.User.UserData;


@Repository
public interface UserRepository extends MongoRepository<UserData, String> {
    UserData findByLogin(String login);
}