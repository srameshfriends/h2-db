package srimalar.examples.webflux.controls;

import org.springframework.stereotype.Service;
import srimalar.examples.webflux.model.Tutorial;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;

@Service
public class TutorialService {
        private static final ConcurrentHashMap<String, Tutorial> tutorialMap = new ConcurrentHashMap<>();

        public static Map<String, Tutorial> getTutorialMap() {
            if(tutorialMap.isEmpty()) {
                List<Tutorial> list = new ArrayList<>();
                list.add(Tutorial.create("1"));
                list.add(Tutorial.create("2"));
                list.add(Tutorial.create("3"));
                list.add(Tutorial.create("4"));
                list.add(Tutorial.create("5"));
                list.add(Tutorial.create("6"));
                list.add(Tutorial.create("7"));
                list.add(Tutorial.create("8"));
                list.add(Tutorial.create("9"));
                list.add(Tutorial.create("10"));
                list.forEach(new Consumer<Tutorial>() {
                    @Override
                    public void accept(Tutorial tutorial) {
                        tutorialMap.put(tutorial.getId(), tutorial);
                    }
                });
            }
            return tutorialMap;
        }

        public Tutorial findTutorialById(String id) {
            if(!getTutorialMap().containsKey(id)) {
                throw new NullPointerException("Tutorial not found.");
            }
            return getTutorialMap().get(id);
        }

        public List<Tutorial> getTutorialList() {
            return getTutorialMap().values().stream().toList();
        }

        public Tutorial addNewTutorial(Tutorial tutorial) {
            if(tutorial != null && tutorial.getId() != null) {
                getTutorialMap().put(tutorial.getId(), tutorial);
            } else {
                throw new NullPointerException("Tutorial not valid to save.");
            }
            return tutorial;
        }

        public Tutorial updateTutorial(String id, Tutorial tutorial) {
            if(tutorial == null ||  id == null || !getTutorialMap().containsKey(id)) {
                throw new NullPointerException("Tutorial not found to update.");
            }
            getTutorialMap().put(id, tutorial);
            return tutorial;
        }

        public void deleteTutorialById(String id) {
            getTutorialMap().remove(id);
        }
}
