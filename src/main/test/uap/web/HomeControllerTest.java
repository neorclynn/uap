package uap.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class HomeControllerTest {
    @Test
    public void testHomePage() throws Exception {
        UserController controller = new UserController();
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/user")).andReturn();


        //System.out.println(MockMvcResultMatchers.model().attribute("users"));
    }
}