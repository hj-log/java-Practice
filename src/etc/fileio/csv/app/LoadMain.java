package etc.fileio.csv.app;

import etc.fileio.csv.domain.*;
import etc.fileio.csv.repository.*;

import java.io.*;
import java.nio.file.*;

public class LoadMain {

    public static void main(String[] args) throws IOException {

        Path csvPath = Path.of("data/activities.csv");
        ActivityRepository<LearningActivity> loadedRepo
                = ActivityRepository.loadFromFile(csvPath);

        System.out.println("CSV 로드 완료: " + loadedRepo.count() +"건");

        loadedRepo.findAll().forEach(a -> System.out.println("- " + a.getActivityType()
                + " | " + a.getTitle() + "(" + a.getMinutes() + "분) ["
                + a.getVisibility().getLabel() + "]" + a.getTags()));

    }

}
