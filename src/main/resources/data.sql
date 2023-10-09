insert into "CATEGORY" (category_name) values ('HAMBURGER');
insert into "CATEGORY" (category_name) values ('DRINK' );
insert into "CATEGORY" (category_name) values ('SIDE' );
insert into "CATEGORY" (category_name) values ('OPTION' );
insert into "CATEGORY" (category_name) values ( 'SET' );

insert into "PRODUCT" (product_name, product_count, product_price, category_id) values ('새우',10,1000,1);
insert into "PRODUCT" (product_name, product_count, product_price, category_id) values ('불고기',10,2000,1);
insert into "PRODUCT" (product_name, product_count, product_price, category_id) values ('콜라',10,1000,2);
insert into "PRODUCT" (product_name, product_count, product_price, category_id) values ('감튀',10,1000,3);

insert into "MEMBER" (MEMBER_ID,EMAIL) values (1,'test@test.com');

insert into "CART" (CART_ID,MEMBER_ID) values (1,1);

insert into "CART_PRODUCT" (CART_DETAIL_ID  ,QUANTITY,CART_ID,PRODUCT_ID ) values (1,1,1,1);
