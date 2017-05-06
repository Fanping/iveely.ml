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

/**
 * Comparison of double data points.
 *
 * Author:liufanping@iveely.com
 **/
public class DoublePointCompare implements IPointCompare<Double> {
    @Override
    public double getDistance(Point<Double> a, Point<Double> b) {
        double sum = 0.0;
        for (int i = 0; i < a.getEles().length; i++) {
            sum += Math.pow(a.getEles()[i] - b.getEles()[i], 2);
        }
        return Math.sqrt(sum);
    }
}
