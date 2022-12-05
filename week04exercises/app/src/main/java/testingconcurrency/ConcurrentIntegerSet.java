// NOTE: In this file, you should only modify the class ConcurrentIntegerSetSync
package testingconcurrency;

import java.util.Set;
import java.util.HashSet;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public interface ConcurrentIntegerSet {
    public boolean add(Integer element);
    public boolean remove(Integer element);
    public int size();
}

class ConcurrentIntegerSetBuggy implements ConcurrentIntegerSet {
    final private Set<Integer> set;

    public ConcurrentIntegerSetBuggy() {
	this.set = new HashSet<Integer>();
    }

    public boolean add(Integer element) {
	return set.add(element);
    }

    public boolean remove(Integer element) {
	return set.remove(element);
    }

    public int size() {
	return set.size();
    }
}

// TODO: Fix this class to pass your tests
class ConcurrentIntegerSetSync implements ConcurrentIntegerSet {
    final private Set<Integer> set;
    Lock lock = new ReentrantLock();
    Boolean res;

    public ConcurrentIntegerSetSync() {
	this.set = new HashSet<Integer>();
    }

    public boolean add(Integer element, CyclicBarrier done, AtomicInteger count) {
        lock.lock();
        try{
            res = set.add(element);
        }
        finally {lock.unlock();}
        return res;
    }

    public boolean remove(Integer element) {
        lock.lock();
        try{
            res = set.remove(element);
        }
        finally {lock.unlock();}
        return res;
    }

    public int size() {
	return set.size();
    }
}

class ConcurrentIntegerSetLibrary implements ConcurrentIntegerSet {
    final private Set<Integer> set;

    public ConcurrentIntegerSetLibrary() {
	this.set = new ConcurrentSkipListSet<Integer>();
    }

    public boolean add(Integer element) {
	return set.add(element);
    }

    public boolean remove(Integer element) {
	return set.remove(element);
    }

    public int size() {
	return set.size();
    }
}

    
