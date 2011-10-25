insert into Wishlist (description, name, version, id) values ('Jeffs Xmas Wishlist', 'Christmas Wishlist', 1, 1);
insert into WishlistCategory (name, version, wishlist_id, id) values ('(none)', 1, 1, 1);

insert into WishlistItem (category_id, description, link, title, version, id) values (1, '1 year subscription', 'http://outdoorphotographer.com', 'Outdoor Photographer Subscription', 1, 1);
insert into WishlistItem (category_id, description, link, title, version, id) values (1, '', 'http://www.rei.com', 'REI Gift Card', 1, 2);    
    
insert into WishlistCategory (name, version, wishlist_id, id) values ('Camera Stuff', 1, 1, 2);

insert into WishlistItem (category_id, description, link, title, version, id) values (2, '', 'http://www.bhphotovideo.com/c/product/274780-GREY/Nikon_2139_AF_S_VR_Zoom_NIKKOR_70_200mm.html', 'Nikon AF-S VR Zoom-NIKKOR 70-200mm f/2.8G IF-ED', 1, 3);
insert into WishlistItem (category_id, description, link, title, version, id) values (2, 'helps with doing cool effects in well lit areas', 'http://www.bhphotovideo.com/c/product/619625-REG/Singh_Ray_R_86_77mm_Vari_ND_Neutral_Density.html', 'Singh-Ray 77mm Vari-ND Variable Neutral Density Filter', 1, 4);    
    
insert into WishlistCategory (name, version, wishlist_id, id) values ('Jeep Stuff', 1, 1, 3);

insert into WishlistItem (category_id, description, link, title, version, id) values (3, 'not too particular about the brand, Gibson or Magnaflow or whatever', 'http://gibsonperformace.com', 'Cat back exhaust', 1, 5);
insert into WishlistItem (category_id, description, link, title, version, id) values (3, 'not picky about the brand', '', 'Performance tuner', 1, 6);

call next value for hibernate_sequence;
call next value for hibernate_sequence;
call next value for hibernate_sequence;
call next value for hibernate_sequence;
call next value for hibernate_sequence;
call next value for hibernate_sequence;
call next value for hibernate_sequence;
call next value for hibernate_sequence;
call next value for hibernate_sequence;
call next value for hibernate_sequence;
call next value for hibernate_sequence;
call next value for hibernate_sequence;
call next value for hibernate_sequence;
call next value for hibernate_sequence;
call next value for hibernate_sequence;
call next value for hibernate_sequence;