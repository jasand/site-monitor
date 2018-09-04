package no.sensor.web.swagger;

import com.mangofactory.swagger.paths.SwaggerPathProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.ServletContext;

/**
 * Created by jan.arne.sandnes on 18.10.15.
 */
public class ApiPathProvider extends SwaggerPathProvider {

    private SwaggerPathProvider defaultSwaggerPathProvider;

    @Autowired
    private ServletContext servletContext;

    private String basePath;

    public ApiPathProvider(String basePath) {
        this.basePath = basePath;
    }

    @Override
    public String getApiResourcePrefix() {
        return defaultSwaggerPathProvider.getApiResourcePrefix();
    }

    public void setDefaultSwaggerPathProvider(SwaggerPathProvider defaultSwaggerPathProvider) {
        this.defaultSwaggerPathProvider = defaultSwaggerPathProvider;
    }

    @Override
    protected String applicationPath() {
        return UriComponentsBuilder
                .fromHttpUrl(basePath)
                .path(servletContext.getContextPath())
                .build()
                .toString();
    }

    @Override
    protected String getDocumentationPath() {
        return UriComponentsBuilder
                .fromHttpUrl(applicationPath())
                .pathSegment("api-docs/")
                .build()
                .toString();
    }
}
