package de.sas.cloudcore.samples.repository;

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
import de.sas.cloudcore.samples.cloud1.HashMapRepository;
import de.sas.cloudcore.samples.entities.User;
import java.util.Optional;
import org.testng.Assert;
import static org.testng.Assert.assertNotNull;

import org.testng.annotations.Test;

/**
 *
 * @author antonepple
 */
public class RepositoryTest {

    public RepositoryTest() {
    }

    @Test
    public void loadRepositoryTest() {
        // given: there's a repository registered

        // when: loading the repository
        Repository<User> repository = RepositoryServiceProvider.getRepository(User.class);

        // then: HashMapRepository should be loaded
        assertNotNull(repository);
        Assert.assertEquals(repository.getClass(), HashMapRepository.class);
    }

    @Test
    public void useRepositoryTest() {
        // given: we add some value to the repository
        Repository<User> repository = RepositoryServiceProvider.getRepository(User.class);
        Optional<User> created = repository.create(new User("id", "toni"));
        
        // when: we retrieve the value
        Optional<User> retrieved = repository.get(created.get().getId());
        
        // then: generics should work and the User should be there
        Assert.assertEquals(created.get(), retrieved.get());
    }



}
