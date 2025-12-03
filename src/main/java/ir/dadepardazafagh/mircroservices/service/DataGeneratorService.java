package ir.dadepardazafagh.mircroservices.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

@Service
@Slf4j
public class DataGeneratorService {

    private static final String[] FIRST_NAMES = {"علی","رضا","حسن","محمد","مهدی","امیر","حسین","میلاد","سجاد","پویا",
            "زهرا","فاطمه","مریم","سارا","نرگس","لیلا","پریسا","مهسا","الناز","مینا"};
    private static final String[] LAST_NAMES = {"محمدی","رضایی","حسینی","احمدی","علوی","نوری","اکبری","قاسمی","جعفری","کریمی"};
    private static final ThreadLocalRandom RAND = ThreadLocalRandom.current();

    public File generateCsvFile() throws IOException {
        long startTime = System.nanoTime();

        File csvFile = File.createTempFile("5m_record", ".csv");
        String createdAt = LocalDateTime.now().toString();

        log.info("شروع تولید ۵ میلیون رکورد...");

        try (BufferedWriter writer = new BufferedWriter(
                new FileWriter(csvFile, StandardCharsets.UTF_8), 8_388_608)) {

            StringBuilder sb = new StringBuilder(128);

            for (int i = 0; i < 5_000_000; i++) {
                sb.setLength(0);
                sb.append(FIRST_NAMES[RAND.nextInt(FIRST_NAMES.length)]).append(',')
                        .append(LAST_NAMES[RAND.nextInt(LAST_NAMES.length)]).append(',')
                        .append( 1000000000L + i).append(',')
                        .append(createdAt);

                writer.write(sb.toString());
                writer.newLine();
            }
        }

        long tookMs = (System.nanoTime() - startTime) / 1_000_000;
        log.info("۵ میلیون رکورد در {} ثانیه ساخته شد | فایل: {} | حجم: {} مگابایت",
                String.format("%.2f", tookMs / 1000.0),
                csvFile.getName(),
                csvFile.length() / (1024 * 1024));

        return csvFile;
    }
}
