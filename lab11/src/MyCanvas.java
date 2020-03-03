import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MyCanvas extends JPanel {
    private List<MyListener> paintListeners = new ArrayList<>();
    private List<Line> lines;
    private Line currentLine;
    private Color color;

    public MyCanvas(List<Line> linesArr) {
        this.lines = linesArr;
        setBackground(Color.white);
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                currentLine = new Line(color);
                lines.add(currentLine);
                currentLine.addPoint(e.getX(), e.getY());
                firePainted(new Point(e.getX(), e.getY()));
            }
        });
        this.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                currentLine.addPoint(e.getX(), e.getY());
                firePainted(new Point(e.getX(), e.getY()));

                revalidate();
                repaint();
            }
        });
    }


    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Line line : lines) {
            line.drawLine(g);
        }
    }

    @Override
    public Dimension getPreferredSize() {
        int maxX = 0;
        int maxY = 0;
        for (Line line : lines) {
            maxX = Math.max(maxX, Collections.max(line.getxList()));
            maxY = Math.max(maxY, Collections.max(line.getyList()));
        }
        return new Dimension(maxX + 10, maxY + 10);
    }

    public BufferedImage getImage() {
        BufferedImage im = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_RGB);
        this.paint(im.getGraphics());
        return im;
    }

    public void addPaintListener(MyListener paintListener) {
        this.paintListeners.add(paintListener);
    }

    private void firePainted(Point point) {
        for (MyListener paintListener : paintListeners) {
            paintListener.painted(point);
        }
    }
}