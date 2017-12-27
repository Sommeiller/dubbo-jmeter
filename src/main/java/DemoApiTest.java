import org.apache.jmeter.config.Arguments;
import org.apache.jmeter.protocol.java.sampler.AbstractJavaSamplerClient;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerContext;
import org.apache.jmeter.samplers.SampleResult;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Sommeiller 2017/12/27 23:45
 * @version v1.0
 */
public class DemoApiTest extends AbstractJavaSamplerClient {
    private DemoApi demoApi = null;

    @Override
    public void setupTest(JavaSamplerContext context) {
        super.setupTest(context);
        ClassPathXmlApplicationContext springContext = new ClassPathXmlApplicationContext(new String[]{"dubbo-consumer.xml"});
        springContext.start();
        demoApi = springContext.getBean(DemoApi.class);
    }

    @Override
    public void teardownTest(JavaSamplerContext context) {
        super.teardownTest(context);
    }

    @Override
    public Arguments getDefaultParameters() {
        Arguments args = new Arguments();
        args.addArgument("parA",null);
        args.addArgument("parB",null);
        return args;
    }

    public SampleResult runTest(JavaSamplerContext context) {
        SampleResult sampleResult = new SampleResult();
        String result = null;

        sampleResult.sampleStart();
        try {
            result = demoApi.getDemoResult(context.getParameter("parA"),context.getParameter("parB"));
            sampleResult.setResponseData(result,"UTF-8");
        }catch (Exception e){
            sampleResult.setResponseData(e.toString(),"UTF-8");
        }finally {
            sampleResult.sampleEnd();
        }
        return sampleResult;
    }
}
