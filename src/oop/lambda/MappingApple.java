package oop.lambda;

import java.util.*;

public class MappingApple {

    // 사과에서 색상만 추출하자
    public static List<Color> mappingAppleByColor(List<Apple> apples) {
        List<Color> colorList = new ArrayList<>();
        for (Apple apple : apples) {
            colorList.add(apple.getColor());
        }
        return colorList;
    }



    // 어떤 타입의 리스트를 받아도 맵핑이 가능한 전역 메서드를 선언해 보자 (X -> Y)
    // X에서 Y를 뽑아내야한다
    public static <X, Y> List<Y> map(List<X> list, GenericFunction<X, Y> mapper) {
        List<Y> mappedList = new ArrayList<>();
        for (X x : list) {
            Y y = mapper.apply(x);
            mappedList.add(y);
        }

        return mappedList;
    }

}
