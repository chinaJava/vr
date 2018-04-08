package io.renren.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @项目名称:renren-security
 * @类名:RestApiConfig
 * @类的描述: Restapi的基本配置
 * @作者:lilinhui
 * @创建时间:2017/4/20 上午10:01
 * @公司:jiayou
 * @邮箱:info@jiayouad.com
 * @使用方法：Restful API 访问路径: http://localhost:8080/renren-security/swagger-ui.html
 */
@EnableWebMvc
@EnableSwagger2
@Configuration
@ComponentScan(basePackages ="io.renren.api")
public class RestApiConfig extends WebMvcConfigurationSupport {


    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("io.renren.api"))
                .paths(PathSelectors.any())
                .build();
    }

    
   	private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("SpringMVC中使用Swagger2构建RESTful APIs")
                .termsOfServiceUrl("http://www.jiayouad.com")
                .contact(new Contact("甲由传媒","http://www.jiayouad.com","info@jiayouad.com"))
                .version("1.0.0")
                .build();
    }

}