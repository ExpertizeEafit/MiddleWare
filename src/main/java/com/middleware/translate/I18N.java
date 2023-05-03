package com.middleware.translate;

import io.micronaut.context.MessageSource;
import io.micronaut.context.annotation.Factory;
import io.micronaut.context.i18n.ResourceBundleMessageSource;
import jakarta.inject.Singleton;

@Factory
public class I18N {
    @Singleton 
    MessageSource createMessageSource() {
        return new ResourceBundleMessageSource("i18n.messages");
    }
}