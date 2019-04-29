package com.capgemini.mibank.transactional.api.helper;

/**
 * @author Jaime Cadena
 */
public class ConstantHelper {

    public static final String HEADER = "Authorization";
    public static final int EXPIRES_IN = 600;
    public static final String SECRET = "queenvictoria";
    public static final String APP_NAME = "Spring Service project";
    public static final String DEVICE = "Generic";
    public static final String BEARER = "Bearer";
    public static final String STATE_ACTIVE = "ACTIVE";

    public static final int CODE_EXITO = 0;
    public static final int CODE_NOT_FOUND_CUSTOMER_PRODUCT = 4000;
    public static final int CODE_ERROR_REFRESCAR_TOKEN = 5001;
    public static final int CODE_ERROR_CREATE_CUSTOMER = 5002;
    public static final int CODE_ERROR_CREATE_CUSTOMER_PRODUCT = 5003;
    public static final int CODE_ERROR_CREATE_CUSTOMER_PRODUCT2 = 5004;
    public static final int CODE_ERROR_CREATE_TRANSACTION = 5005;
    
    public static final String KEY_DESC = "code.";
    public static final String KEY_MESSAGE = "code.message";
    
    public enum TokenParameter {
        USER, CREATED, EXPIRATION, AUDIENCE
    }

}
