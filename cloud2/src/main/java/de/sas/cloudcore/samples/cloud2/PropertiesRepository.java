/* 
 * Copyright (c) 2020 Anton Epple
 * 
 * Permission is hereby granted, free of charge, to any person obtaining
 * a copy of this software and associated documentation files (the
 * "Software"), to deal in the Software without restriction, including
 * without limitation the rights to use, copy, modify, merge, publish,
 * distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to
 * the following conditions:
 * 
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
 * LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
 * OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
 * WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 * 
 */
package de.sas.cloudcore.samples.cloud2;

import de.sas.cloudcore.samples.entities.User;
import de.sas.cloudcore.samples.repository.Repository;
import java.util.List;
import java.util.Optional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;

/**
 * A class representing a proprietary cloud repository
 * @author antonepple
 */
public class PropertiesRepository extends Repository<User>{
    private static Properties users = new Properties();
    public PropertiesRepository() {
        super(User.class);
        System.out.println("PropertiesRepository instantiated");
    }

    @Override
    public Optional<User> create(User entity) {
        entity.setId(UUID.randomUUID().toString());
        User put = (User) users.put(entity.getId(), entity);
        return Optional.of(entity);
    }

    @Override
    public Optional<User> get(String id) {
        return Optional.ofNullable((User) users.get(id));
    }

    @Override
    public List<User> getAll() {
        return new ArrayList<>((List<User>)(List<?>)users.values());
    }

    @Override
    public boolean update(String id, User entity) {
        User get = (User)users.get(id);
        return get == null ? false : users.put(id, entity) == null? false: true;
    }

    @Override
    public boolean delete(String id) {
        return users.remove(id)==null ? false: true;
    }
    
}
