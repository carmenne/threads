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
				Executors.newSingleThreadExecutor();
		
		Future future = executorService.submit(
			() -> System.out.println("My executorService running")
		);
		
		((Executor) executorService).execute(
			() -> System.out.println("My executor running")
		);
		
		// Callable & Future (if return type instead of void is needed)
		Callable<String> task = () -> "my callable";
		
		Future<String> myFuture = executorService.submit(task);
		
		try {
			executorService.shutdownNow();
			System.out.println("Hello, " + myFuture.get());
		} catch (InterruptedException e) {
			System.out.println("I was interrupted");
			throw new IllegalArgumentException();
		} catch (ExecutionException e) {
			System.out.println("ExecutionException");
		} catch (Exception e) {
			System.out.println("Exception");
		}
		
		finally {
			System.out.println("Shut down");
		}
		
	}
	
}
