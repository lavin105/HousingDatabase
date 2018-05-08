public class Engine  {
    private theSystem s=new theSystem();
    //runs application startup
    public void runStart() throws InterruptedException{
        s.start();
    }
    //runs the main functions of the application
    public void runBody() throws InterruptedException{
        s.run();
    }

}
