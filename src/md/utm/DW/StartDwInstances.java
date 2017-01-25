package md.utm.DW;



public class StartDwInstances {
	public static void main(String[] args) throws InterruptedException {
		
		new DataWhereHouseService(7002).start();
	Thread.sleep(1000);
		new DataWhereHouseService(7003).start();
		
	
	}
}
