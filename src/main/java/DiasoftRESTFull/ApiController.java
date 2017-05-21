package DiasoftRESTFull;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class ApiController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
        return new Greeting(counter.incrementAndGet(),
                String.format(template, name));
    }
    @RequestMapping("/login")
    public Login login(@RequestParam(value = "name", defaultValue = "skufirin") String name) {
        return new Login(name);
    }

    @RequestMapping("/works")
    public ActualWorks actualWorks(@RequestParam(value = "userid", defaultValue = "0") String userid) {
        return new ActualWorks(userid);
    }
}
