package oop.enum_exception.domain;

import oop.enum_exception.policy.*;

// LectureLog는 LearningActivity의 한 종류이고, Reviewable에 선언된 역할도 수행할 수 있다.
public class LectureLog extends LearningActivity implements Reviewable, Shareable {

    private String instructorName;

    public LectureLog(String title, int minutes, Visibility visibility, String instructorName) {

        super(title, minutes, visibility, ActivityCategory.LECTURE);
        this.instructorName = normalizeInstructorName(instructorName);
    }



    @Override
    public boolean needsReview() {
        return getCategory().isShortStudy(getMinutes());
    }

    @Override
    public void printReviewTarget() {
        System.out.println("[복습권장] " + getTitle() + " ("+ getMinutes() + "분)");
    }

    public void method1() {
        return;
    }

    private String normalizeInstructorName(String instructorName) {
        if(instructorName == null || instructorName.isBlank()) {
            return "강사 미정";
        }

        return instructorName;

    }


    @Override
    public boolean canShare() {
        return ispublicActivity();
    }

    @Override
    public String getShareTitle() {
        return getTitle();
    }

    @Override
    public String getActivityType() {
        return "강의";
    }

    @Override
    public String getDetailText() {
        return "강사: " + instructorName;
    }
}

