import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

public class CreateThreads {
	
	static class MyThread extends Thread {
		
		@Override
		public void run() {
			System.out.println(getUncaughtExceptionHandler());
			System.out.println("MyThread running");
		}
	
	}
	
	static class MyRunnable implements Runnable {
		
		@Override
		public void run() {
			System.out.println("MyRunnable running");
		}
	
	}
	
	public static void main(String[] args) {
		
		// Method1: Extend Thread class
		Thread thread1 = new MyThread();
		thread1.start();
		
		// Method2.1: implement Runnable interface
		Runnable thread2 = new MyRunnable();
		new Thread(thread2).start();
		
		// Method2.2: anonymuous implementing Runnable
		Runnable thread3 = new Runnable() {
			@Override
			public void run() {
				System.out.println("My anonymuous Runnable running");
			}
		};
		
		new Thread(thread3).start();
		
		// Method2.3: lambda
		new Thread(
			 () -> System.out.println("My lambda Runnable running"))
			.start();
		
		System.out.println("Hello, threads!");
		
		// Method3: Executors
		ExecutorService executorService =
				Executors.newFixedThreadPool(1);
		
		Future future = executorService.submit(
			() -> print("My executorService running")
		);
		
		Future<String> myFuture = 
			executorService.submit(() -> "my callable");
			

		try {
			executorService.shutdown();
		} catch (Throwable e) {
			print("Could not shutdown");
		}

		
		
		try {
			System.out.println("Hello, " + future.get());
			System.out.println("Hello, " + myFuture.get());
		} catch (Throwable e) {			
			print(e.getClass().getName());
			print(e.getMessage());
		} finally {
			executorService.shutdownNow();
			System.out.println("Shut down");
		}
		
	}
	private static void print(String s) {
		System.out.println(s);
	}
	
}
