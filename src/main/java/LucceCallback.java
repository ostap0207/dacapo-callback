import org.dacapo.harness.CommandLineArgs;

import java.util.Date;

public class LucceCallback extends DacapoCallback {

    public LucceCallback(CommandLineArgs args) {
        super(args);
    }

    @Override
    public String getFileName() {
        return "lucce_" + getStringDate();
    }

}
