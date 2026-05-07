package basic.array;

public class javaStater {

    public static void main(String[] args) {
        // 주석(comments)은 프로그램의 실행에 영향을 주지 않습니다.
        // 인텔리제이 출력 함수 생성 : sout
        System.out.println("Hello World!");

        String title = "Java 변수와 자료형";
        int studyMinites = 90;
        int targetMinites = 120;
        int extraMinites = 60;
        // 자료형은 처음 선언에만 사용, 그 이후는 X -> 자료형을 넣으면 새로운 변수로 인식
        studyMinites = 100;

        boolean isPublic = true;
        char level = 'A';

        // 자바에서 int와 int 연산 결과는 항상 int 입니다.
        // 그래서 소수점을 표현하고 싶다면 연산하고자 하는 값의 타입을 실수형(double)로 변경해야합니다.
        double progressRate = (double)studyMinites / targetMinites;
       boolean isEnoughStudy = studyMinites >= 60;

        System.out.println("제목: " + title);
        System.out.println("학습시간: " + studyMinites + "분");
        System.out.println("목표시간: " + targetMinites  + "분");
        System.out.println("추가시간: " + extraMinites);
        System.out.println("중요도: " + level);
        System.out.println("진도율: "+ progressRate + "%");
        System.out.println("충분히 학습했나요? " + isEnoughStudy);

    }
}