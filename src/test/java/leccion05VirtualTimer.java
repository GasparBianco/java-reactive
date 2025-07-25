import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.time.Duration;

public class leccion05VirtualTimer {
    private Flux<Integer> metodo(){
        return Flux.range(1, 5)
                .delayElements(Duration.ofSeconds(10));
    }

    @Test
    public void test(){
        StepVerifier.withVirtualTime(this::metodo)
                .thenAwait(Duration.ofSeconds(51))
                .expectNext(1,2,3,4,5)
                .expectComplete()
                .verify();
    }

    @Test
    public void test2(){
        StepVerifier.withVirtualTime(this::metodo)
                .expectSubscription()
                .expectNoEvent(Duration.ofSeconds(9))
                .thenAwait(Duration.ofSeconds(51))
                .expectNext(1,2,3,4,5)
                .expectComplete()
                .verify();
    }
}
