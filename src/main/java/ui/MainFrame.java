package ui;

import algorithm.DDuble;
import algorithm.Points;
import com.google.common.collect.Lists;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.GCData;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Canvas;
import util.ColorUtil;
import util.Frame;
import util.SWTUtil;

import java.util.List;

/**
 * Created by jing on 2017/1/24.
 */
enum Tool {
    POINT, LINE, NONE
}

public class MainFrame extends Frame {

    private Tool tool;
    private boolean drawing;
    private List<Point> points;
    private GC gc;
    private Canvas canvas;

    public MainFrame() throws Exception {
    }

    @Override
    protected void init() {
        drawing = false;
        points = Lists.newArrayList();
        tool = Tool.NONE;
        shell.setLayout(new GridLayout(12, false));

        Button pointBtn = SWTUtil.createButton(shell, "Point", () -> {
            tool = Tool.POINT;
        });
        Button lineBtn = SWTUtil.createButton(shell, "Line", () -> {
            tool = Tool.LINE;
        });
        Button clearBtn = SWTUtil.createButton(shell, "Clear", () -> {
            clear();
        });
        Button curveBtn = SWTUtil.createButton(shell, "curve", () -> {
            curve();
        });
        SWTUtil.pack(12, pointBtn, lineBtn, clearBtn);

        canvas = new Canvas(shell, SWT.BORDER);
        gc = new GC(canvas);
        canvas.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseDown(MouseEvent e) {
                switch (tool) {
                    case LINE:
                        break;
                    case POINT:
                        gc.setBackground(ColorUtil.BLACK);
                        gc.fillOval(e.x, e.y, 7, 7);
                        points.add(new Point(e.x, e.y));
                        break;
                    case NONE:
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void mouseUp(MouseEvent e) {

            }
        });
        canvas.setLayoutData(SWTUtil.createGridData(12, 1, 700, 500));

        Button pointBtn2 = SWTUtil.createButton(shell, "Point", () -> {
            System.out.println("click point");
        });
        pointBtn2.setLayoutData(SWTUtil.createGridData(12, 1));
    }

    private void curve() {
        DDuble dDuble = new Points(points).curve();
        double a = dDuble.a;
        double b = dDuble.b;
        if (gc != null) {
            gc.setBackground(ColorUtil.WHITE);
            int x = canvas.getSize().x;
            int y = (int) (a * x + b);
            gc.drawLine(0, (int) b, x, y);
        }
    }

    private void clear() {
        points.clear();
        if (gc != null) {
            if (canvas != null) {
                Color temp = gc.getBackground();
                gc.setBackground(ColorUtil.WHITE);
                Point size = canvas.getSize();
                gc.fillRectangle(0, 0, size.x, size.y);
                gc.setBackground(temp);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        MainFrame mf = new MainFrame();
    }
}
