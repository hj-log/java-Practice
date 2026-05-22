package etc.fileio.csv.app;

import etc.fileio.csv.domain.*;
import etc.fileio.csv.repository.*;

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


        // ── 11. 제네릭 레포지토리 — 타입별 저장소 ───────────────
        System.out.println("=== ActivityRepository<T> — 제네릭 레포지토리 ===");

        // LectureLog만 담는 레포지토리 — 다른 타입을 add()하면 컴파일 오류
        ActivityRepository<LectureLog> lectureRepo = new ActivityRepository<>();
        lectureRepo.add((LectureLog) l1);
        lectureRepo.add((LectureLog) l2);
        lectureRepo.add((LectureLog) l3);

        // PracticeLog만 담는 레포지토리
        ActivityRepository<PracticeLog> practiceRepo = new ActivityRepository<>();
        practiceRepo.add((PracticeLog) p1);
        practiceRepo.add((PracticeLog) p2);
        practiceRepo.add((PracticeLog) p3);

        ActivityRepository<ReadingLog> readingRepo = new ActivityRepository<>();
        readingRepo.add((ReadingLog) r1);
        readingRepo.add((ReadingLog) r2);


        // ── 12. CSV 영속화 - File I/O ───────────────

        // CSV 파일을 한번에 작성하려고, 슈퍼타입(부모) 레포에 담아서 저장
        ActivityRepository<LearningActivity> allRepo = new ActivityRepository<>();
        for (LearningActivity a : activities) {
            allRepo.add(a);
        }

        Path csvPath = Path.of("data/activities.csv"); // .of(상대경로)를 작성함 절대경로도 있음(비추). / 차이로 상대경로"(data/activities.csv"), 절대경로 구분("/data/activities.csv")

        allRepo.saveToFile(csvPath);
        System.out.println("CSV 저장 완료: " + csvPath.toAbsolutePath()); // 어디에 저장됐는지 확인(절대경로를 보여줌)
        System.out.println(allRepo.count() + "건 저장됨!");


        System.out.println();
        System.out.println("총 생성된 활동 수: " + LearningActivity.getTotalCreatedCount());

    }
}
