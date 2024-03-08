package fr.iut.bc.pkdxapi.services;


import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import fr.iut.bc.pkdxapi.errors.Pkmn.PkmnAlreadyExistException;
import fr.iut.bc.pkdxapi.errors.Pkmn.PkmnDoesntExistException;
import fr.iut.bc.pkdxapi.errors.Pkmn.PkmnRegionAlreadyExistException;
import fr.iut.bc.pkdxapi.errors.Pkmn.PkmnRegionDoesntExist;
import fr.iut.bc.pkdxapi.models.Pkmn.Pkmn;
import fr.iut.bc.pkdxapi.models.Pkmn.PkmnData;
import fr.iut.bc.pkdxapi.models.Pkmn.PkmnRegion;
import fr.iut.bc.pkdxapi.models.Pkmn.PkmnTypes;
import fr.iut.bc.pkdxapi.models.Pkmn.requests.PkmnTypesResponse;
import fr.iut.bc.pkdxapi.repositories.PkmnRepository;

@Service
public class PkmnService {


    private PkmnRepository repository;

    public PkmnService(PkmnRepository repository) {
        this.repository = repository;
    }

    
    public PkmnData getByName(String name) {
        Optional<PkmnData> data = repository.findPkmnByName(name); 
        if (!data.isPresent()) {
            throw new PkmnDoesntExistException("Pokemon with name " + name + " not found.");  
        }

        return data.get();
    }

    public PkmnData getById(ObjectId id) {
        Optional<PkmnData> data =  repository.findById(id);
        if (!data.isPresent()) {
            throw new PkmnDoesntExistException("Pokemon with id " + id + " not found.");  
        }

        return data.get();
    }

    public PkmnData updateById(
        ObjectId id, 
        Optional<String> name, 
        Optional<String> description, 
        Optional<String> imgUrl, 
        Optional<PkmnTypes> typeOne, 
        Optional<PkmnTypes> typeTwo
    ) {
        
        Optional<PkmnData> data = repository.findById(id);
        if (!data.isPresent()) {
            throw new PkmnDoesntExistException("Pokemon with id " + id + " not found.");  
        }  

        PkmnData pkmnData = data.get();
        pkmnData.setTypes(typeOne, typeTwo);

        if (name.isPresent()) {
            if (pkmnExists(name.get()) && !name.get().equals(pkmnData.getName())) {
                throw new PkmnAlreadyExistException("Pokemon with name " + name.get() + " already exists.");
            }
            pkmnData.setName(name.get());
        }

        if (description.isPresent()) {
            pkmnData.setDescription(description.get());
        }

        if (imgUrl.isPresent()) {
            pkmnData.setImgUrl(imgUrl.get());
        }

        repository.save(pkmnData);
        return pkmnData;
    }

    public Page<PkmnData> searchPkmn(String partialName, Optional<String> typeOne, Optional<String> typeTwo, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        
        if (typeOne.isPresent() && typeTwo.isPresent()) {
            return repository.searchPokemons(partialName, typeOne.get(), typeTwo.get(), pageable);
        } 

        if (typeOne.isPresent()) {
            return repository.searchPokemons(partialName, typeOne.get(), pageable);
        }  
        
        if (typeTwo.isPresent()) {
            return repository.searchPokemons(partialName, typeTwo.get(), pageable);
        } 
            
        return repository.searchPokemons(partialName, pageable);  
    }




    public void create(Pkmn pkmn) {
        if (pkmnExists(pkmn.getName())) {
            throw new PkmnAlreadyExistException("Pokemon with name " + pkmn.getName() + " already exists.");
        }

        repository.insert(new PkmnData(
            pkmn.getName(), 
            pkmn.getDescription(), 
            pkmn.getTypes(), 
            pkmn.getRegions(), 
            pkmn.getImgUrl()
        ));
    }

        
    public PkmnTypesResponse getAllPkmnTypes() {
        List<PkmnTypes> result = new ArrayList<>(
            EnumSet.allOf(PkmnTypes.class)
        );

        return new PkmnTypesResponse(result);
    }



    public PkmnData addRegion(String pkmnName, PkmnRegion region) {
        if (!pkmnExists(pkmnName)) {
            throw new PkmnDoesntExistException("Pokemon with name " + pkmnName + " doesn't exist.");
        }

        if (PknmRegionExists(pkmnName, region)) {
            throw new PkmnRegionAlreadyExistException("Region " + region.getRegionName() + " already exists for pokemon " + pkmnName + "." );
        }

        PkmnData pkmnData = repository.findPkmnByName(pkmnName).get();
        List <PkmnRegion> newRegions = new ArrayList<PkmnRegion>(pkmnData.getRegions());
        
        newRegions.add(region);
        pkmnData.setRegions(newRegions);
        repository.save(pkmnData);

        return pkmnData;
    }

    public PkmnData removeRegion(ObjectId id, String regionName) {
        Optional<PkmnData> data = repository.findById(id);
        if (!data.isPresent()) {
            throw new PkmnDoesntExistException("Pokemon with id " + id + " not found.");  
        }

        PkmnData pkmnData = data.get();

        List<PkmnRegion> regions = pkmnData.getRegions();

        for (PkmnRegion region : regions) {
            if (region.getRegionName().equals(regionName)) {
                regions.remove(region);
                pkmnData.setRegions(regions);
                repository.save(pkmnData);

                return pkmnData;
            }
        }

        throw new PkmnRegionDoesntExist(regionName);
    }




    public void deleteById(ObjectId id) {
        Optional<PkmnData> pkmn = repository.findById(id);
        if (!pkmn.isPresent()) {
            throw new PkmnDoesntExistException("Pokemon with id " + id + " not found.");
        }

        repository.deleteById(id);
    }

    public void deleteByName(String name) {
        Optional<PkmnData> pkmn = repository.findPkmnByName(name);
        if (!pkmn.isPresent()) {
            throw new PkmnDoesntExistException("Pokemon with name " + name + " not found.");
        }

        repository.delete(pkmn.get());
    }
    



    private boolean PknmRegionExists(String pkmnName, PkmnRegion region) {
        PkmnData pkmnData = repository.findPkmnByName(pkmnName).get();

        for (PkmnRegion existingRegion : pkmnData.getRegions()) {
            if (existingRegion.getRegionName().equals(region.getRegionName())) {
                return true;
            }
        }
    
        return false;
    }

    private boolean pkmnExists(String name) {
        return repository.findPkmnByName(name).isPresent();
    }
}
