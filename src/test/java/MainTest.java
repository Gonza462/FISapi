import junit.framework.TestCase;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainTest extends TestCase {
    //valid json to be added
    String testjson = "{\"email\":\"uniquethis71@gmail.com\",\"name\":\"Hello World\",\"company\":\"Hard Coded Company\"}";
    String tfile = "testjson.csv";
    String createfile = "finalreport.csv";
    List<List<String>> te = new ArrayList<>();

    @Before
    public void setUp(){
        te.add(Arrays.asList("Luis","luis@gmail.com"));
        te.add(Arrays.asList("Luis","luis1@gmail.com"));
        te.add(Arrays.asList("Luis","luis2@gmail.com"));
    }
    @Test
    public void testWriteReport() {
        List<List<String>> e = new ArrayList<>();
        boolean bool = Main.writeReport(e,createfile);
        assertFalse(bool);
        bool = Main.writeReport(te,createfile);
        assertTrue(bool);
    }
    //make sure return json array matches the testjson array created.
    @Test
    public void testSerializeCustomersReturnJson() throws IOException, JSONException {
        //set up
        JSONObject expected = new JSONObject(testjson);
        JSONArray e = new JSONArray();
        e.put(expected);
        //System.out.println("expected" + e);
        JSONArray actual = Main.serializeCustomers(tfile,createfile);
        //System.out.println("actual  " + actual.toString());
        assertNotNull(actual);
        assertEquals(e.toString(),actual.toString());
    }
    @Test
    public void testDoGet() throws IOException, JSONException {
        boolean check = Main.doGet("srlintag@gmail.com",createfile);
        System.out.print(check);
        assertTrue(check);
        boolean check2 = Main.doGet("nothere1@gmail.com",createfile);
        assertFalse(check2);
    }
    @Test(expected =  RuntimeException.class)
    public void testDoPost() throws JSONException {
        String jso = "{\"email\":\"som121@gmail.com\",\"name\":\"Hello World\",\"company\":\"Hard Coded Company\"}";
        JSONObject j = new JSONObject(jso);
        String s = Main.doPost(j);
        assertNotNull(s);
    }

}