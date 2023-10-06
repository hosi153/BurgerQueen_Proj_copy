insert into "CATEGORY" (category_id, category_name) values ( 1, 'HAMBURGER');
insert into "CATEGORY" (category_id, category_name) values ( 2, 'DRINK' );
insert into "CATEGORY" (category_id, category_name) values ( 3, 'SIDE' );
insert into "CATEGORY" (category_id, category_name) values ( 4, 'OPTION' );
insert into "CATEGORY" (category_id, category_name) values ( 5, 'SET' );

insert into "PRODUCT" (product_name, product_count, product_price, category_id) values ('새우',10,1000,1);
insert into "PRODUCT" (product_name, product_count, product_price, category_id) values ('불고기',10,2000,1);
insert into "PRODUCT" (product_name, product_count, product_price, category_id) values ('콜라',10,1000,2);
insert into "PRODUCT" (product_name, product_count, product_price, category_id) values ('감튀',10,1000,3);

insert into "MEMBER" (MEMBER_ID,EMAIL) values (1,'test@test.com');
