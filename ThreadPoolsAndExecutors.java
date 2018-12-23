import java.util.concurrent.*;

class ThreadPoolsAndExecutors {
	
	public static void main(String[] args) {
		
		Callable<String> task = () -> {
			Thread.sleep(1000); 
			throw new InterruptedException("I want to sleep more");
			//return "My callable";
		};
		ExecutorService executor = Executors.newFixedThreadPool(1);
		execute(executor, task);
		execute(executor, task);
		try {
			unhandledExecute(executor, task);
		}
		catch(InterruptedException | ExecutionException e) {
			print(e.getClass().getName());
			print(e.getMessage());
		}
		executor.shutdownNow();

	}
	
	public static void execute(ExecutorService executor,
		Callable<String> task) {

		try {
			print(executor.submit(task).get());
		} catch (InterruptedException e) {
			print("I was interrupted");
		} catch (ExecutionException e) {
			print("Oops");
		}
	}
	
	public static void unhandledExecute(ExecutorService executor,
		Callable<String> task) throws InterruptedException,
										ExecutionException {
											
		print(executor.submit(task).get());

	
	}
	
	private static void print(String s) {
		System.out.println(s);
	}
}
