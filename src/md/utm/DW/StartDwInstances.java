package md.utm.DW;



public class StartDwInstances {
	public static void main(String[] args) throws InterruptedException {
		new DataWhereHouseService(6003).start();
		Thread.sleep(1000);
	//	new DataWhereHouseService(6004).start();
		Thread.sleep(100);
	
	}
}
