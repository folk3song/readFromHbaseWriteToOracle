# readFromHbaseWriteToOracle

这个代码的功能是通过springboot从hbase中获取到到数据，然后将这些数据写入到oracle中，之后再打印在页面上。

其中我们可以根据条件来查询

获取指定的列
http://localhost:8080/syndata/query?tableName=hbase_test&qualifiers=age,name

按照列的值进行查询
http://localhost:8080/syndata/query?tableName=hbase_test&qualifiers=age,name&condition='age':'20'

按照多个列的值进行查询
http://localhost:8080/syndata/query?tableName=hbase_test&qualifiers=age,name&condition='age':'20','name':'frank'

通过范围和列值进行查询

http://localhost:8080/syndata/query?tableName=hbase_test&qualifiers=age,name&condition='age > 20','name':'ami'

