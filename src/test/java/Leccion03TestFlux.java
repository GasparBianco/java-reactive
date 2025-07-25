import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

public class Leccion03TestFlux {
    private Flux<Integer> getItems(){
        return Flux.range(1,5);
    }

    @Test
    public void testGetItems(){
        StepVerifier.create(getItems(), 1)
                .expectNext(1)
                .thenCancel()
                .verify();
    }

    @Test
    public void testGetItems2(){
        StepVerifier.create(getItems())
                .expectNext(1)
                .expectComplete() // is going to fail
                .verify();
    }

    @Test
    public void testGetItems3(){
        StepVerifier.create(getItems())
                .expectNext(1)
                .expectNext(2)
                .expectNext(3)
                .expectNext(4)
                .expectNext(5)
                .thenCancel()
                .verify();
    }
    @Test
    public void testGetItems4(){
        StepVerifier.create(getItems())
                .expectNext(1,2,3,4,5)
                .expectComplete()
                .verify();
    }
}
