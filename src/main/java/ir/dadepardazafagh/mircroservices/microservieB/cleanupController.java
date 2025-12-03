package ir.dadepardazafagh.mircroservices.microservieB;

import ir.dadepardazafagh.mircroservices.service.CleanupService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("microServiceB")
@RequiredArgsConstructor
@Slf4j
public class cleanupController {
    private final CleanupService cleanupService;


    @DeleteMapping("/cleanup")
    public String manualCleanup(){
        cleanupService.truncatePersonTable();
        return "پاک شد (دستی).";
    }
}
