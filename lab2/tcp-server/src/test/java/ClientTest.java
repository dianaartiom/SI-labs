import client.Client;
import org.databene.contiperf.PerfTest;
import org.databene.contiperf.Required;
import org.databene.contiperf.junit.ContiPerfRule;
import org.databene.contiperf.junit.ContiPerfSuiteRunner;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import server.Server;

import java.io.IOException;

public class ClientTest {

    Client client = new Client();
    Server server = new Server();

    @Rule
    public ContiPerfRule i = new ContiPerfRule();
    @Test
    @PerfTest(invocations = 10000, threads = 20)
    @Required(max = 1200, average = 250)
    public void clientTestRun() throws IOException, InterruptedException {

        client.run();
    }

    @Test
    @PerfTest(invocations = 1, threads = 1)
    @Required(max = 1200, average = 250)
    public void serverTestRun() throws IOException, InterruptedException {

        server.run();
    }


}
