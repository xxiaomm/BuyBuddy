use BuyBuddy;

create keyspace BuyBuddy
with replication={'class': 'SimpleStrategy', 'replication_factor': 1};

create table Order (
    id UUID PRIMARY KEY
);

select * from Order;