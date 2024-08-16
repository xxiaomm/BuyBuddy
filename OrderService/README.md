## Order Service

### Install and Run cassandra
https://medium.com/@manishyadavv/how-to-install-cassandra-on-mac-os-d9338fcfcba4
```bash
# stable version
brew install cassandra@4.0.13  
# show all avaliable java version
/usr/libexec/java_home -V 
# now use this version, 其他版本报错
export JAVA_HOME=`/usr/libexec/java_home -v 11.0.24`  
# show current using version
java --version 
# start cassandra
cassandra -f
# Open new Terminal Tab and type
cqlsh
```

### Database Scripts
#### Create orders
```json
{
  "userId": "8b5c2c74-f3e7-4a68-b32b-8e4c76c3d052",
  "shippingAddress": "123 Main St, Springfield, IL",
  "billingAddress": "456 Elm St, Springfield, IL",
  "paymentMethod": "Cash",
  "orderItems": [
    {
      "itemId": "a9a9a9b1-bb1b-4c88-bb1b-3d8cbbd7e1f0",
      "price": 55,
      "quantity": 2
    },
    {
      "itemId": "b8b8b8c1-cd1c-4d88-cd1c-4e9cbbd7e2f1",
      "price": 20,
      "quantity": 1
    }
  ]
}
```
```json
{
  "userId": "8b5c2c74-f3e7-4a68-b32b-8e4c76c3d052",
  "shippingAddress": "123 Main St, Springfield, IL",
  "billingAddress": "456 Elm St, Springfield, IL",
  "paymentMethod": "apple_pay",
  "orderItems": [
    {
      "itemId": "a9a9a9b1-bb1b-4c88-bb1b-3d8cbbd7e1f0",
      "price": 55,
      "quantity": 6
    },
    {
      "itemId": "b8b8b8c1-cd1c-4d88-cd1c-4e9cbbd7e2f1",
      "price": 20,
      "quantity": 3
    }
  ]
}
```


#### can not add role
https://stackoverflow.com/questions/24219953/cassandra-only-superuser-is-allowed-to-perform-create-user-queries
go to file: usr/local/etc/cassandra/.yaml

https://github.com/annaworkplace/Java-12-19-2022/tree/main/SpringKafkaDemo/src/main

https://github.com/salvorusso/e-commerce-orders/tree/main/orders/src/main/java/com/unict/dsbd/orders/messaging

https://github.com/antpro0424/a-mall/commit/3e187e6e2b8686a6a7762f5c45e0912fd948e4ab