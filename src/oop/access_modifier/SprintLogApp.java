package oop.access_modifier;

public class SprintLogApp {

    public static void main(String[] args) {

        LearningLog javaLog = new LearningLog("java 시작", 40);
        LearningLog gitLog = new LearningLog("git 복습", 30,false);

//        javaLog.title = "야호";
//        javaLog.minutes = -34567;
//        javalog.pubilcLog = false; 모든 필드가 private 접근 제한자를 지정했기 때문에 값을 창조하거나 수정이 불가능해집니다.


    }
}
