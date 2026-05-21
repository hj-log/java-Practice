package etc.fileio.repository;

import etc.fileio.domain.*;

import java.io.*;
import java.nio.charset.*;
import java.nio.file.*;
import java.util.*;
import java.util.function.*;

/*
 특정 타입의 학습 활동만 담는 제네릭 레포지토리
 */
public class ActivityRepository<T extends LearningActivity> {

    /** CSV 파일의 컬럼 순서. 헤더 행과 데이터 행 모두 이 순서를 따른다. */
    private static final String CSV_HEADER =
            "type,title,minutes,visibility,tags,instructorName,completionRate,bookTitle";

    private final List<T> storage = new ArrayList<>();

    public void add(T activity) {
        if(activity == null) {
            throw new IllegalArgumentException("저장할 활동은 null일 수 없습니다.");
        }
        storage.add(activity);
    }

    // 저장된 모든 활동을 반환한다.
    public List<T> findAll() {
        return Collections.unmodifiableList(storage); // 외부에서 함부러 수정못하게하기 위해서
    }

    // 조건에 맞는 활동들만 골라 변환한다.
    public List<T> filter(Predicate<T> predicate) {
       List<T> result = new ArrayList<>();
        for (T activity : storage) {
            if(predicate.test(activity)) {
                result.add(activity);
            }
        }
        return result;
    }

    // 조건에 맞는 첫번째 활동만 골라 변환한다.
    // 반환값이 T로 하면 조건을 하나도 만족하지 못하면 리턴을 하지 못하고 반복문이 끝나버린다. -> null을 리턴함
    // Optional로 포장해서 줌 -> 그래서 포장한걸 확인하고 값을 꺼내기 -> null을 보내지 않기 위해서 Optional 사용
    public Optional<T> findfirst(Predicate<T> predicate) {
        for (T activity : storage) {
            if(predicate.test(activity)) {
                return Optional.of(activity);
            }
        }
        return Optional.empty();
    }

    // 저장된 활동 수를 반환한다.
    public int count() {
        return storage.size();
    }

    // 저장된 모든 활동의 총 학습 시간(분)을 반환한다.
    public int getTotalMinutes() {
        int total = 0;
        for (T activity : storage) {
            total += activity.getMinutes(); // T가 LearningActivity의 자식이기 때문에 getTotalMinutes()을 호출할 수 있음.
        }
        return total;
    }

    // CSV 영속화 -----------------------------------------------------------------------

    public void saveToFile(Path csvPath) throws IOException { // 경로 정보를 담아줄 수 있는 타입의 객체로 보내세요 -> Path
        Path parent = csvPath.getParent(); // 경로가 실제로 존재하는지 확인하기 위해 부모경로를 부름
        if (parent != null) {
            Files.createDirectories(parent); // .createDirectories() 예외처리가 필요함, try-catch 안 쓸거면 throws 필수
        }

        // 파일 입출력을 담당하는 BufferedWriter(문자 기반 스트림)
        // 첫번째 매개값: 파일 경로, 두번째 매개값: 문자열 인코딩 방식 (한글 작성 시 UTF-8)
        // newBufferedWriter 이 친구도 예외처리가 필요함.
        // try-with-resource: AutoCloseable 인터페이스의 구현체인 경우 자동으로 close()를 진행해주는 문법
        try (BufferedWriter writer = Files.newBufferedWriter(csvPath, StandardCharsets.UTF_8)) {
            writer.write(CSV_HEADER);
            writer.newLine();

            for (T activity : storage) {
                writer.write((toCsvRow(activity)));
                writer.newLine();
            }
        }

    }

    /**
     * 활동을 CSV 한 행으로 직렬화한다.
     */
    private String toCsvRow(T activity) {
        String type = activity.getCategory().name();
        String title = activity.getTitle();
        String minutes = String.valueOf(activity.getMinutes());
        String visibility = activity.getVisibility().name();
        String tags = String.join(";", activity.getTags());

        String instructorName = "";
        String completionRate = "";
        String bookTitle = "";

        if (activity instanceof LectureLog) {
            instructorName = ((LectureLog) activity).getInstructorName();
        } else if (activity instanceof PracticeLog) {
            completionRate = String.valueOf(((PracticeLog) activity).getCompletionRate());
        } else if (activity instanceof ReadingLog) {
            bookTitle = ((ReadingLog) activity).getBookTitle();
        }

        return String.join(",",
                type, title, minutes, visibility, tags,
                instructorName, completionRate, bookTitle);
    }


}
