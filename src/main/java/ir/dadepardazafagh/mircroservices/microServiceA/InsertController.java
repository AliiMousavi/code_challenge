package ir.dadepardazafagh.mircroservices.microServiceA;

import ir.dadepardazafagh.mircroservices.service.InsertService;
import ir.dadepardazafagh.mircroservices.service.DataGeneratorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/microServiceA")
@RequiredArgsConstructor
@Slf4j
public class InsertController {

    private final DataGeneratorService generator;
    private final InsertService loader;
    private final RabbitTemplate rabbitTemplate;

    @PostMapping("/generate")
    @Async
    public ResponseEntity<String> generator() throws IOException {
        File csvFile = generator.generateCsvFile();

//        new Thread(() -> {
            try {
                loader.loadFromCsv(csvFile);
                rabbitTemplate.convertAndSend("massive.exchange", "cleanup.trigger", "done");
                log.info("پیام به میکروسرویس cleanup فرستاده شد — منتظر پاک‌سازی خودکار");
            } catch (Exception e) {
                log.error("خطا در بارگذاری", e);
            }
//        }).start();

        return ResponseEntity.accepted().body("بارگذاری شروع شد — نتیجه در لاگ");
    }
}
