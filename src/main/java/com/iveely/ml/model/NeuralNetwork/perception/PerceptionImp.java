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

package com.iveely.ml.model.NeuralNetwork.perception;

import com.iveely.ml.common.FeatureData;
import com.iveely.ml.common.IActiveFunction;

import java.util.List;

/**
 * Description: y = f(w1*x1+w2*x2+...+wn*xn+offset)
 * Author:liufanping@iveely.com
 **/
public class PerceptionImp {

    // Study Rate.
    private Double η;

    // Weight for each feature.
    private Double[] weights;

    // Iteration count.
    private int iterationCnt;

    // Active function.
    private IActiveFunction activeFunction;

    // Offset, bias factor.
    private double b;

    private boolean useOffset;

    public PerceptionImp(final Double η,
                         final Double[] defaultWeight,
                         final IActiveFunction activeFunction,
                         final int iterationCnt,
                         final boolean useOffset) {
        this.η = η;
        this.weights = defaultWeight;
        this.activeFunction = activeFunction;
        this.iterationCnt = iterationCnt;
        this.b = 0;
        this.useOffset = useOffset;
    }

    private int isUniformity(final FeatureData<Double, Double> featureData) {
        double y = 0;
        for (int i = 0; i < featureData.getFeaturesLength(); i++) {
            y += featureData.getFeatures()[i] * this.weights[i];
        }
        double val = y - featureData.getExpect();
        if (useOffset) {
            val += b;
        }
        if (Math.abs(val) < this.η * 10) {
            return 0;
        } else if (val < 0) {
            return 1;
        } else {
            return -1;
        }
    }

    private void adjustWeight(final FeatureData<Double, Double> featureData, int direction) {
        for (int i = 0; i < featureData.getFeaturesLength(); i++) {
            this.weights[i] += direction * this.η * featureData.getFeatures()[i] * featureData.getExpect();
        }
        if (useOffset) {
            this.b += this.η * direction;
        }
    }

    public void Train(List<FeatureData> featureDataList) {
        boolean doIteration = true;
        int count = iterationCnt;
        while (doIteration && count-- > 0) {
            for (FeatureData featureData : featureDataList) {
                int isRight = isUniformity(featureData);
                if (isRight != 0) {
                    adjustWeight(featureData, isRight);
                    doIteration = true;
                    break;
                } else {
                    doIteration = false;
                }
            }
        }
    }

    public Double[] getWeights() {
        return this.weights;
    }

    public Double predict(Double[] feature) {
        Double sum = 0.0;
        for (int i = 0; i < feature.length; i++) {
            sum += this.weights[i] * feature[i];
        }
        if (useOffset) {
            sum += b;
        }
        return activeFunction.calculate(sum);
    }
}
