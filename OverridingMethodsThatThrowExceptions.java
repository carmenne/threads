class NotHungry extends Exception {};
class NotVeryHungry extends NotHungry {};
class StillHungry extends Exception {};

interface Animal {
	String eat() throws NotHungry, StillHungry;
}

class Mammal implements Animal {
	@Override
	public String eat() {
		return "Let's grab some mammal food";
	}
}

class Dog extends Mammal {

	// throws StillHungry: overridden method does not throw StillHungry
	// throws NotHungry: overridden method does not throw NotHungry
	@Override
	public String eat() { 
		return "Let's grab some meat";
	}
}

class Insect implements Animal {
	@Override
	public String eat() throws StillHungry {
		return "Let's grab some insect food";
	}
}

class Larva extends Insect {

	// throws NotHungry: overridden method does not throw NotHungry
	@Override
	public String eat() throws StillHungry {
		return "Let's grab some grass";
	}
}

class Reptile implements Animal {
	@Override
	public String eat() throws NotHungry {
		return "Let's grab some insect food";
	}
}

class Crocodile extends Reptile {

	// I can throw a more specialized exception
	@Override
	public String eat() throws NotVeryHungry {
		return "Let's grab some grass";
	}
}

class Human implements Animal {
	@Override
	public String eat() throws NotVeryHungry {
		return "Let's grab some nice food";
	}
}

class Woman extends Human {

	// throws NotHungry: I cannot throw a more generic exception
	@Override
	public String eat() {
		return "Let's grab fancy food";
	}
}

class OverridingExcTest {
	public static void main(String[] args) {
		System.out.println("Let's eat");
	}
}
