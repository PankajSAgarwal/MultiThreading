package com.pankaj.multithreading.cyclicbarrier;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class App {

	public static void main(String[] args) {
		ExecutorService executorSerice = Executors.newFixedThreadPool(5);
		CyclicBarrier barrier = new CyclicBarrier(5, new Runnable() {
			
			@Override
			public void run() {
				
				System.out.println("All the tasks are finished ...");
				
			}
		});
		
		for (int i = 0;i<5;i++) {
			executorSerice.execute(new Worker(i+1, barrier));
		}
		
		executorSerice.shutdown();
		

	}

}

class Worker implements Runnable{
	
	private int id;
	private Random random;
	private CyclicBarrier cyclicBarrier;
	
	public Worker(int id, CyclicBarrier cyclicBarrier) {
		
		this.id = id;
		this.random = new Random();
		this.cyclicBarrier = cyclicBarrier;
	}
	

	@Override
	public void run() {
		
		doWork();
		
	}


	private void doWork() {
		
		System.out.println("Thread with ID " + id + " starts the task..." );
		try {
			Thread.sleep(random.nextInt(3000));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Thread with ID " + id +" finished ..." );
		try {
			cyclicBarrier.await();
			System.out.println("After await.. ID " + id);
		} catch (InterruptedException | BrokenBarrierException e) {
			
			e.printStackTrace();
		}
	}


	@Override
	public String toString() {
		return "Worker [id=" + id + "]";
	}
	
	
	
}
