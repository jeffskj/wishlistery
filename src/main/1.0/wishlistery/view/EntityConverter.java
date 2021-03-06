package com.wishlistery.view;

import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.google.common.base.Strings;
import com.wishlistery.domain.BaseEntity;

@RequestScoped
@FacesConverter("entity")
public class EntityConverter implements Converter {

    @Inject
    private EntityManager em;
    
    @Override
    public Object getAsObject(FacesContext ctx, UIComponent component, String value) {
        String[] parts = value.split("-");
        if (Strings.isNullOrEmpty(value)) {
            return null;
        }
        try {
            return em.find(Class.forName(BaseEntity.class.getPackage().getName() + "." + parts[0]), Long.valueOf(parts[1]));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getAsString(FacesContext ctx, UIComponent component, Object value) {
        return value.getClass().getSimpleName() + "-" + String.valueOf(((BaseEntity)value).getId());
    }

}
