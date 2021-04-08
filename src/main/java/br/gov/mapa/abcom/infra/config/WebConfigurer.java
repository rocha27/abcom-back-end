package br.gov.mapa.abcom.infra.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationModule;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.xml.MappingJackson2XmlHttpMessageConverter;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;
import java.util.Optional;

/**
 * Configurações gerais da aplicação
 */
@Configuration
@EnableScheduling
public class WebConfigurer implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS");
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {

        MappingJackson2XmlHttpMessageConverter convXML;
        Optional<HttpMessageConverter<?>> convOpt = converters.stream().filter(c -> c instanceof MappingJackson2XmlHttpMessageConverter).findFirst();
        if (convOpt.isPresent()) {
            convXML = (MappingJackson2XmlHttpMessageConverter) convOpt.get();
            convXML.setObjectMapper(createXmlMapper());
        } else {
            convXML = new MappingJackson2XmlHttpMessageConverter();
            convXML.setObjectMapper(createXmlMapper());
            converters.add(convXML);
        }

        MappingJackson2HttpMessageConverter convJSON;
        convOpt = converters.stream().filter(c -> c instanceof MappingJackson2HttpMessageConverter).findFirst();
        if (convOpt.isPresent()) {
            convJSON = (MappingJackson2HttpMessageConverter) convOpt.get();
            convJSON.setObjectMapper(createObjectlMapper());
        } else {
            convJSON = new MappingJackson2HttpMessageConverter();
            convJSON.setObjectMapper(createObjectlMapper());
            converters.add(convJSON);
        }
    }

    private XmlMapper createXmlMapper() {
        final XmlMapper mapper = new XmlMapper();
        mapper.registerModule(new JaxbAnnotationModule());
        return mapper;
    }

    private ObjectMapper createObjectlMapper() {
        final ObjectMapper mapper = new ObjectMapper();
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
//        mapper.setDateFormat(new ISO8601DateFormat());
        mapper.registerModule(new JavaTimeModule());
        mapper.registerModule(new JaxbAnnotationModule());
        return mapper;
    }
}


