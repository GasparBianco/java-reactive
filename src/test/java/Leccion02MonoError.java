import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class Leccion02MonoError {
    private Mono<String> metodoAProbar(){
        return Mono.error(new RuntimeException("oops"));
    }

    @Test
    public void errorTest1(){
        StepVerifier.create(metodoAProbar())
                .expectError()
                .verify();
    }

    @Test
    public void errorTest2(){
        StepVerifier.create(metodoAProbar())
                .expectError(RuntimeException.class)
                .verify();
    }

    @Test
    public void errorTest3(){
        StepVerifier.create(metodoAProbar())
                .expectErrorMessage("oops")
                .verify();
    }

    @Test
    public void errorTest(){
        StepVerifier.create(metodoAProbar())
                .consumeErrorWith((e) -> {
                    Assertions.assertEquals(e.getMessage(), "oops");
                    Assertions.assertEquals(e.getClass(), RuntimeException.class);
                })
                .verify();
    }
}
