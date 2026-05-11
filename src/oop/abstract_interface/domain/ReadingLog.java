package oop.abstract_interface.domain;

import oop.abstract_interface.policy.*;

public class ReadingLog extends LearningActivity implements Reviewable, Shareable {

    private String bookTitle;

    public ReadingLog(String title, int minutes, boolean publicActivity, String bookTitle) {
        super(title, minutes, publicActivity);
        this.bookTitle = bookTitle;
    }



    @Override
    public boolean needsReview() {
        return getMinutes() < 45;
    }

    @Override
    public void printReviewTarget() {
        System.out.println("[복습권장] " + getTitle() + " ("+ bookTitle + ")");
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
        return "독서";
    }

    @Override
    public String getDetailText() {
        return "책: " + bookTitle;
    }
}
