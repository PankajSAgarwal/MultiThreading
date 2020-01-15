package com.pankaj.parallel.thread.join;


	class Runner1 extends Thread{
	
		@Override
		public void run() {
			
			for (int i = 0;i<=10;i++) {
				
				System.out.println("Runner 1: " + i);
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					
					e.printStackTrace();
				}
			}
		}	
	}

	class Runner2 extends Thread{
		@Override
		public void run() {
			for (int i = 0;i<=100;i++) {
				
				System.out.println("Runner 2: " + i);
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

public class App {
	
	

	public static void main(String[] args) {
		
		Runner1 t1 = new Runner1();
		Runner2 t2 = new Runner2();
		
		t1.start();
		t2.start();
		
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Finished the tasks");

	}

}
