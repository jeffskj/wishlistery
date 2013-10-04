--insert into WishlistUser (id, openId, email, firstName, lastName) values (1, 'https://www.google.com/accounts/o8/id?id=AItOawmgSUw97r3kAh1BjnjbnlT0iOClt1MNv7I', 'jeffskj@gmail.com', 'Jeff', 'Skjonsby');
--insert into WishlistUser (id, openId, email, firstName, lastName) values (2, 'https://www.google.com/accounts/o8/id?id=AItOawmgSUw9asdfdsahnaAF#FAFXDGAg11Andr', 'andrea.skjonsby@gmail.com', 'Andrea', 'Skjonsby');
--insert into WishlistUser (id, openId, email, firstName, lastName) values (3, 'https://www.google.com/accounts/o8/id?id=AItOawmgSUw97r3kAh1BjnjbnlTHfient1MJess', 'jess.kasper@gmail.com', 'Jessica', 'Kasper');
--insert into WishlistUser (id, openId, email, firstName, lastName) values (4, 'https://www.google.com/accounts/o8/id?id=AItOawmgSUw97r3kAh1BjnjbnlT0iOClt1MRose', 'rosehol@gmail.com', 'Rose', 'Holttum');
--insert into WishlistUser (id, openId, email, firstName, lastName) values (5, 'https://www.google.com/accounts/o8/id?id=AItOawmgSUw97r3kAh1BjnjbnlT0iOClt1MViol', 'violet.hul@gmail.com', 'Violet', 'Holttum');
--insert into WishlistUser (id, openId, email, firstName, lastName) values (6, 'https://www.google.com/accounts/o8/id?id=AItOawmgSUw97r3kAh1BjnjbnlT0iOClt1MDami', 'dam.hall@gmail.com', 'Damien', 'Hall');

--insert into Wishlist (description, name, version, id, user_id) values ('Jeffs Xmas Wishlist', 'Christmas Wishlist', 1, 1, 1);
--insert into WishlistCategory (name, version, wishlist_id, id) values ('(none)', 1, 1, 1);

--insert into WishlistItem (wishlist_id, category_id, description, link, title, version, id) values (1, 1, '1 year subscription', 'http://outdoorphotographer.com', 'Outdoor Photographer Subscription', 1, 1);
--insert into WishlistItem (wishlist_id, category_id, description, link, title, version, id) values (1, 1, '', 'http://www.rei.com', 'REI Gift Card', 1, 2);    
    
--insert into WishlistCategory (name, version, wishlist_id, id) values ('Camera Stuff', 1, 1, 2);

--insert into WishlistItem (wishlist_id, category_id, description, link, title, version, id) values (1, 2, '', 'http://www.bhphotovideo.com/c/product/274780-GREY/Nikon_2139_AF_S_VR_Zoom_NIKKOR_70_200mm.html', 'Nikon AF-S VR Zoom-NIKKOR 70-200mm f/2.8G IF-ED', 1, 3);
--insert into WishlistItem (wishlist_id, category_id, description, link, title, version, id) values (1, 2, 'helps with doing cool effects in well lit areas', 'http://www.bhphotovideo.com/c/product/619625-REG/Singh_Ray_R_86_77mm_Vari_ND_Neutral_Density.html', 'Singh-Ray 77mm Vari-ND Variable Neutral Density Filter', 1, 4);    
    
--insert into WishlistCategory (name, version, wishlist_id, id) values ('Jeep Stuff', 1, 1, 3);

--insert into WishlistItem (wishlist_id, category_id, description, link, title, version, id) values (1, 3, 'not too particular about the brand, Gibson or Magnaflow or whatever', 'http://gibsonperformace.com', 'Cat back exhaust', 1, 5);
--insert into WishlistItem (wishlist_id, category_id, description, link, title, version, id) values (1, 3, 'not picky about the brand', '', 'Performance tuner', 1, 6);

--insert into GiftExchangeDefinition (id, name, organizer_id, differentThanPrevious) values (1, 'Kasper Family Exchange', 1, 'true');
--insert into GiftExchangeDefinition_WishlistUser (GiftExchangeDefinition_id, participants_id) values (1, 1);
--insert into GiftExchangeDefinition_WishlistUser (GiftExchangeDefinition_id, participants_id) values (1, 2);
--insert into GiftExchangeDefinition_WishlistUser (GiftExchangeDefinition_id, participants_id) values (1, 3);
--insert into GiftExchangeDefinition_WishlistUser (GiftExchangeDefinition_id, participants_id) values (1, 4);
--insert into GiftExchangeDefinition_WishlistUser (GiftExchangeDefinition_id, participants_id) values (1, 5);

--ALTER SEQUENCE hibernate_sequence RESTART WITH 1000;
