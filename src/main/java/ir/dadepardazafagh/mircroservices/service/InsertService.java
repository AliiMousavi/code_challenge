package ir.dadepardazafagh.mircroservices.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.postgresql.PGConnection;
import org.postgresql.copy.CopyManager;
import org.postgresql.core.BaseConnection;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.io.File;
import java.io.FileReader;
import java.sql.Connection;

@Service
@RequiredArgsConstructor
@Slf4j
public class InsertService {

    private final DataSource dataSource;
    public void loadFromCsv(File csvFile) throws Exception {
        log.info("شروع بارگذاری ۵ میلیون رکورد در postgresql...");
        long start = System.currentTimeMillis();

        try (Connection conn = dataSource.getConnection();
             FileReader reader = new FileReader(csvFile)) {

            CopyManager copyManager = new CopyManager((BaseConnection) conn.unwrap(PGConnection.class));

            copyManager.copyIn(
                    "COPY person (first_name, last_name, national_id, created_at) FROM STDIN WITH (FORMAT csv, DELIMITER ',')",
                    reader
            );
        }

        long took = System.currentTimeMillis() - start;
        log.info("بارگذاری در {} ثانیه با موفقیت در دیتابیس انجام شد!", took / 1000.0);
    }
}