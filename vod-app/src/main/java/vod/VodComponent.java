package vod;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import vod.model.Library;
import vod.service.LibraryService;

import java.util.Arrays;
import java.util.List;

@Component
@Slf4j
public class VodComponent implements CommandLineRunner, ApplicationListener<ContextRefreshedEvent> {
    private final LibraryService libraryService;

    public VodComponent(LibraryService libraryService) {
        this.libraryService = libraryService;
    }

    @PostConstruct
    void init() {
        log.info("in post construct.");
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("program arguments: {}", Arrays.toString(args));
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        log.info("on context refreshed");
        List<Library> libraries = libraryService.getAllLibraries();
        log.info("{} libraries found.", libraries.size());
        libraries.forEach(library -> log.info("{}", library));
    }

    @EventListener
    public void eventListener(ContextRefreshedEvent event) {
        log.info("on context refreshed (from annotated method)");
    }
}
