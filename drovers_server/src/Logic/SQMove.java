package Logic;

public class SQMove{
	
	
	public static void moveUp(int clientId){
		System.out.println("Move up:" + clientId);
	}
	
	public static void moveDown(int clientId){
		System.out.println("Move down:" + clientId);
	}
	
	public static void moveRight(int clientId){
		System.out.println("Move right:" + clientId);
	}
	
	public static void moveLeft(int clientId){
		System.out.println("Move left:" + clientId);
	}

	public static void moveUpLeft(int clientId) {
		System.out.println("Move up-left:" + clientId);
	}

	public static void moveUpRight(int clientId) {
		System.out.println("Move up-right:" + clientId);
	}

	public static void moveDownLeft(int clientId) {
		System.out.println("Move down-left:" + clientId);
	}

	public static void moveDownRight(int clientId) {
		System.out.println("Move down-right:" + clientId);
	}
}