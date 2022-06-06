package ir.maktab.homeservice.service;
/*
 * created by Amir mahdi seydi 6/5/2022 2:32 AM
 */

import ir.maktab.homeservice.exception.NotFoundException;
import ir.maktab.homeservice.exception.UnacceptableException;
import ir.maktab.homeservice.util.ConformationToken;
import net.sf.ehcache.Cache;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;
import net.sf.ehcache.config.CacheConfiguration;
import net.sf.ehcache.config.PersistenceConfiguration;
import net.sf.ehcache.store.MemoryStoreEvictionPolicy;
import org.springframework.stereotype.Service;

@Service
public class CacheService {

    private Ehcache cache;

    public CacheService() {
        init("confirmationToken");
    }

    public void init(String name){
        CacheConfiguration config = new CacheConfiguration(name,
               100000)
                .memoryStoreEvictionPolicy(MemoryStoreEvictionPolicy.LRU)
                .eternal(false)
                .timeToLiveSeconds(350)
                .timeToIdleSeconds(100000)
                .persistence(new PersistenceConfiguration().strategy(PersistenceConfiguration.Strategy.LOCALTEMPSWAP));

        Cache cache = new Cache(config);
        net.sf.ehcache.CacheManager.create().addCache(cache);
        this.cache = cache;
    }

    public void put(String key, ConformationToken value){
        if(this.cache == null) {
            throw new UnacceptableException("Cache is not initialized!");
        }
        this.cache.put(new Element(key, value));
    }

    public ConformationToken get(String key){
        Element element = this.cache.get(key);
        if(element == null) {
            throw new NotFoundException("Cache not found!");
        }
        return (ConformationToken) element.getObjectValue();
    }

    public void evict(String key) {
        this.cache.removeElement(this.cache.get(key));
    }
}
