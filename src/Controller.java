package AnnexGUI;
public class Controller {

	private static Network net;
	private static View UI;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		net = new Network();
                Thread t = new Thread(net);
                t.start();

                javax.swing.SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        UI = new View();  // Let the constructor do the job
                    }
                });

                try {
			Thread.sleep(3000);
			while(true)
			{
                                String values = net.getReadOuts();
				UI.setValues(values);
                                //System.out.println(Count);
                                Thread.sleep(1000);
			}
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // POINT OF FOCUS

	}

}
