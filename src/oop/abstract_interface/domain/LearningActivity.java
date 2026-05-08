package oop.abstract_interface.domain;

public abstract class LearningActivity {

    private static int totalCreateCount = 0;

    private final long id;
    private String title;
    private int minutes;
    private boolean publicActivity;

    LearningActivity(String title, int minutes) {

        this(title, minutes, true);
    }

    public LearningActivity(String title, int minutes, boolean publicActivity) {
        totalCreateCount++;
        this.id = totalCreateCount;
        this.title = normalizeTitle(title);
        this.minutes = minutes;
        this.publicActivity = publicActivity;
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
        this.publicActivity = false;
    }

    public  void openTobePublic() {
        this.publicActivity = true;
    }


   // 추상메서드는 메서드 바디가 없는 메서드의 툴 역할을 합니다.
   // 특정 메서드는 부모가 대충 정해버리는 것보다, 자식이 반드시 자기 방식대로 구현하게 하는 편이 명확할 때가 있습니다.
   // 추상메서드는 자식에게 물려줄 때 오버라이팅을 강제합니다. 반드시 구현해야합니다.
    public abstract void printSummary();

    public static int getTotalCreateCount() {
        return totalCreateCount;
    }

    public String getTitle() {
        return title;
    }

    public int getMinutes() {
        return minutes;
    }

    public boolean ispublicActivity() {
        return publicActivity;
    }

    public long getId() {
        return id;
    }
}
