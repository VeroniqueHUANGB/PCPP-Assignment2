### Exercise 4 Error Fix

##### 4.1.1

I use CyclicBarrier to make sure threads finished before assert.

For this question, I test if we try to add same elements using different thread, there will only be one element added to the set, because there's no duplicated elements in the set.

However, there maybe some error, which means, we may add the same elements twice.

##### 4.1.2

Same as 4.1.1, I fix the problem by using CyclicBarrier.

For this question, I test if we try to  delete same elements using different thread, there will only be one element removed from the set. If there's only one element in the set, then the result should be 0 instead of -1.

However, there maybe some error, which means, after we remove same elements from different thread, the length of the set maybe less than 0.