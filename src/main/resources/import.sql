-- Category

-- 1
INSERT INTO category (category_name, category_description, category_image, created_at, updated_at, deleted_at)
VALUES ('Category 1', 'Category 1 Description', 'category1.jpg', now(), now(), '1970-01-01 00:00:00+00');

-- 2
INSERT INTO category (category_name, category_description, category_image, created_at, updated_at, deleted_at)
VALUES ('Category 2', 'Category 2 Description', 'category2.jpg', now(), now(), '1970-01-01 00:00:00+00');

-- 3
INSERT INTO category (category_name, category_description, category_image, created_at, updated_at, deleted_at)
VALUES ('Category 3', 'Category 3 Description', 'category3.jpg', now(), now(), '1970-01-01 00:00:00+00');

-- Product

-- 1 - Category 1
INSERT INTO product (category_id, product_name, product_stock, product_description, product_image, product_price, created_at, updated_at, deleted_at)
VALUES (1, 'Product 1', 100,'Product 1 Description', 'product1.jpg', 100.00, now(), now(), '1970-01-01 00:00:00+00');

-- 2 - Category 2
INSERT INTO product (category_id, product_name, product_stock, product_description, product_image, product_price, created_at, updated_at, deleted_at)
VALUES (2, 'Product 2', 100,'Product 2 Description', 'product2.jpg', 200.00, now(), now(), '1970-01-01 00:00:00+00');

-- 3 - Category 3
INSERT INTO product (category_id, product_name, product_stock, product_description, product_image, product_price, created_at, updated_at, deleted_at)
VALUES (3, 'Product 3', 100,'Product 3 Description', 'product3.jpg', 300.00, now(), now(), '1970-01-01 00:00:00+00');

-- 4 - Category 1
INSERT INTO product (category_id, product_name, product_stock, product_description, product_image, product_price, created_at, updated_at, deleted_at)
VALUES (1, 'Product 4', 100,'Product 4 Description', 'product4.jpg', 400.00, now(), now(), '1970-01-01 00:00:00+00');