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

package com.iveely.ml.model.neuralnetwork.bp;

import com.iveely.ml.common.FeatureData;

import org.apache.commons.lang3.ArrayUtils;

import java.util.List;
import java.util.Random;

/**
 * BP neural network with multiple hidden layers.
 *
 * Author:liufanping@iveely.com
 **/
public class BackPropagation {

    // Neural network nodes.
    private double[][] layer;

    // Neural network node error.
    private double[][] layerErr;

    // Node weight for each layer.
    public double[][][] layer_weight;

    // Node weight momentum.
    public double[][][] layer_weight_delta;

    // Momentum coefficient.
    public double mobp;

    // Learning coefficient.
    public double rate;

    public BackPropagation(final int[] layerConfig,
                           final Double rate,
                           final Double mobp) {
        this.mobp = mobp;
        this.rate = rate;
        this.layer = new double[layerConfig.length][];
        this.layerErr = new double[layerConfig.length][];
        this.layer_weight = new double[layerConfig.length][][];
        this.layer_weight_delta = new double[layerConfig.length][][];
        final Random random = new Random();
        for (int m = 0; m < layerConfig.length; m++) {
            layer[m] = new double[layerConfig[m]];
            layerErr[m] = new double[layerConfig[m]];
            if (m + 1 < layerConfig.length) {
                layer_weight[m] = new double[layerConfig[m] + 1][layerConfig[m + 1]];
                layer_weight_delta[m] = new double[layerConfig[m] + 1][layerConfig[m + 1]];
                for (int j = 0; j < layerConfig[m] + 1; j++)
                    for (int i = 0; i < layerConfig[m + 1]; i++)
                        layer_weight[m][j][i] = random.nextDouble();
            }
        }
    }

    /**
     * Layer by layer forward output.
     */
    private double[] forward(double[] features) {
        for (int m = 1; m < layer.length; m++) {
            for (int j = 0; j < layer[m].length; j++) {
                double z = layer_weight[m - 1][layer[m - 1].length][j];
                for (int i = 0; i < layer[m - 1].length; i++) {
                    layer[m - 1][i] = m == 1 ? features[i] : layer[m - 1][i];
                    z += layer_weight[m - 1][i][j] * layer[m - 1][i];
                }
                layer[m][j] = 1 / (1 + Math.exp(-z));
            }
        }
        return layer[layer.length - 1];
    }

    /**
     * Layer by layer reverse calculation error and modify weights.
     */
    private void backward(double[] expects) {
        int l = layer.length - 1;
        for (int j = 0; j < layerErr[l].length; j++)
            layerErr[l][j] = layer[l][j] * (1 - layer[l][j]) * (expects[j] - layer[l][j]);

        while (l-- > 0) {
            for (int j = 0; j < layerErr[l].length; j++) {
                double z = 0.0;
                for (int i = 0; i < layerErr[l + 1].length; i++) {
                    z = z + l > 0 ? layerErr[l + 1][i] * layer_weight[l][j][i] : 0;
                    layer_weight_delta[l][j][i] = mobp * layer_weight_delta[l][j][i] + rate * layerErr[l + 1][i] * layer[l][j];

                    // Hidden layer weight adjustment.
                    layer_weight[l][j][i] += layer_weight_delta[l][j][i];
                    if (j == layerErr[l].length - 1) {
                        layer_weight_delta[l][j + 1][i] = mobp * layer_weight_delta[l][j + 1][i] + rate * layerErr[l + 1][i];

                        // Intercept weight adjustment.
                        layer_weight[l][j + 1][i] += layer_weight_delta[l][j + 1][i];
                    }
                }

                // Recording error.
                layerErr[l][j] = z * layer[l][j] * (1 - layer[l][j]);
            }
        }
    }

    public void train(final List<FeatureData<Double, Double[]>> featureData,
                      final Integer iterCnt) {
        for (int i = 0; i < iterCnt; i++) {
            for (FeatureData<Double, Double[]> data : featureData) {
                forward(ArrayUtils.toPrimitive(data.getFeatures()));
                backward(ArrayUtils.toPrimitive(data.getExpect()));
            }
        }
    }

    public double[] predict(FeatureData<Double, Double[]> testData) {
        return forward(ArrayUtils.toPrimitive(testData.getFeatures()));
    }
}
