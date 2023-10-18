insert into "CATEGORY" (category_id, category_name) values ( 0,'미분류' );
insert into "CATEGORY" (category_name) values ( 'SET' );
insert into "CATEGORY" (category_name) values ('HAMBURGER');
insert into "CATEGORY" (category_name) values ('DRINK' );
insert into "CATEGORY" (category_name) values ('SIDE' );
insert into "CATEGORY" (category_name) values ('OPTION' );


insert into "PRODUCT" (product_name, product_description, product_status, product_count, product_price, category_id, product_image) values ('불고기버거','단짠단짠 불고기 버거','PRODUCT_STOP',3,2000,2,'/image/bulgogi.jpg');
insert into "PRODUCT" (product_name, product_description, product_status, product_count, product_price, category_id, product_image) values ('치즈버거','띠드버거 사주세요','PRODUCT_ING',3,2000,2,'/image/cb1.jpg');
insert into "PRODUCT" (product_name, product_description, product_status, product_count, product_price, category_id, product_image) values ('더블치즈버거','치즈가 2장 들어간 햄버거','PRODUCT_ING',3,3000,2,'/image/cb2.jpg');
insert into "PRODUCT" (product_name, product_description, product_status, product_count, product_price, category_id, product_image) values ('트리플치즈버거','치즈가 3장 들어간 햄버거','PRODUCT_ING',3,4000,2,'/image/cb3.jpg');
insert into "PRODUCT" (product_name, product_description, product_status, product_count, product_price, category_id, product_image) values ('내장파괴버거','이 햄버거를 먹으면 고지혈증에 걸릴수도 있을지 몰라','PRODUCT_ING',3,7000,2,'/image/max.jpg');
insert into "PRODUCT" (product_name, product_description, product_status, product_count, product_price, category_id, product_image) values ('더블패티버거','패티가 2장 들어간 햄버거','PRODUCT_ING',3,4000,2,'/image/double.jpg');
insert into "PRODUCT" (product_name, product_description, product_status, product_count, product_price, category_id, product_image) values ('쿼터패티버거','패티가 4장 들어간 햄버거','PRODUCT_ING',3,5000,2,'/image/4buger.jpeg');
insert into "PRODUCT" (product_name, product_description, product_status, product_count, product_price, category_id, product_image) values ('새우버거','오동통 새우버거','PRODUCT_ING',3,2000,2,'/image/shrimp.jpg');


insert into "PRODUCT" (product_name, product_description, product_status, product_count, product_price, category_id, product_image) values ('콜라','기본픽','PRODUCT_ING',3,1000,3,'/image/cola.jpeg');
insert into "PRODUCT" (product_name, product_description, product_status, product_count, product_price, category_id, product_image) values ('콜라(L)','기본픽 라지사이즈','PRODUCT_ING',3,1500,3,'/image/colaL.jpg');
insert into "PRODUCT" (product_name, product_description, product_status, product_count, product_price, category_id, product_image) values ('제로콜라','양심음료','PRODUCT_ING',3,1000,3,'/image/zero.jpeg');
insert into "PRODUCT" (product_name, product_description, product_status, product_count, product_price, category_id, product_image) values ('제로콜라(L)','양심음료 라지사이즈','PRODUCT_ING',3,1500,3,'/image/zeroL.jpg');
insert into "PRODUCT" (product_name, product_description, product_status, product_count, product_price, category_id, product_image) values ('아메리카노','한국인의 피','PRODUCT_ING',3,1000,3,'/image/ame.jpg');
insert into "PRODUCT" (product_name, product_description, product_status, product_count, product_price, category_id, product_image) values ('쥬스','신선한 생과일 쥬스','PRODUCT_ING',3,1000,3,'/image/juice.jpg');
insert into "PRODUCT" (product_name, product_description, product_status, product_count, product_price, category_id, product_image) values ('쉐이크','달콤한 밀크쉐이크','PRODUCT_ING',3,1500,3,'/image/milkshake.jpg');


insert into "PRODUCT" (product_name, product_description, product_status, product_count, product_price, category_id, product_image) values ('감자튀김','짭짤한 감자튀김','PRODUCT_STOP',3,1500,4,'/image/potato.jpg');
insert into "PRODUCT" (product_name, product_description, product_status, product_count, product_price, category_id, product_image) values ('양념감자','롯X리아의 그 감자튀김','PRODUCT_ING',3,2000,4,'/image/mixpotato.jpg');
insert into "PRODUCT" (product_name, product_description, product_status, product_count, product_price, category_id, product_image) values ('감자튀김 with 칠리소스','칠리소스와 함께 먹는 감자튀김','PRODUCT_ING',3,2000,4,'/image/potato_Chili.jpg');
insert into "PRODUCT" (product_name, product_description, product_status, product_count, product_price, category_id, product_image) values ('감자튀김 with 오지치즈','치즈가 뿌려진 감자튀김','PRODUCT_ING',3,2000,4,'/image/potato_Cheese.jpg');
insert into "PRODUCT" (product_name, product_description, product_status, product_count, product_price, category_id, product_image) values ('치즈볼 X 4','치즈가 주욱 늘어나는 치즈볼','PRODUCT_ING',3,2000,4,'/image/cheeseBall.jpg');
insert into "PRODUCT" (product_name, product_description, product_status, product_count, product_price, category_id, product_image) values ('새우튀김 X 4','새우튀김','PRODUCT_ING',3,2000,4,'/image/FriedShrimp.jpg');


insert into "PRODUCT" (product_name, product_description, product_status, product_count, product_price, category_id) values ('빨대','환경을 생각해 종이로 만든 빨대','PRODUCT_ING',10000,0,5);
insert into "PRODUCT" (product_name, product_description, product_status, product_count, product_price, category_id) values ('넵킨','환경을 생각해 종이로 만든 넵킨','PRODUCT_STOP',10000,0,5);

insert into "PRODUCT" (product_name, product_description, product_status, product_count, product_price, category_id, product_image) values ('불고기버거 세트','단짠단짠 불고기 버거','PRODUCT_STOP',3,3500,1,'/image/bulgogiset.jpg');
insert into "PRODUCT" (product_name, product_description, product_status, product_count, product_price, category_id, product_image) values ('치즈버거 세트','띠드버거 사주세요','PRODUCT_ING',3,3500,1,'/image/cb1set.jpg');
insert into "PRODUCT" (product_name, product_description, product_status, product_count, product_price, category_id, product_image) values ('더블치즈버거 세트','치즈가 2장 들어간 햄버거','PRODUCT_ING',3,4500,1,'/image/cb2set.jpg');
insert into "PRODUCT" (product_name, product_description, product_status, product_count, product_price, category_id, product_image) values ('트리플치즈버거 세트','치즈가 3장 들어간 햄버거','PRODUCT_STOP',3,5500,1,'/image/cb3set.jpg');
insert into "PRODUCT" (product_name, product_description, product_status, product_count, product_price, category_id, product_image) values ('더블패티버거 세트','패티가 2장 들어간 햄버거','PRODUCT_STOP',3,5500,1,'/image/doubleset.jpg');
insert into "PRODUCT" (product_name, product_description, product_status, product_count, product_price, category_id, product_image) values ('쿼터패티버거 세트','패티가 4장 들어간 햄버거','PRODUCT_ING',3,6500,1,'/image/4bugerset.jpg');
insert into "PRODUCT" (product_name, product_description, product_status, product_count, product_price, category_id, product_image) values ('새우버거 세트','오동통 새우버거','PRODUCT_STOP',3,3500,1,'/image/shrimpset.jpg');




insert into "PROMOTION" (promotion_name, promotion_description, amount, discount_type, promotion_status, category_id) values ('세트할인 프로모션','세트상품 20% 할인 진행 중', 20,'DISCOUNT_RATE','PROMOTION_ING',1);
insert into "PROMOTION" (promotion_name, promotion_description, amount, discount_type, promotion_status, category_id) values ('햄버거 단품할인 프로모션','햄버거 300원 할인 진행 중', 300,'DISCOUNT_AMOUNT','PROMOTION_ING',2);
update "CATEGORY" SET promotion_id=1 WHERE category_name='SET';
update "CATEGORY" SET promotion_id=2 WHERE category_name='HAMBURGER';


insert into "MEMBER" (EMAIL, user_name, password, grade, stamp, address1, address2, address3, phone) values ('admin@test.com','관리자','$2a$10$azfYLb9QxNhdIUaokehxlufvl3nomaLVIXpPVCVsEpf.WVrTFzc4i','GRADE_MASTER',0,'서울시 강서구 마곡중앙8로','마곡사이언스파크 LG유플러스', '07795','02-6987-8282');
insert into "MEMBER" (EMAIL, user_name, password, grade, stamp, address1, address2, address3, phone) values ('test@test.com','테스트사용자','$2a$10$azfYLb9QxNhdIUaokehxlufvl3nomaLVIXpPVCVsEpf.WVrTFzc4i','GRADE_VIP',0,'서울시 강서구 마곡중앙8로','마곡사이언스파크 LG유플러스', '07795','02-6987-8282');
insert into "MEMBER" (EMAIL, user_name, password, grade, stamp, address1, address2, address3, phone) values ('hg@uplus.co.kr','최호균','$2a$10$owLoHstGPxzPk8WLtbSP3ucKrWewyWHSRphSLsjnhyePOOPVW8C.u','GRADE_JOIN',0,'서울시 강서구 마곡중앙8로','마곡사이언스파크 LG유플러스', '07795','010-8080-1234');
insert into "MEMBER" (EMAIL, user_name, password, grade, stamp, address1, address2, address3, phone) values ('jy@uplus.co.kr','박지영','$2a$10$owLoHstGPxzPk8WLtbSP3ucKrWewyWHSRphSLsjnhyePOOPVW8C.u','GRADE_MEMBER',0,'서울시 강서구 마곡중앙8로','마곡사이언스파크 LG유플러스', '07795','010-8080-5678');
insert into "MEMBER" (EMAIL, user_name, password, grade, stamp, address1, address2, address3, phone) values ('up@uplus.co.kr','김유플','$2a$10$owLoHstGPxzPk8WLtbSP3ucKrWewyWHSRphSLsjnhyePOOPVW8C.u','GRADE_MASTER',0,'서울시 강서구 마곡중앙8로','마곡사이언스파크 LG유플러스', '07795','02-6987-8282');
insert into "MEMBER" (EMAIL, user_name, password, grade, stamp, address1, address2, address3, phone) values ('test2@uplus.co.kr','테스트2','$2a$10$owLoHstGPxzPk8WLtbSP3ucKrWewyWHSRphSLsjnhyePOOPVW8C.u','GRADE_FRIEND',0,'서울시 강서구 마곡중앙8로','마곡사이언스파크 LG유플러스', '07795','02-6987-8282');
insert into "MEMBER" (EMAIL, user_name, password, grade, stamp, address1, address2, address3, phone) values ('test3@uplus.co.kr','테스트3','$2a$10$owLoHstGPxzPk8WLtbSP3ucKrWewyWHSRphSLsjnhyePOOPVW8C.u','GRADE_MASTER',0,'서울시 강서구 마곡중앙8로','마곡사이언스파크 LG유플러스', '07795','02-6987-8282');



insert into "CART" (MEMBER_ID, TOTAL_COUNT, total_price) values (1,12, 18900);
insert into "CART" (MEMBER_ID, TOTAL_COUNT, total_price) values (2,1, 2000);

insert into "CART_PRODUCT" (QUANTITY,CART_ID,PRODUCT_ID ) values (1,1,1);
insert into "CART_PRODUCT" (QUANTITY,CART_ID,PRODUCT_ID ) values (1,1,2);
insert into "CART_PRODUCT" (QUANTITY,CART_ID,PRODUCT_ID ) values (1,2,3);



-- insert into "ORDER" (ORDER_ID,ORDER_STATUS,TOTAL_COUNT,TOTAL_DISCOUNT_PRICE,TOTAL_PRICE,MEMBER_ID) values (1,'ORDER_REQUEST',1,200,2000,1);
-- insert into "ORDER" (ORDER_ID,ORDER_STATUS,TOTAL_COUNT,TOTAL_DISCOUNT_PRICE,TOTAL_PRICE,MEMBER_ID) values (2,'ORDER_REQUEST',1,200,2000,1);
-- insert into "ORDER" (ORDER_ID,ORDER_STATUS,TOTAL_COUNT,TOTAL_DISCOUNT_PRICE,TOTAL_PRICE,MEMBER_ID) values (3,'ORDER_REQUEST',1,200,2000,1);
-- insert into "ORDER" (ORDER_ID,ORDER_STATUS,TOTAL_COUNT,TOTAL_DISCOUNT_PRICE,TOTAL_PRICE,MEMBER_ID) values (4,'ORDER_REQUEST',1,200,2000,2);
-- insert into "ORDER" (ORDER_ID,ORDER_STATUS,TOTAL_COUNT,TOTAL_DISCOUNT_PRICE,TOTAL_PRICE,MEMBER_ID) values (5,'ORDER_REQUEST',1,200,2000,2);
--

-- insert into "ORDER_PRODUCT" (ORDER_PRODUCT_ID,QUANTITY,ORDER_ID,PRODUCT_ID ) values (1,1,1,1);
-- insert into "ORDER_PRODUCT" (ORDER_PRODUCT_ID,QUANTITY,ORDER_ID,PRODUCT_ID ) values (2,1,2,2);
-- insert into "ORDER_PRODUCT" (ORDER_PRODUCT_ID,QUANTITY,ORDER_ID,PRODUCT_ID ) values (3,1,3,2);
-- insert into "ORDER_PRODUCT" (ORDER_PRODUCT_ID,QUANTITY,ORDER_ID,PRODUCT_ID ) values (4,1,4,2);
-- insert into "ORDER_PRODUCT" (ORDER_PRODUCT_ID,QUANTITY,ORDER_ID,PRODUCT_ID ) values (5,1,5,2);
--
-- insert into "DELIVERY" (DELIVERY_ID,DELIVERY_STATUS,ORDER_ID) values (1,'DELIVERY_READY',1);
-- insert into "DELIVERY" (DELIVERY_ID,DELIVERY_STATUS,ORDER_ID) values (2,'DELIVERY_START',2);
-- insert into "DELIVERY" (DELIVERY_ID,DELIVERY_STATUS,ORDER_ID) values (3,'DELIVERY_SUCCESS',3);
-- insert into "DELIVERY" (DELIVERY_ID,DELIVERY_STATUS,ORDER_ID) values (4,'DELIVERY_SUCCESS',4);
-- insert into "DELIVERY" (DELIVERY_ID,DELIVERY_STATUS,ORDER_ID) values (5,'DELIVERY_SUCCESS',5);


