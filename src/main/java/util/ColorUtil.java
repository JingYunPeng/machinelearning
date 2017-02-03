package util;

import com.google.common.collect.Lists;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Display;

import java.util.List;

/**
 * Created by Administrator on 2017/1/27.
 */
public class ColorUtil {
    private static final Display DISPLAY = Display.getDefault();
    public static final Color BLACK = DISPLAY.getSystemColor(SWT.COLOR_BLACK);
    public static final Color WHITE = DISPLAY.getSystemColor(SWT.COLOR_WHITE);
    public static final Color RED = DISPLAY.getSystemColor(SWT.COLOR_RED);
    public static final Color GREEN = DISPLAY.getSystemColor(SWT.COLOR_GREEN);
    public static final Color YELLOW = DISPLAY.getSystemColor(SWT.COLOR_YELLOW);
    public static final Color BLUE = DISPLAY.getSystemColor(SWT.COLOR_BLUE);
    public static final List<Color> COLORS = Lists.newArrayList(BLACK, RED, GREEN, YELLOW, BLUE);
}
