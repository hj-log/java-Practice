package oop.static_final;

public class LearningLog {

    // 상수(constant)는 대문자로 작성하는 것이 관례
    // 상수는 고정된 불변의 값을 의미, 모든 객체가 하나의 값을 공유하며, 코드를 절대 수정하지 못하는 값
    private static final int REVIEW_THRESHOLD_MINUTES = 60;

    // 지금까지 LearningLog 객체가 몇 개 만들어졌는지 세는 변수
    private static int totalCreateCount = 0;

    private final long id;
    private String title;
    private int minutes;
    private boolean publicLog;

    LearningLog(String title, int minutes) {

        this(title, minutes, true);
    }

    LearningLog(String title, int minutes, boolean publicLog) {
        totalCreateCount++;
        this.id = totalCreateCount;
        this.title = normalizeTitle(title);
        this.minutes = minutes;
        this.publicLog = publicLog;
    }

    public void setMinutes(int minutes) {
        if (minutes <= 0) {
            System.out.println("잘못된 공부 시간입니다.");
            return; // void 메서드에서 return은 메서드를 강제 종료합니다.
        }
        this.minutes += minutes;
    }

    public void setTitle(String newTitle) {
        this.title = normalizeTitle(newTitle);
    }

    private String normalizeTitle(String newTitle) {
        if (newTitle == null || newTitle.isBlank()) {
            return "제목 없음";
        }

        return newTitle;
    }

    public  void hideFromPublic() {
        this.publicLog = false;
    }

    public  void openTobePublic() {
        this.publicLog = true;
    }


    void printSummary() {
        String visibility = publicLog ? "공개" : "비공개";
        System.out.println("#" + id +". " + this.title + " - " + this.minutes + "분 - " + visibility);
    }

    boolean needsReview() {

        return minutes < REVIEW_THRESHOLD_MINUTES;
    }

    public static int getTotalCreateCount() {
        return totalCreateCount;
    }

    public String getTitle() {
        return title;
    }

    public int getMinutes() {
        return minutes;
    }

    public boolean isPublicLog() {
        return publicLog;
    }
}
