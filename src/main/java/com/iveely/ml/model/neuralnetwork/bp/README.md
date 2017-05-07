# BackPropagation [![Build Status](https://travis-ci.org/Fanping/iveely.ml.svg?branch=master)](https://travis-ci.org/Fanping/iveely.ml)
How to use bp-neural-network ?

It's easy as follw:

```java
     // 2 is input size, last 1 is output size. 10 and 3 are hidden-layer.
     BackPropagation bp = new BackPropagation(new int[]{2, 10, 3, 1}, 0.02, 0.9);
     List<FeatureData<Double, Double[]>> trainData = new ArrayList<>();
     trainData.add(new FeatureData<>(new Double[]{1.0}, new Double[]{2.0, 4.0}));
     trainData.add(new FeatureData<>(new Double[]{1.0}, new Double[]{2.0, 6.0}));
     trainData.add(new FeatureData<>(new Double[]{0.0}, new Double[]{4.0, 2.0}));
     trainData.add(new FeatureData<>(new Double[]{0.0}, new Double[]{6.0, 2.0}));
     bp.train(trainData, 10000 * 4);
             
     assertEquals(bp.predict(new FeatureData<>(
               new Double[]{0.0}, new Double[]{2.0, 6.0}))[0], 1.0, 0.01);
     assertEquals(bp.predict(new FeatureData<>(
               new Double[]{0.0}, new Double[]{6.0, 2.0}))[0], 0.0, 0.01);
     assertEquals(bp.predict(new FeatureData<>(
               new Double[]{0.0}, new Double[]{8.0, 2.0}))[0], 0.0, 0.01);
```
