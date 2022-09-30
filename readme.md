#### Exercise 3.1 

##### 3.1.2

- [x] Class state: All shared primitive variables are private

	`private final int bufferSize = 10;;`

- [x] Escaping: Obviously, there is no escaping problem in this program since there is no set and get functions in the program.

- [x] Publication:

	- For primitive types； make it unmodifiable to 10

		- ```java
			private final int bufferSize = 10;
			```

	- For a complex object: declare it as `final`

		- ```java
			private final Queue<Object> queue = new LinkedList<>();
			private final Semaphore semConsumer  = new Semaphore(0);
			private final Semaphore semProducer = new Semaphore(bufferSize);
			```

			

- [x] Immutability: achieved by using `final` and semaphores

**Conclusion**: The class is thread-safe

##### 3.1.3

No, the barrier cannot do that, it doesn't block a thread. So it doesn't support the blocking wait when the buffer is full while the producer tries to put an element and when the buffer is empty while the consumer tries to take an element.



#### Exercise 3.2

##### 3.2.2

- [x] Class state: all the variables are `private`  and one for tracking the increment id setting to `static`

- [x] The program returns the field value separately in different functions rather than returning the whole object. So no reference for the object thus no escaping.

- [x] Initialization happens-before publication

- [x] Immutability:

	- ```java
		public Person(){
		        synchronized (Person.class){
		            this.id = idCounter;
		            idCounter++;
		        }
		    }
		```

	- By using `synchronized(Person.class)`, we can guarantee subsequent access to a created object will never refer to a partially created object. 



##### 3.3.4

No, only by theoretical analysis of the program can we decide that the implementation is thread-safe.