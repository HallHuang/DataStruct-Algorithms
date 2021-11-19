package sort;

/**
 * 一个基本数据类实现Comparable接口，可以进行对象的比较
 */
public class Student implements Comparable<Student> {

    private final int age;
    private final String name;

    public Student(int age, String name) {
        this.age = age;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Student{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public int compareTo(Student o) {
        return this.age - o.age;
    }

    public static Comparable getMax(Comparable c1, Comparable c2) {
        int result = c1.compareTo(c2);
        if (result >= 0) {
            return c1;
        } else {
            return c2;
        }
    }

    public static void main(String[] args) {
        Student s1 = new Student(23, "Jack");
        Student s2 = new Student(20, "Sarah");

        Comparable max = getMax(s1, s2);
        System.out.print(max.toString());

    }
}
