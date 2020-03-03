import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Line {
    private Color color;
    private List<Integer> xList = new ArrayList<>();
    private List<Integer> yList = new ArrayList<>();

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public List<Integer> getxList() {
        return xList;
    }

    public void setxList(List<Integer> xList) {
        this.xList = xList;
    }

    public List<Integer> getyList() {
        return yList;
    }

    public void setyList(List<Integer> yList) {
        this.yList = yList;
    }

    public Line(Color c) {
        color = c;
    }

    public void addPoint(int x, int y) {
        xList.add(x);
        yList.add(y);
    }

    public void drawLine(Graphics g) {
        for (int i = 0; i < xList.size() - 1; i++) {
            g.setColor(color);
            g.drawLine(xList.get(i), yList.get(i), xList.get(i + 1), yList.get(i + 1));
        }
    }

}
