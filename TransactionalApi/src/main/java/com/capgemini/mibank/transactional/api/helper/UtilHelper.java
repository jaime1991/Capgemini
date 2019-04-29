package com.capgemini.mibank.transactional.api.helper;

import com.capgemini.mibank.transactional.api.dto.ResponseOut;
import com.capgemini.mibank.transactional.api.dto.Status;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

/**
 * @author Jaime Cadena
 */
@Configuration
@PropertySource("classpath:messages.properties")
public class UtilHelper implements EnvironmentAware {

    static Environment env;

    public static ResponseOut mapperResponse(Object bodyResponse, int codeResponse) {
        ResponseOut response = new ResponseOut();
        Status status = new Status();
        status.setStatus_code(codeResponse);
        status.setStatus_desc(env.getProperty(ConstantHelper.KEY_DESC + codeResponse));
        response.setStatus(status);
        response.setResponse(bodyResponse);
        return response;
    }
    
    public static ResponseOut mapperResponse(int codeResponse) {
        ResponseOut response = new ResponseOut();
        Status status = new Status();
        status.setStatus_code(codeResponse);
        status.setStatus_desc(env.getProperty(ConstantHelper.KEY_DESC + codeResponse));
        response.setStatus(status);
        response.setResponse(env.getProperty(ConstantHelper.KEY_MESSAGE + codeResponse));
        return response;
    }

    @Override
    public void setEnvironment(Environment environment) {
        UtilHelper.env = environment;
    }
}
