/* Licensed to the Apache Software Foundation (ASF) under one
* or more contributor license agreements.  See the NOTICE file
* distributed with this work for additional information
* regarding copyright ownership.  The ASF licenses this file
* to you under the Apache License, Version 2.0 (the
* "License"); you may not use this file except in compliance
* with the License.  You may obtain a copy of the License at
*
* http:* www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/

package com.iveely.ml.model.kmeans;

import java.util.Arrays;

/**
 * Implementation of universal Kmeans clustering algorithm.
 *
 * Author:liufanping@iveely.com
 **/
public class KmeansImp<T> {

    // All data points.
    private Point<T>[] points;

    // Max iteration count.
    private int iterCnt;

    // Cluster size.
    private int cluserSize;

    private CenterPoint<T>[] centers;

    // Compare distance between two points.
    private IPointCompare<T> compare;

    public KmeansImp(final Point<T>[] points,
                     final int cluserSize,
                     final IPointCompare<T> compare,
                     final int iterCnt) {
        assert (points.length > cluserSize);
        this.points = points;
        this.iterCnt = iterCnt;
        this.cluserSize = cluserSize;
        this.compare = compare;
        this.centers = new CenterPoint[this.cluserSize];
    }

    public Point<T>[] getPointsWithClusterId() {

        // 1. Init center with random.
        initCenter();

        // 2. Training.
        int maxIterCnt = this.iterCnt;
        while (maxIterCnt-- > -1) {
            // 2.1 Find the nearest center point.
            for (Point<T> point : points) {
                if (isCenterPoint(point)) {
                    continue;
                }
                double minDistance = Double.MAX_VALUE;
                int clusterId = point.getClusterId();
                for (CenterPoint center : centers) {
                    double distance = this.compare.getDistance(point, center.getPoint());
                    if (minDistance > distance) {
                        minDistance = distance;
                        if (clusterId != center.getPoint().getClusterId()) {
                            centers[clusterId].remove(point);
                            point.setClusterId(center.getPoint().getClusterId());
                            center.add(point);
                        }
                    }
                }
            }

            // 2.2 Adjust center points.
            boolean isCenterChanged = false;
            for (CenterPoint center : centers) {
                double minDistance = Double.MAX_VALUE;
                Point<T> newCenter = null;
                for (Object obj_1 : center.getCluster()) {
                    Point<T> a = (Point<T>) obj_1;
                    double distance = 0.0;
                    for (Object obj_2 : center.getCluster()) {
                        Point<T> b = (Point<T>) obj_2;
                        distance += this.compare.getDistance(a, b);
                    }
                    if (minDistance > distance) {
                        minDistance = distance;
                        newCenter = a;
                    }
                }
                if (!center.getPoint().equals(newCenter)) {
                    center.updatePoint(newCenter);
                    isCenterChanged = true;
                }
            }

            // 2.3 Check should continue.
            if (!isCenterChanged) {
                break;
            }
        }

        return points;
    }

    private void initCenter() {
        for (int i = 0; i < cluserSize; i++) {
            centers[i] = new CenterPoint<T>(points[i]);
            points[i].setClusterId(i);
        }
        for (int i = 0; i < this.points.length; i++) {
            points[i].setClusterId(i % this.cluserSize);
            centers[i % this.cluserSize].add(this.points[i]);
        }
    }

    private boolean isCenterPoint(final Point<T> point) {
        return Arrays.stream(centers).anyMatch(center -> center.getPoint().equals(point));
    }
}
