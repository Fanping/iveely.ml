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
 * Multidimensional data points.
 *
 * Author:liufanping@iveely.com
 **/
public class Point<T> {

    private T[] eles;

    private int centorId;

    public Point(final T... eles) {
        this.eles = eles;
        this.centorId = -1;
    }

    public T[] getEles() {
        return eles;
    }

    public int getCentorId() {
        return centorId;
    }

    public void setCentorId(final int centorId) {
        this.centorId = centorId;
    }
}
