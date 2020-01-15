package com.pankaj.multithreading.semaphores;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/*
 *  - semaphores maintains a set of permits
 *  - acquire() - if a permit is available then take it
 *  - release() - adds a permit
 *  
 *  	Sempaphore just keeps a count of the number available
 *  	new Semaphore(int permits, boolean fair)
 */


	
enum Downloader {
	
	INSTANCE;
	private Semaphore semapahore = new Semaphore(5, true);
	
	public void downloadData() {
		
		try {
			semapahore.acquire();
			downlaod();
		}catch(InterruptedException e) {
			e.printStackTrace();
		}finally {
			
			semapahore.release();
		}
	}

	private void downlaod() {
		System.out.println("Downloading data from web");
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
	}
}


public class App {

	public static void main(String[] args) {
		
		ExecutorService executorService = Executors.newCachedThreadPool();
		
		for(int i=0;i<12;i++) {
			//System.out.println("i : " + i);
			executorService.execute(new Runnable() {
				
				@Override
				public void run() {
					Downloader.INSTANCE.downloadData();
					
				}
			});;
		}
		executorService.shutdown();
	}

}
