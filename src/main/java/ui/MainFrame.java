package ui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Canvas;
import util.Frame;
import util.SWTUtil;

/**
 * Created by jing on 2017/1/24.
 */
public class MainFrame extends Frame {
    private enum Tool {
        POINT, LINE, NONE
    }

    private Tool tool = Tool.NONE;

    public MainFrame() throws Exception {
    }

    @Override
    protected void init() {
        shell.setLayout(new FillLayout());
        Button pointBtn = SWTUtil.createButton(shell, "Point", () -> {
            System.out.println("click point");
        });


        Canvas canvas = new Canvas(shell, SWT.NO_REDRAW_RESIZE);
        canvas.addPaintListener(new PaintListener() {
            public void paintControl(PaintEvent e) {
                Rectangle clientArea = canvas.getClientArea();
                e.gc.setBackground(display.getSystemColor(SWT.COLOR_CYAN));
                e.gc.fillOval(0, 0, clientArea.width, clientArea.height);
            }
        });
    }

    public static void main(String[] args) throws Exception {
        MainFrame mf = new MainFrame();
    }
}
