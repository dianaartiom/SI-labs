import org.databene.contiperf.PerfTest;
import org.databene.contiperf.Required;
import org.databene.contiperf.junit.ContiPerfRule;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.io.IOException;

public class AppTest {

    App app = new App();

    @Rule
    public ContiPerfRule i = new ContiPerfRule();
    @Test
    @PerfTest(invocations = 10000, threads = 20)
    @Required(max = 1200, average = 250)
    public void runMethodTest() throws IOException, InterruptedException {
        String[] args = ("165.227.137.80 /home/diana/Desktop/lab.txt").split(" ");
        app.run(args);
    }

    @Test
    @PerfTest(invocations = 1, threads = 1)
    @Required(max = 1200, average = 250)
    public void serverTestRun() throws IOException, InterruptedException {

    }


}
