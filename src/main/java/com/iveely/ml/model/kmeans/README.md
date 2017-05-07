# Kmeans [![Build Status](https://travis-ci.org/Fanping/iveely.ml.svg?branch=master)](https://travis-ci.org/Fanping/iveely.ml)
How to use Kmeans ?

It's easy as follw:

```java
     Point<Integer>[] points = new Point[4];
     points[0] = new Point<>(1, 1);
     points[1] = new Point<>(2, 2);
     points[2] = new Point<>(-1, -1);
     points[3] = new Point<>(-2, -2);
     KmeansImp<Integer> kmeansImp = new KmeansImp<>(points,
             2,
             new IntPointCompare(), 100);
     points = kmeansImp.getPointsWithClusterId();
     assertEquals(points[0].getClusterId(), points[1].getClusterId());
     assertEquals(points[2].getClusterId(), points[3].getClusterId());
```
