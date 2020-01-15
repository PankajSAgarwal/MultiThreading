package com.pankaj.multithreading.concurrentmap;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

class FirstWorker implements Runnable {

	private ConcurrentMap<String, Integer> map;

	public FirstWorker(ConcurrentMap<String, Integer> map) {
		this.map = map;
	}

	@Override
	public void run() {
		try {
			map.put("B", 1);
			map.put("C", 2);
			map.put("F", 3);

			Thread.sleep(1000);

			map.put("A", 4);
			Thread.sleep(1000);
			map.put("E", 5);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}

class SecondWorker implements Runnable {

	private ConcurrentMap<String, Integer> map;

	public SecondWorker(ConcurrentMap<String, Integer> map) {
		this.map = map;
	}

	@Override
	public void run() {
		try {
			Thread.sleep(5000);
			System.out.println(map.get("A"));
			Thread.sleep(1000);
			System.out.println(map.get("E"));
			System.out.println(map.get("C"));

			//Thread.sleep(1000);

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}

public class App {

	public static void main(String[] args) {

		ConcurrentMap<String, Integer> map = new ConcurrentHashMap<>();
		
		new Thread(new FirstWorker(map)).start();
		new Thread(new SecondWorker(map)).start();
	}

}
