interface Ship {
	void go();
}

class NavalShip implements Ship {
	@Override
	public void go() {
		System.out.println("Sea");
	}
}

class Submarine extends NavalShip {
	@Override
	public void go() {
		System.out.println("Under sea");
	}
}

class PoliTest {
	public static void main(String[] args) {
		NavalShip nv = new NavalShip();
		((Submarine)nv).go();
	}
}
