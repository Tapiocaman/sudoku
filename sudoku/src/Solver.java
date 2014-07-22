import java.util.ArrayList;
import java.util.Stack;


public class Solver {
	private static boolean forwardCheck;
	private static Stack<Board> states;
	private static Board current;
	private static int n;
	private static int p;
	private static int q;
	private static long start;
	private static long timeout;
	
	
	public Solver(Board box, boolean forward, int a, int b, int c, long timer){
		//to create a monster sudoku, need to take in n,p,q, and odometer set
		n=a;
		p=b;
		q=c;
		start=System.currentTimeMillis();
		if (timer==0){
		timeout=60000;}
		else timeout=timer;
		
		current=box;
		states=new Stack<Board>();
		states.push(current);
		forwardCheck=forward;
		backTrack(current);
		if(backTrack(current)==false){
			System.out.println("I'm a failure");
		}
		printBoard();
	}
	
	private boolean backTrack(Board x){
		//System.out.println("backtracking...");
		if((start+timeout)<System.currentTimeMillis()) {
			System.out.println("Timed Out in: "+ (System.currentTimeMillis()-start));
			System.exit(0);
		}
		
		if (states.isEmpty() && !x.isComplete()){
			//means explored all options, but the board still can't be completed.
			System.out.println("No possible solutions");
			return false;
		}
		else{
			//make a move
			for(int j=0; j<n; j++){
				for(int i=0; i<n; i++){
					if(x.get(i, j).equals("0")){
						for(int k=0; k<current.getSet().length; k++){
							if(forwardChecking(i, j, k)){
								current.insert(i, j, current.getSet()[k]);
								states.push(current);
								if(isValid(i,j,current) && backTrack(current)){
									return true;
								}
								else{
									states.pop();
									current.remove(i, j, "0");
									
								}
							}
						}
						if(current.get(i, j)=="0"){
							return false;
						}
					}
				}
			}
		}
		
		return true;
	}
	
	private boolean forwardChecking(int x, int y, int check){
		if (forwardCheck==true){
			//System.out.println("forward checking");
			ArrayList<String> cBoard= current.getCants(x, y);
			//System.out.println("Got cants size:"+ cBoard.size());
			boolean flag= true;

			for(int i=0; i<cBoard.size(); i++){
				if (cBoard.get(i).equals(current.getSet()[check])){
					//System.out.println("!!!!!!!!!!!!!!!!!!!!!"+cBoard.get(i)+"   "+current.getSet()[check]);
					flag=false;
				}
			}

			return flag;
				
		}
		
		return true;
	}
	
	public void printBoard(){
		System.out.println("Finished in: " + (System.currentTimeMillis()-start)+ " ms");
		for(int j=0; j<n; j++){
			for(int i=0; i<n; i++){
				System.out.print(current.get(i,j)+" ");
			}
			System.out.println();
		}
	}
	
	private boolean isValid(int x, int y, Board b){
		//assumes you have already inserted the new value, only checking to see if the move you just made was valid
		for(int i=0; i<n; i++){
			if(b.get(x, i).equals(b.get(x, y))&& i!=y){
				return false;
			}
			if(b.get(i, y).equals(b.get(x, y))&& i!=x){
				return false;
			}
			
		}
		int segmentX=x/p;
		int segmentY=y/q;
		for(int j=0; j<p; j++){
			for(int i=0; i<q; i++){
				if(b.get(x, y).equals(b.get(segmentX*p+i,segmentY*q+j))&& (x*10)+y!=((segmentX*p+i)*10) +(segmentY*q+j)){
					return false;
				}
			}
		}
		
		return true;
	}
}
