import org.dacapo.harness.CommandLineArgs;

import java.time.LocalDateTime;
import java.util.Date;

public class AgentlessCallback extends DacapoCallback {

    public AgentlessCallback(CommandLineArgs args) {
        super(args);
    }

    @Override
    public String getFileName() {
        return "agentless_" + getStringDate();
    }

}
