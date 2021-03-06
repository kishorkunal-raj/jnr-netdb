
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
public class ProtocolTest {

    public ProtocolTest() {
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

    @Test public void canLookupIpProtocolByName() {
        // we try ip first and then ipv4 due to jnr/jnr-netdb#4
        Protocol p = Protocol.getProtocolByName("ip");
        if (p != null) {
            assertEquals("incorrect proto number", 0, p.getProto());
            assertEquals("incorrect name", "ip", p.getName());
        } else {
            p = Protocol.getProtocolByName("ipv4");
            assertNotNull("could not lookup ipv4 protocol", p);
            assertEquals("incorrect proto number", 4, p.getProto());
            assertEquals("incorrect name", "ipv4", p.getName());
        }
    }

    @Test public void returnsNullOnUnknownProtocol() {
        Protocol p = Protocol.getProtocolByName("foo-bar-baz");
        assertNull("could not handle unknown protocol", p);
    }

    @Test public void canLookupIpProtocolByNumber() {
        // we try ip first and then ipv4 due to jnr/jnr-netdb#4
        Protocol p = Protocol.getProtocolByName("ip");
        if (p != null) {
            p = Protocol.getProtocolByNumber(0);
            assertEquals("incorrect proto number", 0, p.getProto());
            assertEquals("incorrect name", "ip", p.getName());
        } else {
            p = Protocol.getProtocolByNumber(4);
            assertNotNull("could not lookup ip protocol", p);
            assertEquals("incorrect proto number", 4, p.getProto());
            assertEquals("incorrect name", "ipv4", p.getName());
        }
    }

    @Test public void returnsNullOnInvalidNumber() {
        Protocol p = Protocol.getProtocolByNumber(-1);
        assertNull("could not handle invalid number ", p);
    }
}
