## Exercise week4

### 4.1

- Question 1

  In this part, we check 2 safe properties

  - If you try to insert twice the same element, the second insertion will return false, and the length of the set only add one
  - If multile threads try to add the same element, there should be only one element successfully added

  ConcurrentIntegerSetBuggy successfully passed the first test, but failed on the second one.
![截屏2022-09-30 上午2 45 08的副本](https://user-images.githubusercontent.com/95942789/193168982-575ccee8-90d8-44db-90fe-abb6d74f3200.png)

  The set may add the same element twice.

- Question 2

  The test is similiar to the second test from question 1. ConcurrentIntegerSetBuggy also failed on this test.
  ![截屏2022-09-30 上午2 47 50](https://user-images.githubusercontent.com/95942789/193169129-99a32bcb-f926-4d1e-9054-ede29282a885.png)


 	the size of the set may be negative.

- Question 3

  To fix the errors above in ConcurrentIntegerSetSync, we use lock. Since there's data race/ race condition here, and the change of the set should be covered by a critical session. Maybe we can also use keyword synchronized here.

  Our solution make sure that one time, only one thread can add/remove elements from the set. 

- Question 4

  there's no errors on oncurrentIntegerSetLibrary.

- Question 5

  Yes, because it violate the safety property of the concurrency. 

- Quetsion 6

  No, because there maybe other potential bugs.

  
