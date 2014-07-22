import java.util.Scanner;

public class main {
	private static int n;
	private static int p;
	private static int q;
	private static String[] base={"1","2","3","4","5","6","7","8","9","A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};

	public static void main(String [] args){
		int n,p,q;
		String[] masterSet;
		Scanner read= new Scanner(System.in);
		System.out.println("Enter the number of blocks: ");
		n=read.nextInt();
		System.out.println("Enter the number of rows in a block:");
		p=read.nextInt();
		System.out.println("Enter the number of columns in a block:");
		q=read.nextInt();
		System.out.println(n+" "+ p+" "+q);
		
		if ((p*q)!=n){
			System.out.println("Error: Puzzle unsolvable due to input constraints.");
			System.exit(0);
		}
		
		System.out.println("Turn off Forward Checking? (Y/N)");
		boolean forward=(read.next().equals("N"));

		System.out.println("Set Timeout?: (Y/N)");
		boolean time=(read.next().equals("Y"));
		long timer=0;
		if(time){
			System.out.println("TimeOut Time: (in ms)");
			timer=read.nextLong();
		}
		
		//define master set
		masterSet=new String[n];
		for(int i=0; i<n;i++){
			masterSet[i]=odometer(i);
		}

		

		//build the sudoku, any blank square should be replaced with a 0
		//it should be inputed line by line(rows, not columns)
		int x=0;
		Board find=new Board(n, p, q, masterSet);
		for(int j=0;j<n;j++){	
			System.out.println("Input next line:");
			for(int i=0; i<n;i++){
				find.insert(i, j, read.next());
				++x;
			}		
		}

		Solver solve=new Solver(find, forward, n, p, q, timer);
		//solve.printBoard();
	}


	//makes an element
	private static String odometer(int x){
		if(x<base.length){
			return base[x];
		}
		else{
			int first=1;
			while(x>(first*base.length)){
				first*=base.length;
			}
			System.out.println(base.length);
			System.out.println(first);
			int temp=(x/first)-1;
			System.out.println(temp);
			int give=x%first;
			System.out.println(give);
			return base[temp]+odometer(give);
		}
	}

}