package etc.api.optional;

import java.util.*;

public class OptionalExample {

    public static void main(String[] args) {

        Map<String, String> phoneByName = new HashMap<>();
        phoneByName.put("kim", "010-1234-5678");

        String phone = phoneByName.get("lee");   // 없음  -> null을 반환
//        System.out.println(phone.length());      // NullPointerException!


        // .ifPresent 값이 존재한다면 람다 실행, 없거나 null이라면 실행 안함
        // Optional.ofNullable(str);
        String str = "kim";
        Optional<String> name = Optional.of(str);
        name.ifPresent(n-> System.out.println("환영합니다, " + n));
        System.out.println(name.isPresent()); // 존재하면 true / 존재하지 않으면 false로 알려줌


        System.out.println("=============================================");

        List<User> userList = List.of(
                new User("kim", "kim1234@naver.com", 24),
                new User("lee", "lee1234@naver.com", 20),
                new User("choi", "choi1234@naver.com", 32),
                new User("park", "park1234@naver.com", 28),
                new User("jung", "jung1234@naver.com", 40)
        );

        int max = userList.stream()
                .filter(user -> user.getAge() >= 25)
                .mapToInt(User::getAge)
                .max()
                .orElse(0); // Optional 메서드 -> 값이 존재하지 않으면 지정한 값으로 나옴
//                .orElseThrow(RuntimeException::new); // Optional 메서드 - > 만약 값이 없으면 예외를 터뜨림
        System.out.println("max = " + max);



    }
}
