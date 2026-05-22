package etc.fileio.json.app;

import etc.fileio.json.domain.*;
import etc.fileio.json.repository.*;

import java.nio.file.*;
import java.util.*;


public class SprintLogApp {

    public static void main(String[] args) throws Exception {

        // ── 1. 활동 목록 구성 — 이 섹션은 완성되어 있습니다 ─────────────
        List<LearningActivity> activities = new ArrayList<>();

        LearningActivity l1 = new LectureLog("JCF 이론", 50, Visibility.PUBLIC, "박코치");
        LearningActivity l2 = new LectureLog("Stream 이론", 55, Visibility.PUBLIC, "박코치");
        LearningActivity l3 = new LectureLog("람다 이론", 45, Visibility.PRIVATE, "박코치");
        LearningActivity p1 = new PracticeLog("List 실습", 80, Visibility.PUBLIC, 90);
        LearningActivity p2 = new PracticeLog("Stream 실습", 90, Visibility.PUBLIC, 88);
        LearningActivity p3 = new PracticeLog("람다 실습", 70, Visibility.PRIVATE, 75);
        LearningActivity r1 = new ReadingLog("Effective Java", 40, Visibility.PRIVATE, "조슈아 블로크");
        LearningActivity r2 = new ReadingLog("Java 8 in Action", 35, Visibility.PRIVATE, "하먀");

        for (LearningActivity a : List.of(l1, l2, l3)) a.addTag("이론");
        for (LearningActivity a : List.of(p1, p2, p3)) a.addTag("실습");
        for (LearningActivity a : List.of(r1, r2)) a.addTag("도서");
        for (LearningActivity a : List.of(l2, p2)) a.addTag("stream");

        activities.addAll(List.of(l1, l2, l3, p1, p2, p3, r1, r2));

        ActivityRepository<LearningActivity> allRepo = new ActivityRepository<>();
        for (LearningActivity a : activities) {
            allRepo.add(a);
        }

        Path jsonPath = Path.of("data/activities.json");
        allRepo.saveToJson(jsonPath);
        System.out.println("JSON 저장 완료: " + jsonPath.toAbsolutePath()
                + "(" + allRepo.count() + "건)");





    }
}
