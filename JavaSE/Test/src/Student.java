public class Student {
    private int id;

    public Student() {
    }

    public Student(int id) {
        this.id = id;
    }

    /**
     * 获取
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * 设置
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    public String toString() {
        return "Student{id = " + id + "}";
    }
}
