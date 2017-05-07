# Perception [![Build Status](https://travis-ci.org/Fanping/iveely.ml.svg?branch=master)](https://travis-ci.org/Fanping/iveely.ml)
How to use perception ?

It's easy as follw:

```java
     // Simulation for 'AND'
     Double[] w = {0.001, 0.001};
     PerceptionImp p = new PerceptionImp(0.001, w, new AndActiveFunction(), 100000, true);
     List<FeatureData> featureDataList = new LinkedList<>();
     featureDataList.add(new FeatureData<>(new Double[]{1.0, 0.0}, 0.0));
     featureDataList.add(new FeatureData<>(new Double[]{1.0, 1.0}, 1.0));
     featureDataList.add(new FeatureData<>(new Double[]{0.0, 0.0}, 0.0));
     featureDataList.add(new FeatureData<>(new Double[]{0.0, 1.0}, 0.0));
     p.Train(featureDataList);
     assertEquals(1.0, p.predict(new Double[]{1.0, 1.0}), 0.001);
     assertEquals(0.0, p.predict(new Double[]{0.0, 1.0}), 0.001);
     assertEquals(0.0, p.predict(new Double[]{1.0, 0.0}), 0.001);
     assertEquals(0.0, p.predict(new Double[]{0.0, 0.0}), 0.001);
```
