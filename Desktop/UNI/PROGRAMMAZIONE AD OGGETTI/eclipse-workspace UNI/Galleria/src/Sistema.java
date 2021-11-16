import java.util.Scanner;

public class Sistema {
	static Scanner input = new Scanner(System.in);
	static Collezione listsCollezioni[]={new Collezione()};
	public static void main(String argv[]) {
		System.out.println("Benvenuto!");
		while (true) {	
			System.out.print("Cosa desideri fare?\n" + "\t[1] •Inserire un'opera d'arte\n"
					+ "\t[2] •accedere ad un'opera d'arte\n" + "\t[3] •stampare la collezione\n" +
					"\t[4] •uscire\n"
					+ "scelta = ");
			switch (input.nextInt()) {
			case 1:
				input.nextLine();
				// Inserire un'opera d'arte

				break;
			case 2:
				input.nextLine();
				// accedere ad un'opera d'arte

				break;
			case 3:
				input.nextLine();
				// stampare la collezione

				break;
			case 4:
				input.nextLine();
				// uscire

				break;
			default:
				input.nextLine();

				break;
			}
			System.out.println();
			System.out.println();
			System.out.println();
		}
	}
}