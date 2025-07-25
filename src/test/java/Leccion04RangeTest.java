import org.junit.jupiter.api.Test;
import org.reactivo.common.Utils;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

public class Leccion04RangeTest {
    private Flux<Integer> metodoPrueba(){
        return Flux.range(1, 100);
    }

    @Test
    public void test(){
        StepVerifier.create(metodoPrueba())
                .expectNext(1)
                .expectNextCount(98)
                .expectNext(100)
                .expectComplete()
                .verify();
    }

    private Flux<Integer> metodoPrueba2(){
        return Flux.range(1, 100)
                .map(i -> Utils.faker().random().nextInt(1, 100));
    }

    @Test
    public void test2(){
        StepVerifier.create(metodoPrueba2())
                .expectNextMatches(i -> i >= 1 && i <= 100)
                .expectNextCount(99)
                .expectComplete()
                .verify();
    }

    @Test
    public void test3(){
        StepVerifier.create(metodoPrueba2())
                .thenConsumeWhile(i -> i >= 1 && i <= 100)
                .expectComplete()
                .verify();
    }
}
