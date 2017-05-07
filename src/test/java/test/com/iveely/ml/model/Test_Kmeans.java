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

package test.com.iveely.ml.model;

import com.iveely.ml.model.kmeans.IntPointCompare;
import com.iveely.ml.model.kmeans.KmeansImp;
import com.iveely.ml.model.kmeans.Point;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

/**
 * Test for kmeans.
 *
 * Author:liufanping@iveely.com
 **/
public class Test_Kmeans {

    @Test
    public void testKmeans() {
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
    }
}
