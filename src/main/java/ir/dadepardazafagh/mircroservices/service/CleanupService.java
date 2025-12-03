package ir.dadepardazafagh.mircroservices.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class CleanupService {
    private final JdbcTemplate jdbcTemplate;
    public void truncatePersonTable() {
        long start = System.nanoTime();
        jdbcTemplate.execute("TRUNCATE TABLE person RESTART IDENTITY CASCADE");
        long took = System.nanoTime() - start;
        log.info("۵ میلیون رکورد در {} میلی‌ثانیه پاک شد!", took / 1_000_000);
    }
}