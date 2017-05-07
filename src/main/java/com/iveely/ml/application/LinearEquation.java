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

package com.iveely.ml.application;


import com.iveely.ml.common.FeatureData;
import com.iveely.ml.common.IActiveFunction;
import com.iveely.ml.model.neuralnetwork.perception.PerceptionImp;

import java.util.LinkedList;
import java.util.List;

/**
 * Author:liufanping@iveely.com
 **/
public class LinearEquation {

    public class Y_Equal_X implements IActiveFunction {

        @Override
        public double calculate(double value) {
            return value;
        }
    }

    // Each variable's default value.
    private Double[] defaultValues;

    // All equations.
    private List<FeatureData> equations;

    // perceptionImp model.
    private PerceptionImp perceptionImp;

    public LinearEquation(final int variableNumber) {
        assert (variableNumber > 0);
        this.defaultValues = new Double[variableNumber];
        for (int i = 0; i < variableNumber; i++) {
            this.defaultValues[i] = new Double(0.1);
        }
        this.equations = new LinkedList<>();
        this.perceptionImp = new PerceptionImp(0.0001, defaultValues,
                new Y_Equal_X(),
                100000 * (int) Math.pow(3.0, variableNumber * 1.0),
                false);
    }

    public void addEquation(final Double[] euqationLeft, Double euqationRight) {

        // 0. Process.
        Double max = euqationRight;
        for (Double val : euqationLeft) {
            max = max < val ? val : max;
        }
        for (int i = 0; i < euqationLeft.length; i++) {
            euqationLeft[i] /= max;
        }
        euqationRight /= max;
        if (euqationRight == 0.0) {
            euqationRight = 0.0001;
        }
        if (euqationRight < 1) {
            euqationRight *= -1;
            for (int i = 0; i < euqationLeft.length; i++) {
                euqationLeft[i] *= -1;
            }
        }

        // 1. Make sure these belong to the effective equations.
        assert (euqationLeft != null);
        assert (euqationLeft.length == defaultValues.length);

        // 2. Parse equations to extract variables.
        this.equations.add(new FeatureData<>(euqationRight, euqationLeft));
    }

    public void practice() {
        this.perceptionImp.Train(this.equations);
    }

    public Double[] getVariables() {
        assert (equations.size() == defaultValues.length);
        return this.perceptionImp.getWeights();
    }

    public Double predict(final Double[] euqationLeft) {
        return this.perceptionImp.predict(euqationLeft);
    }
}
