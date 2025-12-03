package ir.dadepardazafagh.mircroservices.microservieB;

import ir.dadepardazafagh.mircroservices.service.CleanupService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class CleanupListener {

    private final CleanupService cleanupService;

    @RabbitListener(queues = "cleanup.queue")
    public void receiveFromLoadService(String message) {
        log.info("میکروسرویس a پیام فرستاد: {} — شروع پاک‌سازی...", message);
        cleanupService.truncatePersonTable();
        log.info("میکروسرویس cleanup کارش رو انجام داد — ارتباط RabbitMQ موفق بود!");
    }
}
