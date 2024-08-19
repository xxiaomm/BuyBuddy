use BuyBuddy;

create keyspace if not exists BuyBuddy
with replication = {
    'class': 'SimpleStrategy',
    'replication_factor': 1
    };

DROP TABLE IF EXISTS orders;
DROP TABLE IF EXISTS order_item;



CREATE TABLE orders (
    id UUID PRIMARY KEY,
    user_id UUID,
    total_price DOUBLE,
    order_status TEXT,  -- Enum values are stored as text
    shipping_address TEXT,
    billing_address TEXT,
    payment_method TEXT,
    create_time TIMESTAMP,
    update_time TIMESTAMP
);
DROP TABLE IF EXISTS order_items;
CREATE TABLE order_items (
     order_id UUID,
     item_id UUID,
     price DOUBLE,
     quantity INT,
     PRIMARY KEY (order_id, item_id)
);

INSERT INTO order_items (order_id, item_id, price, quantity)
VALUES (
        d0e8f382-5506-4d28-9f2d-7d1a53d7e957,
        a9a9a9b1-bb1b-4c88-bb1b-3d8cbbd7e1f0,
        30.0,
        2
        );

INSERT INTO order_items (order_id, item_id, price, quantity)
VALUES (
        d0e8f382-5506-4d28-9f2d-7d1a53d7e957,
        b8b8b8c1-cd1c-4d88-cd1c-4e9cbbd7e2f1,
        45.0,
        1
        );


INSERT INTO orders (id, user_id, total_price, order_status, shipping_address, billing_address, payment_method, create_time, update_time)
VALUES (
        d0e8f382-5506-4d28-9f2d-7d1a53d7e957,
        8b5c2c74-f3e7-4a68-b32b-8e4c76c3d052,
        105,
        'CREATE',
        '123 Shipping St, City, Country',
        '456 Billing Ave, City, Country',
        'CREDIT',
        toTimestamp(now()),
        toTimestamp(now())
        );



INSERT INTO orders (id, user_id, total_price, order_status, shipping_address, billing_address, payment_method, create_time, update_time)
VALUES (
           2748f382-5506-4d28-9f2d-7d1a53d7e222,
           8b5c2c74-f3e7-4a68-b32b-8e4c76c3d052,
           611,
           'CREATE',
           '123 Shipping St, City, Country',
           '456 Billing Ave, City, Country',
           'CREDIT',
           toTimestamp(now()),
           toTimestamp(now())
       );

INSERT INTO order_items (order_id, item_id, price, quantity)
VALUES (
           2748f382-5506-4d28-9f2d-7d1a53d7e222,
           b8b8b8c1-cd1c-4d88-cd1c-4e9cbbd7e2f1,
           50.0,
           4
       );

INSERT INTO order_items (order_id, item_id, price, quantity)
VALUES (
           2748f382-5506-4d28-9f2d-7d1a53d7e222,
           a99aa9b1-bb1b-4c88-bb1b-3d8cbbd70f10,
           411.0,
           1
       );