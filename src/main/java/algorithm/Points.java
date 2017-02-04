package algorithm;

import com.google.common.collect.Lists;
import org.eclipse.swt.graphics.Point;

import java.util.List;
import java.util.Random;

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

    private double dist(DDuble center, Point point) {
        double dx = center.a - point.x;
        double dy = center.b - point.y;
        return dx * dx + dy * dy;
    }

    private double dist(Point p1, Point p2) {
        return dist(new DDuble(p1), p2);
    }

    public List<List<Point>> evaluatedKeans(int k) {
        List<ExPoint> result = Lists.newArrayList();
        double value = 0.0;
        for (int i = 0; i < 50; i++) {
            List<ExPoint> exKmeans = getExKmeans(k);
            if (evalue(k, exKmeans) > value) {
                value = evalue(k, exKmeans);
                result = exKmeans;
            }
        }
        return calcuResult(k, result);
    }

    private double evalue(int k, List<ExPoint> exPoints) {
        double ssv = 0.0;
        for (ExPoint exPoint : exPoints) {
            ssv += evalue(k, exPoint, exPoints);
        }
        return ssv / exPoints.size();
    }

    private double evalue(int k, ExPoint exPoint, List<ExPoint> exPoints) {
        double sav = 0.0;
        int aCount = -1;
        double[] bvs = new double[k];
        for (int i = 0; i < k; i++) {
            bvs[i] = 0.0;
        }

        for (ExPoint other : exPoints) {
            Point otherPoint = other.point;
            int otherGroupId = other.getGroupId();
            double dist = dist(otherPoint, exPoint.point);

            if (exPoint.getGroupId() == otherGroupId) {
                sav += dist;
                aCount++;
            } else {
                if (bvs[otherGroupId] == 0.0) {
                    bvs[otherGroupId] = dist;
                } else if (dist < bvs[otherGroupId]) {
                    bvs[otherGroupId] = dist;
                }
            }
        }
        double sbv = 0.0;
        for (double bv : bvs) {
            sbv += bv;
        }

        double av = aCount == 0 ? 0.0 : sav / aCount;
        double bv = sbv / (k - 1);
        double sv = (bv - av) / Math.max(av, bv);
        return sv;
    }


    public List<List<Point>> kmeans(int k) {
        return calcuResult(k, getExKmeans(k));
    }

    private List<ExPoint> getExKmeans(int k) {
        if (n < k) {
            throw new RuntimeException();
        }
        List<ExPoint> exPoints = Lists.newArrayList();
        initExpoints(k, exPoints);

        while (doKmeans(exPoints, k)) ;

        return exPoints;
    }

    private List<List<Point>> calcuResult(int k, List<ExPoint> exPoints) {
        List<List<Point>> result = Lists.newArrayList();
        for (int i = 0; i < k; i++) {
            result.add(Lists.newArrayList());
        }
        for (ExPoint exPoint : exPoints) {
            result.get(exPoint.getGroupId()).add(exPoint.point);
        }
        return result;
    }

    private void initExpoints(int k, List<ExPoint> exPoints) {
        for (Point point : points) {
            Random random = new Random();
            exPoints.add(new ExPoint(point, random.nextInt(k)));
        }
    }

    private boolean doKmeans(List<ExPoint> exPoints, int k) {
        boolean result = false;
        List<Point> centers = Lists.newArrayList();
        for (int i = 0; i < k; i++) {
            centers.add(getCenter(exPoints, i));
        }
        for (ExPoint exPoint : exPoints) {
            Point point = exPoint.point;
            int index = getIndex(centers, point);
            result |= exPoint.changeGroupIdTo(index);
        }
        return result;
    }

    private int getIndex(List<Point> centers, DDuble point) {
        Point temp = centers.get(0);
        int index = 0;
        for (int i = 1; i < centers.size(); i++) {
            Point center = centers.get(i);
            if (dist(point, center) < dist(point, temp)) {
                temp = center;
                index = i;
            }
        }
        return index;
    }

    private int getIndex(List<Point> centers, Point point) {
        return getIndex(centers, new DDuble(point));
    }

    private Point getCenter(List<ExPoint> exPoints, int i) {
        double sx = 0.0;
        double sy = 0.0;
        int count = 0;
        List<Point> group = Lists.newArrayList();
        for (ExPoint exPoint : exPoints) {
            if (exPoint.getGroupId() == i) {
                Point point = exPoint.point;
                sx += point.x;
                sy += point.y;
                count++;
                group.add(point);
            }
        }
        DDuble dDuble = new DDuble(sx / count, sy / count);
        Point center = group.get(getIndex(group, dDuble));
        return center;
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
