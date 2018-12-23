import java.util.concurrent.*;
import java.util.stream.*;
import java.util.List;

class SharedState {

	int count = 0;
	
	int increment() {
		return count++;
	}
	
	Callable<Integer> createTask() {
		return () -> increment();	
	}
	
	public static void main(String[] args) {
		
		SharedState state = new SharedState();
		ExecutorService ext = Executors.newFixedThreadPool(2);
		List<Callable<Integer>> tasks = IntStream.range(0, 10000)
						.mapToObj((i) -> state.createTask())
						.collect(Collectors.toList());
	
		int interruptedThreads = 0;
		try {
			ext.invokeAll(tasks);
		} catch (Exception e) {
			interruptedThreads++;
		}
		ext.shutdown();
		print(String.valueOf(state.count));
		print(String.valueOf(interruptedThreads));
	}
	
	private static void print(String s) {
		System.out.println(s);
	}
	
}
