package edu.sapi.mestint;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;


public class PerceptronWindow extends Canvas {

    private static int WIDTH = 400;
    private static int HEIGHT = 400;
    private List<Point> points = new ArrayList<>();
    private List<Line> lines = new ArrayList<>();

    public PerceptronWindow(String title) throws HeadlessException {
        super();
        this.setBackground(Color.white);
        JFrame frame = new JFrame(title);
        frame.add(this);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(WIDTH, HEIGHT);
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D gr = (Graphics2D) g;
        gr.setStroke(new BasicStroke(3F));
        for (Point p : points) {
            gr.setColor(p.color);
            gr.fillOval(p.x, p.y, 20, 20);
        }
        for (Line l : lines) {
            gr.setColor(l.color);
            gr.drawLine(l.x1, l.y1, l.x2, l.y2);
        }
    }

    public void drawPoint(int x, int y, Color color) {
        points.add(new Point(new Integer(x), new Integer(y), color));
        this.repaint();
    }

    public void drawLine(int x1, int y1, int x2, int y2, Color color) {
        lines.add(new Line(x1, y1, x2, y2, color));
        this.repaint();
    }

    static class Point {
        int x;
        int y;
        Color color;

        public Point(int x, int y, Color color) {
            this.x = x;
            this.y = y;
            this.color = color;
        }
    }

    static class Line {
        int x1;
        int y1;
        int x2;
        int y2;
        Color color;

        public Line(int x1, int y1, int x2, int y2, Color color) {
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
            this.color = color;
        }
    }
}
