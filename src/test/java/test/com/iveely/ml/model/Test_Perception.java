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

import com.iveely.ml.common.BasicActiveFunction;
import com.iveely.ml.common.FeatureData;
import com.iveely.ml.common.IActiveFunction;
import com.iveely.ml.model.Perception;

import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Test for perception
 * Author:liufanping@iveely.com
 **/
public class Test_Perception {

    public class AndActiveFunction implements IActiveFunction {

        @Override
        public double calculate(double value) {
            return value >= 0 ? 1 : 0;
        }
    }

    @Test
    public void testPerception() {
        // Simulation for 'XOR'
        Double[] w = {0.001, 0.001, 0.001};
        Perception p = new Perception(0.001, w, new AndActiveFunction(), 10000);
        List<FeatureData> featureDataList = new LinkedList<>();
        featureDataList.add(new FeatureData<>(new Double[]{1.0, 0.0}, 0.0));
        featureDataList.add(new FeatureData<>(new Double[]{1.0, 1.0}, 1.0));
        featureDataList.add(new FeatureData<>(new Double[]{0.0, 0.0}, 0.0));
        featureDataList.add(new FeatureData<>(new Double[]{0.0, 1.0}, 0.0));
        p.Train(featureDataList);
        System.out.println(Arrays.toString(p.getWeights()));
        System.out.println(p.predict(new Double[]{0.0, 0.0}));
    }

}
