package etc.api.lang.object;

import java.util.*;


    public class Person implements Cloneable {

    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == this) return true; // 주소값이 같다면 같은 객체야!
        if (obj instanceof Person) {
            Person p = (Person) obj;
            if (p.age == this.age && p.name.equals(this.name)) return true;
        }
        return false; // Person 타입을 가질 수 없다면 동등 비교 의미가 없음!
    }

        @Override
        protected Object clone() throws CloneNotSupportedException {
            // 객체 복사 메서드
            return super.clone();
        }

        //equals를 오버라이딩 할 때는 hashCode도 함께 오버라이딩을 진행해 주시는 것이 좋습니다. * 강력하게 권장
    //equals를 재 정의한다는 것은 내가 원하는 방식의 동등 비교를 하겠다는 것이기 때문에
    //hashcode의 값도 그에 맞게 같이 재 정의가 되어야 합니다. (hash 주소를 equals에 맞게 정의)
    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }

    // toString(): 객체의 정보(필드)를 문자열 형태로 리턴.
    // Object가 물려주는 toString은 패키지 + 클래스 이름 + 주소값 -> 잘 쓸일이 없다.
    // 대부분 오버라이딩 해서 사용하는 편.
    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }



}
