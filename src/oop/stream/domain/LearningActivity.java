package oop.stream.domain;

import oop.stream.exception.*;

import java.util.*;

public abstract class LearningActivity {

    private static int totalCreateCount = 0;

    private final long id;
    private String title;
    private int minutes;
    private Visibility visibility;
    private final ActivityCategory category;

//    LearningActivity(String title, int minutes) {
//
//        this(title, minutes, Visibility.PUBLIC );
//    }

    public LearningActivity(String title, int minutes, Visibility visibility, ActivityCategory category) {
        validateTitle(title);
        validateMinutes(minutes);
        totalCreateCount++;
        this.id = totalCreateCount;
        this.title = title.trim(); // 좌우 공백 제거
        this.minutes = minutes;
        this.visibility = visibility;
        this.category = category;
    }

    public static int getTotalCreatedCount() {
        return totalCreateCount;
    }

    //
    public void extendStudy(int additionalMinutes) {
        if (additionalMinutes <= 0) {
            throw new InvalidActivityException(
                    "추가 학습 시간은 1분 이상이어야 합니다. 입력값: " + additionalMinutes);
        }
        this.minutes += additionalMinutes;
    }

    public void changeTitle(String newTitle) {
        validateTitle(newTitle);
        this.title = newTitle;
    }

    private void validateTitle(String newTitle) {
        if (newTitle == null || newTitle.isBlank()) {
            throw new InvalidActivityException("학습 제목은 비워둘 수 없습니다.");
        }
    }

    private void validateMinutes(int newMinutes) {
        if(newMinutes <= 0) {
            throw new InvalidActivityException("학습 시간은 1분 이상이여야 합니다. 입력값: " + newMinutes);
        }
    }

    public void hideFromPublic() {
        this.visibility = Visibility.PUBLIC;
    }

    public void openTobePublic() {
        this.visibility = Visibility.PRIVATE;
    }


    // 추상메서드는 메서드 바디가 없는 메서드의 툴 역할을 합니다.
    // 특정 메서드는 부모가 대충 정해버리는 것보다, 자식이 반드시 자기 방식대로 구현하게 하는 편이 명확할 때가 있습니다.
    // 추상메서드는 자식에게 물려줄 때 오버라이팅을 강제합니다. 반드시 구현해야합니다.
    public abstract String getActivityType();

    public abstract String getDetailText();

    public static int getTotalCreateCount() {
        return totalCreateCount;
    }

    public String getTitle() {
        return title;
    }

    public int getMinutes() {
        return minutes;
    }

    public Visibility getvisibility() {
        return visibility;
    }

    public long getId() {
        return id;
    }

    public String getVisibilityText() {
        return this.visibility.getLabel();
    }

    public boolean isPublicActivity() {
        return visibility == Visibility.PUBLIC;
    }

    public ActivityCategory getCategory() {
        return category;
    }


    public String addTag(String s) {
        return s;
    }
}


