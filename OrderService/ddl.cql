use BuyBuddy;

create keyspace if not exists BuyBuddy
with replication = {
    'class': 'SimpleStrategy',
    'replication_factor': 1
    };

create table Order (
    id UUID PRIMARY KEY
);
CREATE TABLE Orders (
    id UUID PRIMARY KEY,
    user_id UUID,
    order_status TEXT,

    payment_method TEXT,
    total_amount DECIMAL,
    created_at TIMESTAMP,
    updated_at TIMESTAMP,
);

select * from Order;