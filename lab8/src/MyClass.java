public class MyClass implements Comparable<MyClass> {
    private String value;

    public MyClass(String curr) {
        value = curr;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public int compareTo(MyClass obj) {
        return this.value.compareTo(obj.value);
    }
}
