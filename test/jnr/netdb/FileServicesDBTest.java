
package jnr.netdb;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 */
public class FileServicesDBTest {

    public FileServicesDBTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test public void canLookupBootpServiceByName() {
        FileServicesDB db = new FileServicesDB();
        Service s = db.getServiceByName("bootps", "udp");
        assertNotNull("could not lookup bootps service", s);
        assertEquals("incorrect port", 67, s.getPort());
        assertEquals("incorrect name", "bootps", s.getName());
    }

    @Test public void canLookupBootpServiceByPort() {
        FileServicesDB db = new FileServicesDB();
        Service s = db.getServiceByPort(67, "udp");
        assertNotNull("could not lookup bootps service", s);
        assertEquals("incorrect port", 67, s.getPort());
        assertEquals("incorrect name", "bootps", s.getName());
    }

}