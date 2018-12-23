class Exceptions {
	
	public static void exceptionInCatch() {
		    /*
		 *  Doing something exceptional
		 *	Handled
		 *	Closing down
		 *	NullPointerException
		*/
		
		try {
			System.out.println("Doing something exceptional");
			throw new IllegalArgumentException();		
		} catch (NullPointerException | IllegalArgumentException e) {
			System.out.println("Handled");
			throw new NullPointerException();
		} finally {
			System.out.println("Closing down");
		}
	}
	
	public static void exceptionInCatchAndFinally() {
			/*
		 *  Doing something exceptional
		 *	Handled
		 *	Closing down
		 *	IllegalArgumentException
		*/
		
		try {
			System.out.println("Doing something exceptional");
			throw new IllegalArgumentException();		
		} catch (NullPointerException | IllegalArgumentException e) {
			System.out.println("Handled");
			throw new NullPointerException();
		} finally {
			System.out.println("Closing down");
			throw new IllegalArgumentException();
		}
	}
	
	public static void main(String[] args) {
		
		// exceptionInCatch();
		exceptionInCatchAndFinally();

	}
}
