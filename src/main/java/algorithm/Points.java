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

    private double dist(DDuble center, DDuble point) {
        double dx = center.a - point.a;
        double dy = center.b - point.b;
        return dx * dx + dy * dy;
    }

    public List<List<Point>> kmeans(int k) {
        if (n < k) {
            throw new RuntimeException();
        }
        List<ExPoint> exPoints = Lists.newArrayList();
        for (Point point : points) {
            exPoints.add(new ExPoint(point, k - 1));
        }

        for (int i = 0; i < k; i++) {
            exPoints.get(i).setGroupId(i);
        }


        while (doKmeans(exPoints, k)) ;


        List<List<Point>> result = Lists.newArrayList();
        for (int i = 0; i < k; i++) {
            result.add(Lists.newArrayList());
        }
        for (ExPoint exPoint : exPoints) {
            result.get(exPoint.getGroupId()).add(exPoint.point);
        }
        System.out.println("result = " + result);
        for (List<Point> pointList : result) {
            System.out.println("pointList.size() = " + pointList.size());
        }
        return result;
    }

    private boolean doKmeans(List<ExPoint> exPoints, int k) {
        boolean result = false;
        List<DDuble> centers = Lists.newArrayList();
        for (int i = 0; i < k; i++) {
            centers.add(getCenter(exPoints, i));
        }
        for (ExPoint exPoint : exPoints) {
            DDuble point = new DDuble(exPoint.point);
            int index = getIndex(k, centers, point);
            result |= exPoint.changeGroupIdTo(index);
        }
        return result;
    }

    private int getIndex(int k, List<DDuble> centers, DDuble point) {
        DDuble temp = centers.get(0);
        int index = 0;
        for (int i = 1; i < k; i++) {
            DDuble center = centers.get(i);
            if (dist(point, center) < dist(point, temp)) {
                temp = center;
                index = i;
            }
        }
        return index;
    }

    private DDuble getCenter(List<ExPoint> exPoints, int i) {
        double sx = 0.0;
        double sy = 0.0;
        int count = 0;
        for (ExPoint exPoint : exPoints) {
            if (exPoint.getGroupId() == i) {
                sx += exPoint.point.x;
                sy += exPoint.point.y;
                count++;
            }
        }
        return new DDuble(sx / count, sy / count);
    }

    private class ExPoint {
        private int groupId;
        private final Point point;

        public ExPoint(Point point) {
            this.point = point;
        }

        public ExPoint(Point point, int k) {
            this.point = point;
            this.groupId = k;
        }

        public int getGroupId() {
            return groupId;
        }

        public void setGroupId(int groupId) {
            this.groupId = groupId;
        }

        public boolean changeGroupIdTo(int index) {
            boolean changed = groupId != index;
            setGroupId(index);
            return changed;
        }
    }

    public static void main(String[] args) {
        List<Point> ps = Lists.newArrayList();
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j <= i; j++) {
                int p = i * 10 + j;
                ps.add(new Point(p, p));
            }
        }
        new Points(ps).kmeans(5);
    }
}
