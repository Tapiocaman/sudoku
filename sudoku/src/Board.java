import java.util.ArrayList;

public class Board {
	private static String[][] box;
	private static Coordinate[] checks;
	private static int lastX;
	private static int lastY;
	private static int n;
	private static int p;
	private static int q;
	private String[] all;
	
	public Board(int a, int b, int c, String[] set){
		int x=0;
		n=a;
		p=b;
		q=c;
		box=new String[n][n];
		checks=new Coordinate[(n*n)];
		all=set;
		
		for(int i=0; i<n; i++){
			for(int j=0; j<n; j++){
				//System.out.println(i+ " " + j);
				box[i][j]="0";
				checks[x]=new Coordinate(j,i);
				++x;
			}
		}
	}
	
	public void remove(int x, int y, String value){
		box[x][y]=value;
		lastX=x;
		lastY=y;
		
		for(int i=0; i<checks.length; i++){
			int tempX=x/3;
			int tempY=y/3;
			if(checks[i].getX()==x){
				checks[i].removeFromCant(value);
			}
			else if(checks[i].getY()==y){
				checks[i].removeFromCant(value);
			}
			for(int j=0; j<p; j++){
				for(int k=0; k<q; k++){
					if((tempX*p)+j==checks[i].getX() &&(tempY*q)+k==checks[i].getY()){
						checks[i].addToCant(value);
					}					
				}
			}
		}
	}
	
	public void insert(int x,int y, String value ){
		box[x][y]=value;
		lastX=x;
		lastY=y;
		int boxX=x/p;
		int boxY=y/q;
		if(!value.contains("0")){
			//System.out.println("Value =" + value);
			for(int i=0; i<checks.length; i++){

				if(checks[i].getX()==x){
					//System.out.println(checks[i].getX()+"==============="+x);
					checks[i].addToCant(value);
				}
				if(checks[i].getY()==y){
					
					checks[i].addToCant(value);
				}
				for(int j=0; j<p; j++){
					for(int k=0; k<q; k++){
						if((boxX*p)+j==checks[i].getX() &&(boxY*q)+k==checks[i].getY()){
							
							checks[i].addToCant(value);
						}					
					}
				}

			}
		}
		
		if(x==8  &&y==8 && value.equals("2")){
			System.out.println("LINTLINTLINTLINTLINTLINTLINTLINTLINTLINTLINTLINTLINTLINTLINTLINTLINTLINT");
		}
	}
	
	public String get(int x, int y){
		return box[x][y];
	}
	
	public String[] getSet(){
		return all;
	}
	
	public ArrayList<String> getCants(int x, int y){
		for(int i=0; i<checks.length; i++){
			if (checks[i].getX()==x && checks[i].getY()==y){
				return checks[i].getCant();
			}
		}
		return new ArrayList<String>();
	}
	
	public boolean isComplete(){
		for(int i=0; i<q; i++){
			for(int j=0; j<p; j++){
				if(box[i][j]=="0"){
					return false;
				}
			}
		}
		return true;
	}
	
	public int getLastX(){
		return lastX;
	}
	public int getLastY(){
		return lastY;
	}
}
