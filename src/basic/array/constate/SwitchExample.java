package basic.array.constate;

public class SwitchExample {

    public static void main(String[] args) {

        String gender = "z";

        // if문처럼 논리형 조건식이 들어가는게 아니라
        // 분기를 나눌 기준값이 괄호 안에 들어갑니다. (문자열, 정수만 가능)
        switch (gender) {
            case "m" : case "M" : case "남자" : case "ㅡ" :
                System.out.println("남자입니다.");
                break;  // 해당 case만 실행하고 switch문을 종료해라!

            // java 14버전 이후 개선된 case문
            case "F", "f", "ㄹ", "여" :
                System.out.println("여자입니다.");
                break;

            default: // case를 설정하지 않은 값들은 모두 default로 빠짐.
                System.out.println("잘못된 성별입니다.");
        }
    }
}
