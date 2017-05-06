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

package test.com.iveely.ml.application;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Author:liufanping@iveely.com
 **/
public class Test_LinearEquation {

    @Test
    public void testEquation2() {
        com.iveely.ml.application.LinearEquation linearEquation =
                new com.iveely.ml.application.LinearEquation(2);
        linearEquation.addEquation(new Double[]{10.0, 2.0}, 12.0);
        linearEquation.addEquation(new Double[]{1.0, 1.0}, 2.0);
        linearEquation.practice();
        Double[] ret = linearEquation.getVariables();
        // Error ratio less than 0.1
        assertEquals(1.0, ret[0], 0.1);
        assertEquals(1.0, ret[1], 0.1);
    }

    @Test
    public void testEquation3() {
        com.iveely.ml.application.LinearEquation linearEquation =
                new com.iveely.ml.application.LinearEquation(3);
        linearEquation.addEquation(new Double[]{10.0, 2.0, 4.0}, 16.0);
        linearEquation.addEquation(new Double[]{1.0, 1.0, 3.0}, 5.0);
        linearEquation.addEquation(new Double[]{1.0, 1.0, 2.0}, 4.0);
        linearEquation.practice();
        Double[] ret = linearEquation.getVariables();
        // Error ratio less than 0.1
        assertEquals(1.0, ret[0], 0.1);
        assertEquals(1.0, ret[1], 0.1);
        assertEquals(1.0, ret[2], 0.1);

    }
}
