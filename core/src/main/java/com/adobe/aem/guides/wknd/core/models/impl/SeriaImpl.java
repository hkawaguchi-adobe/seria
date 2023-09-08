package com.adobe.aem.guides.wknd.core.models.impl;

import javax.annotation.PostConstruct;
import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;
import org.apache.sling.models.annotations.injectorspecific.Self;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.apache.sling.models.factory.ModelFactory;
import com.adobe.aem.guides.wknd.core.models.Seria;
import com.adobe.cq.wcm.core.components.models.Image;

@Model(
        adaptables = {SlingHttpServletRequest.class},
        adapters = {Seria.class},
        resourceType = {SeriaImpl.RESOURCE_TYPE},
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)
public class SeriaImpl implements Seria {
    protected static final String RESOURCE_TYPE = "wknd/components/seria";

    @Self
    private SlingHttpServletRequest request;

    @OSGiService
    private ModelFactory modelFactory;

    @ValueMapValue
    private String name;

    @ValueMapValue
    private String description;

    @ValueMapValue
    private int modelNumber;

    private Image image;

    /**
    * @PostConstruct is immediately called after the class has been initialized
    * but BEFORE any of the other public methods.
    * It is a good method to initialize variables that is used by methods in the rest of the model
    *
    */
    @PostConstruct
    private void init() {
        // set the image object
        image = modelFactory.getModelFromWrappedRequest(request, request.getResource(), Image.class);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public String getModelNumber(){
        return "製品番号："+modelNumber;
    }

    @Override
    public boolean isEmpty() {
        final Image componentImage = getImage();
        
        // javaを学習するまでとりあえずコメントアウト 
        /*if(StringUtils.isBlank(name) || StringUtils.isBlank(description) || modelNumber == null || componentImage == null){
            return true;
        }*/

        return false;
    }

    /**
    * @return the Image Sling Model of this resource, or null if the resource cannot create a valid Image Sling Model.
    */
    private Image getImage() {
        return image;
    }
}

