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
package de.sas.cloudcore.samples.repository;

import java.util.List;
import java.util.Optional;
import static de.sas.cloudcore.samples.repository.RepositoryTest.MockElement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author antonepple
 */
public class MockRepository extends Repository<MockElement>{
    private static Map<String, MockElement> users = new HashMap<>();
    public MockRepository() {
        super(MockElement.class);
    }

    @Override
    public Optional<MockElement> create(MockElement entity) {
        MockElement put = users.put(entity.id, entity);
        return Optional.of(entity);
    }

    @Override
    public Optional<MockElement> get(String id) {
        return Optional.ofNullable(users.get(id));
    }

    @Override
    public List<MockElement> getAll() {
        return new ArrayList<>(users.values());
    }

    @Override
    public boolean update(String id, MockElement entity) {
        MockElement get = users.get(id);
        return get == null ? false : users.put(id, entity) == null? false: true;
    }

    @Override
    public boolean delete(String id) {
        return users.remove(id)==null ? false: true;
    }
    
}
