package canard.intern.post.following.backend.service.impl;

import canard.intern.post.following.backend.dto.PoeDetailDto;
import canard.intern.post.following.backend.dto.PoeDto;
import canard.intern.post.following.backend.dto.TraineeDto;
import canard.intern.post.following.backend.entity.Poe;
import canard.intern.post.following.backend.error.UpdateException;

import canard.intern.post.following.backend.repository.PoeRepository;
import canard.intern.post.following.backend.repository.TraineeRepository;
import canard.intern.post.following.backend.service.PoeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

/**
 * Implementation de l'interface Poeservice = c'est ici qu'on ecrit le code de l'interface
 * CLasse qui met a disposition tous les services de recuperation et mise à jour des données de formation POE
 */
@Service
public class PoeServiceJpa implements PoeService {

    // On cree un objet de la classe PoeRepository afin de faire des actions de consultation/modification des données en base de données
    @Autowired
    private PoeRepository poeRepository;

    // implementation d'un objet de lecture/ecriture en base de données des informations des stagiaires
    private TraineeRepository traineeRepository;

    // objet qui va permettre de convertir des objets de la base de données en objet de DTO
    @Autowired
    private ModelMapper modelMapper;



    @Override
    public List<PoeDto> getAll() {
//        return traineeRepository.findAll().stream().map(
//                (t)->TraineeDto.builder()
//                        .id(t.getId())
//                        .lastname(t.getLastname())
//                        .email(t.getEmail())
//                        .firstname(t.getFirstname())
//                        .gender(t.getGender())
//                        .birthdate(t.getBirthdate())
//                        .phoneNumber(t.getPhoneNumber())
//                        .build()
//
//        ).toList();
        return poeRepository.findAll().stream().map((t)->modelMapper.map(t,PoeDto.class)).toList();
    }

    @Override
    public Optional<PoeDetailDto> getById(int id) {
        var optPoe = poeRepository.findById(id);
        if (optPoe.isPresent()){
            var trainees = traineeRepository.findByPoeId(id)
                    .stream()
                    .map(traineeEntity -> modelMapper.map(traineeEntity, TraineeDto.class))
                    .toList();
            var poeDDto= modelMapper.map(optPoe.get(),PoeDetailDto.class);
            poeDDto.setTrainees(trainees);
            return Optional.of(poeDDto);
        }
        else{
            return Optional.empty();
        }
    }
//    @Override
//    public Optional<PoeDetailDto> getById(int id) {
//        var optPoe = poeRepository.findById(id);
//        PoeDetailDto poeDto ;
//        if (optPoe.isPresent()){
//            poeDto= modelMapper.map(optPoe.get(),PoeDetailDto.class);
//            return Optional.of(poeDto);
//        }
//        else{
//            return Optional.empty();
//        }
//    }

        //        return traineeRepository.findById(id)
        //                .map((te)-> modelMapper.map(te,
        //                TraineeDto.class));//
        //                transfo que si y'a kkchose dans la boite


    @Override
    public PoeDto create(PoeDto poeDto) {
        // transformer traineeDto en trainee (grace au modelMapper)
        // le sauver dans la base de donnée grace au traineeRepository.save
        // récupérer le trainee renvoyé par traineeRepository.save et le convertir en TraineeDto
        Poe poeDb;
        try {
            poeDb= poeRepository.save(modelMapper.map(poeDto, Poe.class));
        }catch(Exception e){
            throw (new UpdateException("poe couldn't be saved",e));
        }

        return modelMapper.map(poeDb, PoeDto.class);
    }

    @Override
    public Optional<PoeDto> update(int id, PoeDto poeDto) {
        poeDto.setId(id);
        var optPoeDb = poeRepository.findById(id);
        try {
            if (optPoeDb.isPresent()) {
                var poeDb = optPoeDb.get();
                modelMapper.map(poeDto, poeDb); // change traineeDb in hibernate cache
                poeRepository.flush(); // synchro et force l'update en sql
                return Optional.of(modelMapper.map(poeDb, PoeDto.class));
            } else {
                return Optional.empty();
            }
        } catch (Exception e) {
            throw new UpdateException("trainee couldn't be updated", e);
        }
    }

    @Override
    public boolean delete(int id) {
        try {
            if (poeRepository.findById(id).isPresent()) {
                poeRepository.deleteById(id);
                return true;
            } else {
                return false;
            }
        }catch(Exception e){// autres problèmes
            throw (new UpdateException("Trainee couldn't be deleted",e));
            //return false;
        }
    }
}