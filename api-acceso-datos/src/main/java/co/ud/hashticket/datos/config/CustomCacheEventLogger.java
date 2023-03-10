package co.ud.hashticket.datos.config;

import org.ehcache.event.CacheEvent;
import org.ehcache.event.CacheEventListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CustomCacheEventLogger implements CacheEventListener<Object, Object> {

    private static final Logger LOG= LoggerFactory.getLogger(CustomCacheEventLogger.class);
    @Override
    public void onEvent(CacheEvent<?, ?> event) {
        LOG.info("custom Caching event {} {} {} {} ", event.getType(),event.getKey(),event.getOldValue(),event.getNewValue());
    }
}
