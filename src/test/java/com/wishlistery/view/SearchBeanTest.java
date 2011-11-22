package com.wishlistery.view;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.wishlistery.domain.User;

@SuppressWarnings("serial")
public class SearchBeanTest {

    @Test
    public void shouldFindBasedOnName() {
        SearchBean sb = new SearchBean() {
            @Override
            List<User> findByName(String firstName, String lastName) {
                return Arrays.asList(new User(), new User(), new User());
            }
        };
        
        sb.setQuery("Joe Blow");
        sb.search();
        assertEquals(3, sb.getResults().size());
    }

    @Test
    public void shouldFindBasedOnEmail() {
        SearchBean sb = new SearchBean() {
            @Override
            List<User> findByEmail(String email) {
                return Arrays.asList(new User(), new User(), new User());
            }
        };
        sb.setQuery("blah");
        sb.search();
        assertEquals(3, sb.getResults().size());
    }
}
