package srimalar.sample.webflux.controls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import srimalar.sample.webflux.model.Tutorial;

/*@CrossOrigin(origins = "http://localhost:8081")*/
@RestController
@RequestMapping("/tutorials")
public class TutorialController {

    @Autowired
    TutorialService tutorialService;

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public Flux<Tutorial> getAllTutorials() {
        return Flux.fromIterable(tutorialService.getTutorialList().stream().toList());
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Tutorial> getTutorialById(@PathVariable("id") String id) {
        Tutorial tutorial = tutorialService.findTutorialById(id);
        return Mono.just(tutorial);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Tutorial> createTutorial(@RequestBody Tutorial tutorial) {
        Tutorial result = tutorialService.addNewTutorial(tutorial);
        return Mono.just(result);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Tutorial> updateTutorial(@PathVariable("id") String id, @RequestBody Tutorial tutorial) {
        Tutorial result = tutorialService.updateTutorial(id, tutorial);
        return Mono.just(result);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> deleteTutorial(@PathVariable("id") String id) {
        tutorialService.deleteTutorialById(id);
        return Mono.empty();
    }
}
