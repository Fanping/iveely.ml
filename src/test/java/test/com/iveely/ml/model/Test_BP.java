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

import com.iveely.ml.common.FeatureData;
import com.iveely.ml.model.neuralnetwork.bp.BackPropagation;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Test for back propagation neural network.
 *
 * Author:liufanping@iveely.com
 **/
public class Test_BP {

    @Test
    public void testBackPropagation() {

        // 2 is input size, last 1 is output size. 10 and 3 are hidden-layer.
        BackPropagation bp = new BackPropagation(new int[]{2, 10, 3, 1}, 0.02, 0.9);
        List<FeatureData<Double, Double[]>> trainData = new ArrayList<>();
        trainData.add(new FeatureData<>(new Double[]{1.0}, new Double[]{2.0, 4.0}));
        trainData.add(new FeatureData<>(new Double[]{1.0}, new Double[]{2.0, 6.0}));
        trainData.add(new FeatureData<>(new Double[]{0.0}, new Double[]{4.0, 2.0}));
        trainData.add(new FeatureData<>(new Double[]{0.0}, new Double[]{6.0, 2.0}));
        bp.train(trainData, 10000 * 4);

        assertEquals(bp.predict(new FeatureData<>(
                new Double[]{0.0}, new Double[]{2.0, 6.0}))[0], 1.0, 0.1);
        assertEquals(bp.predict(new FeatureData<>(
                new Double[]{0.0}, new Double[]{6.0, 2.0}))[0], 0.0, 0.1);
        assertEquals(bp.predict(new FeatureData<>(
                new Double[]{0.0}, new Double[]{8.0, 2.0}))[0], 0.0, 0.1);
    }
}
