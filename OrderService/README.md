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

#### can not add role
https://stackoverflow.com/questions/24219953/cassandra-only-superuser-is-allowed-to-perform-create-user-queries
go to file: usr/local/etc/cassandra/.yaml

https://github.com/annaworkplace/Java-12-19-2022/tree/main/SpringKafkaDemo/src/main

https://github.com/salvorusso/e-commerce-orders/tree/main/orders/src/main/java/com/unict/dsbd/orders/messaging

https://github.com/antpro0424/a-mall/commit/3e187e6e2b8686a6a7762f5c45e0912fd948e4ab