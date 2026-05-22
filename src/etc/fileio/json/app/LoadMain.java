package etc.fileio.json.app;

import etc.fileio.json.domain.*;
import etc.fileio.json.repository.*;

import java.io.*;
import java.nio.file.*;

public class LoadMain {

    public static void main(String[] args) throws IOException {

        Path jsonPath = Path.of("data/activities.json");
        ActivityRepository<LearningActivity> loadedRepo
                = ActivityRepository.loadFromJson(jsonPath);

        System.out.println("CSV 로드 완료: " + loadedRepo.count() +"건");

        loadedRepo.findAll().forEach(a -> System.out.println("- " + a.getActivityType()
                + " | " + a.getTitle() + "(" + a.getMinutes() + "분) ["
                + a.getVisibility().getLabel() + "]" + a.getTags()));

    }

}
