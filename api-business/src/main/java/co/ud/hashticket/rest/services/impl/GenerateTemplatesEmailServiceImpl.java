package co.ud.hashticket.rest.services.impl;

import co.ud.hashticket.rest.services.GenerateTemplatesEmailService;
import co.ud.hashticket.rest.services.QRGeneratorService;
import co.ud.ud.hashticket.enumeration.TYPE_FILES;
import co.ud.ud.hashticket.utilities.Base64Utilities;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

@Service
public class GenerateTemplatesEmailServiceImpl implements GenerateTemplatesEmailService {
    @Value("${qr.path}")
    private String pathQRs;
    private QRGeneratorService qrGeneratorService;
    private Predicate<String> isGenerateQr = confirmationCode -> qrGeneratorService.generateQRCodeImage(confirmationCode);
    private UnaryOperator<String> generatePath = confirmationCode -> String.format("%s%s.png",pathQRs,confirmationCode);
    private UnaryOperator<String> generateBase64 = path -> Base64Utilities.convertFileToBase64(path, TYPE_FILES.PNG);
    @Autowired
    public GenerateTemplatesEmailServiceImpl(QRGeneratorService qrGeneratorService) {
        this.qrGeneratorService = qrGeneratorService;
    }
    @Override
    public String buyTicket(String confirmation) {
        String image = isGenerateQr.test(confirmation) ? generatePath.andThen(generateBase64).apply(confirmation) : null;
        if(Objects.nonNull(image)){
            return getTemplate(image);
        }
        return null;
    }
    private String getTemplate(String image){
        try {
            Configuration config = new Configuration(Configuration.VERSION_2_3_31);
            config.setClassForTemplateLoading(getClass(), "/email-templates/");
            Template template = config.getTemplate("buyTicket.ftl");
            Map<String, Object> data = new HashMap<>();
            data.put("dataImageQr", image);
            StringWriter sw = new StringWriter();
            template.process(data, sw);
            return sw.toString();
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return "Ok";
    }
}