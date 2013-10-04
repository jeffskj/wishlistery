package com.wishlistery.persistence;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.wishlistery.domain.Wishlist;

@Repository
public interface WishlistRepository extends MongoRepository<Wishlist, String> {
    
}