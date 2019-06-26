package info.beverlyshill.javaspringmvc;

import info.beverlyshill.javaspringmvc.test.config.ControllerTestConfig;

import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { ControllerTestConfig.class })
@ActiveProfiles("test")
@Ignore
public class AbstractControllerTest {

}
