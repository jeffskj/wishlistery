package com.wishlistery.domain;


public interface Rule {
	boolean isInvalidPair(User from, User to);
}
