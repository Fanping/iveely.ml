# Application [![Build Status](https://travis-ci.org/Fanping/iveely.ml.svg?branch=master)](https://travis-ci.org/Fanping/iveely.ml)
How to use applications ?

1. Linear Equation

```java
      // 10.0*x1+2.0*x2 = 12.0
      // 1.0*x1+1.0*x2  = 2.0
      // so, x1 = 1.0, x2 = 1.0
      com.iveely.ml.application.LinearEquation linearEquation =
                     new com.iveely.ml.application.LinearEquation(2);
      linearEquation.addEquation(new Double[]{10.0, 2.0}, 12.0);
      linearEquation.addEquation(new Double[]{1.0, 1.0}, 2.0);
      linearEquation.practice();
      Double[] ret = linearEquation.getVariables();
      // Error ratio less than 0.1
      assertEquals(1.0, ret[0], 0.1);
      assertEquals(1.0, ret[1], 0.1);
```
