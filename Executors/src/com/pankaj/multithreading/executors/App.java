package com.pankaj.multithreading.executors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class App {

	public static void main(String[] args) {
		
		ExecutorService executorService = Executors.newCachedThreadPool();
		
		for(int i = 0;i < 5; i++) {
			executorService.execute(new Worker());
		}
		
		executorService.shutdown();

	}

}

class Worker implements Runnable{

	@Override
	public void run() {
		for(int i = 0;i < 10;i++) {
			System.out.println(i);
			try {
				Thread.sleep(100);
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
	
}
