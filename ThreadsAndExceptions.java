class ThreadsAndExceptions {
	
	static class MyExceptionalThread extends Thread {
		
		@Override
		public void run() {
			print("I am executing");
			print(getThreadGroup().getName());
			print(getThreadGroup().getParent().getName());
			throw new RuntimeException("Exception ocurred");
		}
	}
	
	static class MyThreadGroup extends ThreadGroup {
		
		MyThreadGroup(String name) {
			super(name);
		} 
		
		@Override
		public void uncaughtException(Thread t, Throwable e) {
		}
	
	}
	
	public static void main(String[] args) {
	
		Thread thread = new MyExceptionalThread();
		thread.start();
		ThreadGroup myGroup = new MyThreadGroup("My group");
		
		try {
			Thread noGroup = new Thread(myGroup,
										() -> execute(), 
										"new group");
			print(noGroup.getThreadGroup().getName());	
			print(noGroup.getThreadGroup().getParent().getName());
					
			noGroup.start();
		} catch (Exception e) {
			print("Exception is handled");
		} 
				
	}
	
	private static void execute() {
		print("I am executing");
		throw new RuntimeException("Exception ocurred my group");
		
	}
	
	static void print(String s) {
		System.out.println(s);
	}
}
