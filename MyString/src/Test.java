
public interface Test {
	public void hello();
	class Hidden{
		private void hello() {
			System.out.println("Interface Hidden class hello()");
		}
	}
}