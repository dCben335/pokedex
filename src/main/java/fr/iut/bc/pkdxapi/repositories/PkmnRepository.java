package fr.iut.bc.pkdxapi.repositories;

import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import fr.iut.bc.pkdxapi.models.Pkmn.PkmnData;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

@Repository
public interface PkmnRepository extends MongoRepository<PkmnData, ObjectId> {

    @Query("{name:'?0'}")
    Optional<PkmnData> findPkmnByName(String name);

    @Query("{name: { $regex: ?0, $options: 'i' }, types: { $all: [?1, ?2] } }")
    Page<PkmnData> searchPokemons(String partialName, String typeOne, String typeTwo, Pageable pageable);

    @Query("{name: { $regex: ?0, $options: 'i' }, types: { $all: [?1] } }")
    Page<PkmnData> searchPokemons(String partialName, String typeOne, Pageable pageable);

    @Query("{name: { $regex: ?0, $options: 'i' }}")
    Page<PkmnData> searchPokemons(String partialName, Pageable pageable);
} 