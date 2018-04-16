package agh.edu.pl.MapNotes;

import agh.edu.pl.MapNotes.model.Map;
import agh.edu.pl.MapNotes.model.MapRepository;
import agh.edu.pl.MapNotes.model.Pin;
import agh.edu.pl.MapNotes.model.PinRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.http.MockHttpOutputMessage;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MapNotesApplication.class)
@WebAppConfiguration
public class BaseControllerTest {

    public Map map1;
    public Map map2;
    public List<Pin> pins;
    public Pin pin1;
    public Pin pin2;
    public Pin pin3;

    public MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));

    public MockMvc mockMvc;

    @Autowired
    public WebApplicationContext webApplicationContext;

    @Autowired
    public PinRepository pinRepository;

    @Autowired
    public MapRepository mapRepository;

    public ObjectMapper mapper = new ObjectMapper();

    public HttpMessageConverter<Object> mappingJackson2HttpMessageConverter;

    @Autowired
    public void setConverters(HttpMessageConverter<Object>[] converters) {
        mappingJackson2HttpMessageConverter = Arrays.stream(converters)
                .filter(hmc -> hmc instanceof MappingJackson2HttpMessageConverter)
                .findAny()
                .orElse(null);
        assertNotNull("the JSON message converter must not be null", mappingJackson2HttpMessageConverter);
    }

    public String toJson(Object o) throws IOException {
        MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();
        mappingJackson2HttpMessageConverter.write(o, MediaType.APPLICATION_JSON, mockHttpOutputMessage);
        return mockHttpOutputMessage.getBodyAsString();
    }

    @Before
    public void setup() throws Exception {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();
        HashMap<String, Object> mapData = new HashMap<String, Object>();
        mapData.put("name", "flats in Krakow");

        this.map1 = new Map(mapData);
        this.map2 = new Map(new HashMap<>());

        HashMap<String, Object> pinData1 = new HashMap<>();
        pinData1.put("name", "Small house in ");
        pinData1.put("size", "32");
        pinData1.put("price", "600 tys");
        this.pin1 = new Pin(pinData1, this.map1);

        HashMap<String, Object> pinData2 = new HashMap<>();
        pinData2.put("name", "flat in Krowodrza");
        pinData2.put("price", "500 tys");
        pinData2.put("size", "50");
        this.pin2 = new Pin(pinData2, this.map1);

        HashMap<String, Object> pinData3 = new HashMap<>();
        pinData3.put("name", "Big house in ");
        pinData3.put("price", "500 tys");
        pinData3.put("size", "100");
        this.pin3 = new Pin(pinData3, this.map1);

        this.pins = new ArrayList<>();
        this.pins.add(pin1);
        this.pins.add(pin2);
        this.pins.add(pin3);

        this.pinRepository.save(this.pin1);
        this.pinRepository.save(this.pin2);
        this.pinRepository.save(this.pin3);
        this.mapRepository.save(this.map1);
        this.mapRepository.save(this.map2);
    }
}
