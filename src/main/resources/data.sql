insert into "CATEGORY" (category_id, category_name) values ( 0,'미분류' );
insert into "CATEGORY" (category_name) values ( 'SET' );
insert into "CATEGORY" (category_name) values ('HAMBURGER');
insert into "CATEGORY" (category_name) values ('DRINK' );
insert into "CATEGORY" (category_name) values ('SIDE' );
insert into "CATEGORY" (category_name) values ('OPTION' );


insert into "PRODUCT" (product_name, product_description, product_count, product_price, category_id) values ('새우','오동통 새우버거',10,1000,2);
insert into "PRODUCT" (product_name, product_description, product_count, product_price, category_id) values ('와퍼','1겹 와퍼',10,1500,2);
insert into "PRODUCT" (product_name, product_description, product_count, product_price, category_id) values ('더블와퍼','2겹 와퍼',10,2000,2);
insert into "PRODUCT" (product_name, product_count, product_price, category_id) values ('불고기',10,2000,2);
insert into "PRODUCT" (product_name, product_count, product_price, category_id) values ('콜라',10,1000,3);
insert into "PRODUCT" (product_name, product_count, product_price, category_id) values ('감튀',10,1000,4);
insert into "PRODUCT" (product_name, product_count, product_price, category_id) values ('새우튀김',10,1000,5);
insert into "PRODUCT" (product_name, product_description, product_count, product_price, category_id) values ('새우버거 세트','새우버거단품 + 음료 + 감자튀김',10,5000,1);
insert into "PRODUCT" (product_name, product_description, product_count, product_price, category_id) values ('와퍼 세트','와퍼 단품 + 음료 + 감자튀김',10,5000,1);

insert into "MEMBER" (MEMBER_ID,EMAIL) values (1,'test@test.com');

-- insert into "CART" (CART_ID,MEMBER_ID) values (1,1);
--
-- insert into "CART_PRODUCT" (CART_DETAIL_ID  ,QUANTITY,CART_ID,PRODUCT_ID ) values (1,1,1,1);
