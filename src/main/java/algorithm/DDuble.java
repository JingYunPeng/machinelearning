package algorithm;

import org.eclipse.swt.graphics.Point;

/**
 * Created by Administrator on 2017/1/27.
 */
public class DDuble {
    public DDuble(double a, double b) {
        this.a = a;
        this.b = b;
    }

    public double a;
    public double b;

    public DDuble(Point point) {
        a = point.x;
        b = point.y;
    }

    public Point toPoint() {
        return new Point((int) a, (int) b);
    }
}
