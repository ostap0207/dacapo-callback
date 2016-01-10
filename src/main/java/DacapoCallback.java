import org.dacapo.harness.Callback;
import org.dacapo.harness.CommandLineArgs;

import java.io.IOException;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;

import static java.nio.file.Files.createFile;
import static java.nio.file.Files.notExists;
import static java.nio.file.Files.write;
import static java.nio.file.Paths.get;

public abstract class DacapoCallback extends Callback {

    private double totalTime;
    private int warmUpIterations = 1;
    private String fileName;

    public DacapoCallback(CommandLineArgs args) {
        super(args);
        totalTime = 0;
        fileName = getFileName() + ".txt";
    }

    @Override
    protected void start(String benchmark, boolean warmup) {
        super.start(benchmark, warmup);
    }

    @Override
    protected void complete(String benchmark, boolean valid, boolean warmup) {
        super.complete(benchmark, valid, warmup);
        if (this.iterations + 1 > warmUpIterations ) {
            totalTime += this.elapsed;
        }
        if (!isWarmup()) {
            double avgTime = totalTime / this.iterations;
            String result = benchmark + ":" + avgTime + "\n";
            System.out.print(result);
            try {
                if (notExists(get(fileName))) {
                    createFile(get(fileName));
                }
                write(get(fileName), result.getBytes(), StandardOpenOption.APPEND);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public abstract String getFileName();

    public String getStringDate() {
        LocalDateTime now = LocalDateTime.now();
        return now.getDayOfMonth() + "_" + now.getMonth() + "_" + now.getYear() + "_" + now.getHour() + "_" + now.getMinute();
    }
}
