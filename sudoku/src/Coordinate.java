import java.util.ArrayList;

public class Coordinate {
	private static int coorX;
	private static int coorY;
	private static ArrayList<String> cant=new ArrayList<String>();
	
	public Coordinate(int x, int y){
		coorX=x;
		coorY=y;
	}
	public int getX(){
		return coorX;
	}
	public int getY(){
		return coorY;
	}
	public void addToCant(String value){
		boolean addit=true; 
		for(int i=0; i<cant.size();i++){
			if(cant.get(i).equals(value)){
				addit=false;
				break;
			}
		}
		if(addit){
			cant.add(value);
		}
	}
	public void removeFromCant(String value){
		for(int i=0; i<cant.size(); i++){
			if(cant.get(i).equals(value)){
				cant.remove(i);
			}
		}
	}
	
	public ArrayList<String> getCant(){
		return cant;
	}
}
