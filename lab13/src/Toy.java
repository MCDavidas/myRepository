import java.util.Objects;
import java.util.Scanner;

public class Toy {
    private final String name;
    private final int price;
    private final int minYear;
    private final int maxYear;

    public Toy(String name, int price, int minYear, int maxYear) {
        this.name = name;
        this.price = price;
        this.minYear = minYear;
        this.maxYear = maxYear;
    }

    public int getPrice() {
        return price;
    }

    public int getMinYear() {
        return minYear;
    }

    public int getMaxYear() {
        return maxYear;
    }

    public static Toy read(Scanner scanner) {
        return new Toy(scanner.next(), Integer.parseInt(scanner.next()),
                Integer.parseInt(scanner.next()), Integer.parseInt(scanner.next()));
    }

    @Override
    public String toString() {
        return "name='" + name + '\'' +
                ", price=" + price +
                ", minYear=" + minYear +
                ", maxYear=" + maxYear;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Toy toy = (Toy) o;
        return price == toy.price &&
                minYear == toy.minYear &&
                maxYear == toy.maxYear &&
                Objects.equals(name, toy.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price, minYear, maxYear);
    }
}
