import java.util.Objects;

public class Person {
    private String name;
    private Integer age;
    private Dog dog;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(name, person.name) && Objects.equals(age, person.age) && Objects.equals(dog, person.dog);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age, dog);
    }

    public Person() {
    }

    public Person(String name, Integer age, Dog dog) {
        this.name = name;
        this.age = age;
        this.dog = dog;
    }

    /**
     * 获取
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * 设置
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取
     * @return age
     */
    public Integer getAge() {
        return age;
    }

    /**
     * 设置
     * @param age
     */
    public void setAge(Integer age) {
        this.age = age;
    }

    /**
     * 获取
     * @return dog
     */
    public Dog getDog() {
        return dog;
    }

    /**
     * 设置
     * @param dog
     */
    public void setDog(Dog dog) {
        this.dog = dog;
    }

    public String toString() {
        return "Person{name = " + name + ", age = " + age + ", dog = " + dog + "}";
    }
}
