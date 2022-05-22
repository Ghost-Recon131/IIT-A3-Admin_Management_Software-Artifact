package rmit.iit.a3.Logs.service;

import com.thedeanda.lorem.Lorem;
import com.thedeanda.lorem.LoremIpsum;
import lombok.AllArgsConstructor;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rmit.iit.a3.Logs.model.Log;
import rmit.iit.a3.Logs.repository.LogRepository;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

@Service
public class LogService {

    private final org.apache.logging.log4j.Logger logger = LogManager.getLogger(this.getClass());

    @Autowired
    private LogRepository logRepository;

    Lorem lorem = LoremIpsum.getInstance();

    public LogService(LogRepository logRepository) {
        this.logRepository = logRepository;
    }

    // Generate mock data if database is empty
    public void generateMockData(){
        if(logRepository.findAll().isEmpty()){
            Log newSystemLog = new Log(105L, lorem.getParagraphs(3, 5),"10kb" );
            Log newSystemLog2 = new Log(1241L, lorem.getParagraphs(3, 5),"2kb" );
            Log newSystemLog3 = new Log(12L, lorem.getParagraphs(3, 5),"15kb" );
            Log newSystemLog4 = new Log(6132L, lorem.getParagraphs(3, 5),"25kb" );

            logRepository.save(newSystemLog);
            logRepository.save(newSystemLog2);
            logRepository.save(newSystemLog3);
            logRepository.save(newSystemLog4);
        }
    }

    // Retrieves all database entries for logs then generates CSV file
    public void generateCSV(Writer writer) {
        List<Log> logArrayList = logRepository.findAll();
        try (CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT)) {
            for (Log log : logArrayList) {
                csvPrinter.printRecord(log.getLogID(), log.getCreateTime(), log.getUserID(), log.getFileSize(), log.getData());
            }
        } catch (IOException e) {
            logger.error("Error exporting CSV \n" + e.getMessage());
        }
    }
    
}
