package com.zws.core.validate.image;

import com.zws.core.support.ErrorEnum;
import com.zws.core.validate.AbstractValidateCodeHandler;
import com.zws.core.validate.ValidateCode;
import com.zws.core.validate.ValidateCodeException;
import org.springframework.web.context.request.ServletWebRequest;

import javax.imageio.ImageIO;

/**
 * @Author: Gosin
 * @Date: 2018/10/2 11:51
 * @Email: 2848392861@qq.com
 */
public class ImgValidateCodeHandler extends AbstractValidateCodeHandler<ImageCode> {


    @Override
    protected void send(ImageCode imageCode, ServletWebRequest servletWebRequest)  {
        try {
            ImageIO.write(imageCode.getImage(), "JPEG", servletWebRequest.getResponse().getOutputStream());
        }catch (Exception e){
            throw  new ValidateCodeException(ErrorEnum.VALIDATE_IMAGE_SEND_ERROR);
        }
    }

    @Override
    protected String getKey(ServletWebRequest servletWebRequest) {
        return "img-code";
    }
}
