package util;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Display;

/**
 * Created by Administrator on 2017/1/27.
 */
public class ColorUtil {
    private static final Display DISPLAY = Display.getDefault();
    public static final Color BLACK = DISPLAY.getSystemColor(SWT.COLOR_BLACK);
    public static final Color WHITE = DISPLAY.getSystemColor(SWT.COLOR_WHITE);
}
