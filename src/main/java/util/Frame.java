package util;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

/**
 * Created by jing on 2017/1/24.
 * frame for ui
 */
public abstract class Frame {
    protected Display display;
    protected Shell shell;

    public Frame() throws Exception {
        this("fram");
    }

    public Frame(String title) throws Exception {
        this(title, 800, 700);
    }

    public Frame(String title, int width, int height) throws Exception {
        display = Display.getDefault();
        shell = new Shell();
        shell.setSize(width, height);
        shell.setText(title);
        init();
        shell.open();
        shell.layout();
        while (!shell.isDisposed()) {
            if (!display.readAndDispatch()) {
                display.sleep();
            }
        }
    }

    protected abstract void init();
}
