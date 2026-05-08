package oop.abstract_interface.domain;

import oop.abstract_interface.policy.*;

// LectureLog는 LearningActivity의 한 종류이고, Reviewable에 선언된 역할도 수행할 수 있다.
public class LectureLog extends LearningActivity implements Reviewable, Shareable {

    private String instructorName;

    public LectureLog(String title, int minutes, boolean publicActivity, String instructorName) {

        super(title, minutes, publicActivity);
        this.instructorName = normalizeInstructorName(instructorName);
    }

    @Override
    public void printSummary() {
        System.out.println("[강의] #" + getId() + " " + getTitle() + " - " + getMinutes()
                + "분 - 강사: " + instructorName);
    }

    @Override
    public boolean needsReview() {
        return getMinutes() < 60;
    }

    @Override
    public void printReviewTarget() {
        printSummary();
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
}

