use BuyBuddy;

create keyspace if not exists BuyBuddy
with replication = {
    'class': 'SimpleStrategy',
    'replication_factor': 1
    };

DROP TABLE IF EXISTS Orders;
DROP TYPE IF EXISTS OrderItem;

-- 'OrderItem' is a user-defined type (UDT)
CREATE TYPE OrderItem (
    item_id TEXT,
    price INT,
    quantity INT
);

CREATE TABLE Orders (
    id UUID PRIMARY KEY,
    user_id TEXT,
    total_price DOUBLE,
    order_status TEXT,
    items LIST<FROZEN<OrderItem>>,
    shipping_address TEXT,
    billing_address TEXT,
    payment_method TEXT,
    create_time TIMESTAMP,
    update_time TIMESTAMP
);

SELECT * FROM Orders;
SELECT items FROM Orders WHERE id = uuid;
