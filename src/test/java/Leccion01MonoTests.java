import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@Slf4j
public class Leccion01MonoTests {

    private Mono<String> metodoAProbar(String i){
        return Mono.fromSupplier(() -> {
            log.info("Procesando...");
            return "prduct-"+i;
        });
    }

    @Test
    public void test(){
        StepVerifier.create(metodoAProbar("1"))
                .expectNext("prduct-1")
                .expectComplete()
                .verify();
    }
}
