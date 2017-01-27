package algorithm;

import com.google.common.collect.Lists;
import org.eclipse.swt.graphics.Point;

import java.util.List;

/**
 * Created by Administrator on 2017/1/27.
 */
public class Points {
    private List<Point> points;
    private double sx = 0.0;
    private double sy = 0.0;
    private double sxy = 0.0;
    private double sx2 = 0.0;
    private int n = 0;

    public Points(List<Point> points) {
        this.points = points;
        for (Point point : points) {
            sx += point.x;
            sy += point.y;
            sxy += point.x * point.y;
            sx2 += point.x * point.x;
        }
        n = points.size();
    }

    public DDuble curve() {
        double a = (sy * sx / n - sxy) / (sx * sx / n - sx2);
        double b = (sy - a * sx) / n;
        return new DDuble(a, b);
    }

    public List<List<Point>> kmeans(int k) {
        List<List<Point>> result = Lists.newArrayList();
        if (n < k) {
            throw new RuntimeException();
        }
        List<List<Point>> temp = copy(result);
        return null;
    }

    private List<List<Point>> copy(List<List<Point>> ori) {
        List<List<Point>> copy = Lists.newArrayList();
        for (List<Point> pointList : ori) {
            List<Point> copyPointList = Lists.newArrayList();
            for (Point point : pointList) {
                copyPointList.add(point);
            }
            copy.add(copyPointList);
        }
        return copy;
    }

    private boolean allEquals(List<List<Point>> temp, List<List<Point>> last) {
        for (List<Point> pointList : temp) {
            boolean notFound = true;
            for (List<Point> list : last) {
                if (equals(pointList, list)) {
                    notFound = false;
                    break;
                }
            }
            if (notFound) {
                return false;
            }
        }
        return true;
    }

    private boolean equals(List<Point> temp, List<Point> last) {
        if (temp.size() != last.size()) {
            return false;
        }
        for (Point tempPoint : temp) {
            boolean notFound = true;
            for (Point lastPoint : last) {
                if (equals(tempPoint, lastPoint)) {
                    notFound = false;
                    break;
                }
            }
            if (notFound) {
                return false;
            }
        }
        return true;
    }

    private boolean equals(Point temp, Point last) {
        return temp.x == last.x && temp.y == last.y;
    }

}
