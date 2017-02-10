/*
 * Copyright 2008-2009 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.hasor.website.cache;
import com.google.common.cache.CacheBuilder;
import net.hasor.core.ApiBinder;
import net.hasor.core.Module;
import net.hasor.website.domain.beans.AppConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;
/**
 * Cache
 * @version : 2016年1月10日
 * @author 赵永春(zyc@hasor.net)
 */
public class CacheModule implements Module {
    protected Logger logger = LoggerFactory.getLogger(getClass());
    //
    public void loadModule(ApiBinder apiBinder) throws Throwable {
        // .初始化Cache
        final com.google.common.cache.Cache<Object, Object> map = CacheBuilder.newBuilder()//
                .maximumSize(5000)//
                .expireAfterWrite(1, TimeUnit.MINUTES)//
                .build();
        apiBinder.bindType(Cache.class).nameWith(AppConstant.CACHE_USER).toInstance(new Cache() {
            public String getName() {
                return AppConstant.CACHE_USER;
            }
            public boolean put(Object key, Object value) {
                map.put(key, value);
                return true;
            }
            public boolean put(Object key, Object value, int timeout) {
                map.put(key, value);
                return true;
            }
            public Object get(Object key) {
                if (key == null) {
                    return null;
                }
                return map.getIfPresent(key);
            }
        });
        //
    }
}