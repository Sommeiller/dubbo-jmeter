import org.apache.jmeter.config.Arguments;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerContext;
import org.junit.Test;

/**
 * @author Sommeiller 2017/12/27 23:45
 * @version v1.0
 */
public class TestCase {

    @Test
    public void test002(){
        Arguments args = new Arguments();
        args.addArgument("source","source");
        args.addArgument("version","version");
        args.addArgument("pageSize","15");
        args.addArgument("versionNum","10");
        JavaSamplerContext javaSamplerContext = new JavaSamplerContext(args);

    }

    @Test
    public void test003(){
        Arguments args = new Arguments();
        args.addArgument("aaa","1");
        JavaSamplerContext javaSamplerContext = new JavaSamplerContext(args);
        String a  = javaSamplerContext.getParameter("aaa");

        System.out.println(a);
        System.out.println(a.getClass());

    }
}
