package oop.stream;

import java.util.*;
import java.util.function.*;
import java.util.stream.*;

import static oop.stream.Menu.*;

public class Mapping {

    public static void main(String[] args) {

        // map : 컬렉션에서 원하는 데이터만 추출
        // original : [ {}, {}, {}, {}, {} ]
        // filter   : [ {}, {}, {} ] 객체의 원형을 필터링해서 원하는 것만
        // map      : [ "", "", "", "", "" ] 객체원형을 다른 형태로 담는 것

        // 요리 이름만 다 추출
        // toList(): 자바 16부터 쓸 수 있음. 불변 리스트 -> 수정 안됨!
        // collect(Collectors.toList()): 자바 8부터 존재하던 방식 -> 수정 자유로움
        List<String> nameLIst = MENU_LIST.stream()
               .map(Dish::getName)
               .collect(Collectors.toList());


        nameLIst.add("마라탕");
        System.out.println("nameLIst = " + nameLIst);

        System.out.println("=====================================================");

        List<String> browsers
        = List.of("safari", "chrome", "ms edge", "opera", " firefox");

        List<Integer> list = browsers.stream()
                .map(b -> b.length())
                .toList(); // 여기서 지역 변수 설정 (option + enter)
        System.out.println("list = " + list);

        List<Character> list2 = browsers.stream()
                .map(b -> b.toUpperCase().charAt(0))
                .toList();
        System.out.println("list2 = " + list2);

        System.out.println("=====================================================");

        // 메뉴 목록에서 메뉴 이름과 칼로리만 추출해서
        // 새로운 객체로 포장해서 받고 싶아
        List<SimpleDish> simpleDishes = MENU_LIST.stream()
                .map(SimpleDish::new)
                .toList();

        simpleDishes.forEach(System.out::println);

        System.out.println("=====================================================");

        /*
            메뉴 목록에서 칼로리가 500칼로리보다 큰
            음식들을 필터링한 다음에 음식의 이름과 타입만
            추출해서 출력해주세요. -> DishDetail이라는 객체로 맵핑
            단, 타입은 MEAT의 경우 육류라고 저장
            FISH는 어류라고 저장, OTHER는 기타라고 저장
         */
        MENU_LIST.stream()
                .filter(dish -> dish.getCalories() > 500)
                .map(DishDetail::new)
                .toList()
                .forEach(System.out::println);

        System.out.println("=====================================================");

        // 메뉴 목록에 있는 요리들의 총 칼로리 구하이
        int sum = MENU_LIST.stream()
                .mapToInt(Dish::getCalories)
                .sum();

        System.out.println("sum = " + sum);

        // 육류 메뉴의 평균 칼로리 구하기
        double meatAvgCal = MENU_LIST.stream()
                .filter(dish -> dish.getType() == Dish.Type.MEAT)
                .mapToInt(Dish::getCalories)
                .average()
                .getAsDouble();

        System.out.printf("meatAvgCal = %.2f", meatAvgCal);

        System.out.println("=====================================================");

//        String pets = "강아지, 고양이, 짹짹이, 코끼리";
//        String[] split = pets.split(", ");
//        System.out.println(Arrays.toString(split));
//
//        String s = "progrramming"; // 모든 문자를 하나씩 나누고 싶을 때
//        String[] split1 = s.split("");
//        System.out.println(Arrays.toString(split1));

        // 모든 요리 이름에 포함된 알파벳을 중복 없이 추출.
        // flatMap: map처럼 변환을 하긴 하는데, 그 결과는 반드시 stream 이어야 한다.
        // 여러개의 스트림을 단일 스트림으로 합쳐줍니다.
        List<String> stringList = MENU_LIST.stream()
                .map(dish -> dish.getName().split(""))
                .flatMap(arr -> Arrays.stream(arr))// 각각의 배열 스트림을 하나의 스트림을 통합
                .filter(s -> !s.isBlank()) // 각각의 단일 문자 중 공백을 제거하는 행위
                .distinct()
                .toList();

        System.out.println("stringList = " + stringList);


    }
}
