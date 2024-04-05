package org.alptis.pocsse.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@RestController
@RequestMapping("api/v1/sse")
public class SseController {

    private static final Logger log = LoggerFactory.getLogger(SseController.class);

    List<SseEmitter> emitterList = new CopyOnWriteArrayList<>();
    private Long lastId = 0L;

    @GetMapping("/subscribe")
    public SseEmitter subscribe() throws IOException {
        SseEmitter sseEmitter = new SseEmitter(600000L);
        sseEmitter.send(SseEmitter.event()
                .name("message")
                .id("" + lastId++)
                .data("connexion"));
        emitterList.add(sseEmitter);
        log.info(String.format("%d subscribers", emitterList.size()));

        return sseEmitter;
    }

    @PostMapping("/publish")
    public void publish(@RequestBody Object data) {
        emitterList.forEach(sseEmitter -> {
            try {
                sseEmitter.send(SseEmitter.event()
                        .name("message")
                        .id("" + ++lastId)
                        .data(data));
            } catch (IOException e) {
                log.error(e.getMessage());
                throw new RuntimeException(e);
            }
        });
    }

    @Scheduled(fixedRate = 10000)
    public void heartbeat() throws IOException {
        emitterList.forEach(sseEmitter -> {
            try {
                sseEmitter.send(SseEmitter.event()
                        .name("message")
                        .id("" + ++lastId)
                        .data("heartbeat"));
            } catch (IOException e) {
                log.error(e.getMessage());
                throw new RuntimeException(e);
            }
        });
    }
}
