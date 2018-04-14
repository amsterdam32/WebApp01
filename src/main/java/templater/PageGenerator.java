package templater;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Map;

public class PageGenerator {
    public static final String HTML_DIR = "templates";

    private static PageGenerator pageGenerator;
    private static Configuration configuration;

    public static PageGenerator instance(){
        if(pageGenerator == null) pageGenerator = new PageGenerator();
        return pageGenerator;
    }

    public String getPage(String fileName, Map<String, Object> data){
        Writer stream = new StringWriter();
        try {
            Template template = configuration.getTemplate(HTML_DIR + File.separator + fileName);
            template.process(data, stream);
        }
        catch (TemplateException | IOException e){
            e.printStackTrace();
        }
        return stream.toString();
    }

    private PageGenerator(){
        configuration = new Configuration();
    }
}
