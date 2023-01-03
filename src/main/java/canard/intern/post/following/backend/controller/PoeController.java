package canard.intern.post.following.backend.controller;


import canard.intern.post.following.backend.dto.PoeDetailDto;
import canard.intern.post.following.backend.dto.PoeDto;
import canard.intern.post.following.backend.service.PoeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;


/**
* Le controller des POE répond aux appels API du chemin/URL : ../api/poes
* http://{host}/api/poes
 */
@RestController
@RequestMapping("/api/poes")
public class PoeController {
    /**
     * Creation d'un objet PoeService
     */
    @Autowired
    private PoeService poeService;

    /**
     * GET /api/poes
     * Quand on fait un appel de type GET sans parametre , on recupere la liste de tous les poe
     * @return all poes
     */
    @GetMapping
    public List<PoeDto> getAll(){
        return poeService.getAll();
    }

    /**
     * GET /api/trainees/{id}
     *
     * Example: in order to get trainee of id 3, call
     *    GET /api/trainees/3
     *
     * @param id : id of trainee to get
     * @return trainee with this id if found
     */
//    @GetMapping("/{id}")
//    public PoeDto getById(@PathVariable("id") int id){
//        var optPoeDto =  poeService.getById(id);
//        if (optPoeDto.isEmpty()) {
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
//                    String.format("No trainee found with id <%d>", id));
//        }
//        return optPoeDto.get();
//    }
    @GetMapping("/{id}")
    public PoeDetailDto getById(@PathVariable("id") int id){
        // on recupere via la methode "getById" un objet PoeDailDto correspondant à un "id" de POE
        var optPoeDto =  poeService.getById(id);
        // si le resultat de l'opération precedente retour un objet vide, alors il n'y a pas de POE correspondant à l'ID
        if (optPoeDto.isEmpty()) {
            // On retourne donc une erreur au front de type "404 : non trouvé" et un message personnalisé :  No Poe found with id <%d>
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    String.format("No Poe found with id <%d>", id));
        }
        return optPoeDto.get();
    }

    /**
     * Methode répondant aux appel de type POST avec les informations d'un POE à creer
     * @param poeDto
     * @return un code 201 annonce le succes de la créaction (HttpStatus.CREATED)
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PoeDto create(@Valid @RequestBody PoeDto poeDto) {
        return poeService.create(poeDto);

    }

    /**
     * methode de mise à jour d'un POE via des requetes HTTP de type PUT
     * @param id : id pour identifier le POE à mettre à jour
     * @param poeDto : donnée de POE
     * @return
     */
    @PutMapping("/{id}")
    public PoeDto update(@PathVariable("id") int id, @Valid @RequestBody PoeDto poeDto){

        // etape 1
        // appel de la methode "update" pour mettre a jour un POE via son ID et les nouvelles données
        // en retour on recupere les données du POE à jour
        var optPoeDto =  poeService.update(id, poeDto);

        // etape 2 à inverser avec l'etape 1 car il faut controler avant de mettre à jour !!!!
        // si l'id du parametre "id" n'est pas le même que l'id des parametre du body alors on retourne une erreur (probleme cote front ?)
        if (Objects.nonNull(poeDto.getId()) && (poeDto.getId() != id)) {
            // on retourne de code retour 404 avec une message personnalisé
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Id <%d> from path does not match id <%d> from body", id, poeDto.getId()));
        }

        // etape 3
        // si le retour de l'etape 1 retour un objet vide (il n'y a pas de POE correspondnat à l'id) ...
        if (optPoeDto.isEmpty()){
            // on retour un code erreur 404 avec un message personnalisé
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("No trainee found with id  <%d> ", id));
        }

        // on retourne les information de POE mis à jour
        return optPoeDto.get();
    }

    /**
     * methode permettant de repondre au appel HTTP de type DELETE avec dans l'URL l'id du POE à supprimer
     * @param id : id du POE à supprimer
     * @return code http 204 signifiant le succes de la suppression
     */
    //NB: other choice, return Dto removed if found
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") int id){
        // appel de la methode "delete" de suppression d'un POE via son ID
        // retourne un boolean (TRUE ou FALSE) signifiant le suces ou non de l'opération
        var optPoeDto = poeService.delete(id);

        // si le retour est un FALSE alors on retourne un code erreur de type 404.
        if(!optPoeDto){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("No trainee found with id  <%d> ", id));
        }
    }
}
