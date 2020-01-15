package com.pankaj.multithreading.producerandconsumer;

import java.util.ArrayList;
import java.util.List;

class Processor{
	
	
	private List<Integer> list = new ArrayList<>();
	
	private final int LIMIT = 5;
	private final int BOTTOM = 0;
	private final Object lock = new Object();
	private int value = 0;
	
	public void producer() throws InterruptedException{
	
		synchronized (lock) {
			
			while(true) {
				if(list.size() == LIMIT) {
					System.out.println("Waiting for removing items from the list ....");
					lock.wait();
				}else {
					System.out.println("Adding " + value);
					list.add(value);
					value++;
					lock.notify();
				}
				
				Thread.sleep(1000);
			}
		}
	}
	
	public void consumer() throws InterruptedException{
		
		synchronized (lock) {
			
			while(true) {
				if(list.size() == BOTTOM) {
					System.out.println("Waiting for items to be added to the list ...");
					lock.wait();
				}else {
					System.out.println("Removed : " + list.remove(--value));
					lock.notify();
				}
				
				Thread.sleep(1000);
			}
			
		}
	}
	
	
}

	public class App {
	
		public static void main(String[] args) {
			
			Processor processor = new Processor();
			
			Thread t1 = new Thread(new Runnable() {
				
				@Override
				public void run() {
					
					try {
						processor.producer();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
			});
	
			Thread t2 = new Thread(new Runnable() {
				
				@Override
				public void run() {
					try {
						processor.consumer();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
			
			t1.start();
			t2.start();
		}
	
	}
